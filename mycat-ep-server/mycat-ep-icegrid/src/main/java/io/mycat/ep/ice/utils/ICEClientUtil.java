package io.mycat.ep.ice.utils;

import Ice.ObjectPrx;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ICEClientUtil {
	private static volatile Ice.Communicator ic = null;
	@SuppressWarnings("rawtypes")
	private static Map<Class, ObjectPrx> cls2PrxMap = new HashMap<Class, ObjectPrx>();
	private static volatile long lastAccessTimestamp;
	private static volatile MonitorThread nonitorThread;
	private static long idleTimeOutSeconds = 3000;
	private static String iceLocator = null;
	private static final String locatorKey = "--Ice.Default.Locator";
	private static boolean isdebug = false;
	private static String endpoint = null;

	public static Ice.Communicator getICECommunictor() {
		if (ic == null) {
			synchronized (ICEClientUtil.class) {
				if (ic == null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println(sdf.format(new Date()) + "\tIce client's locator is "
							+ iceLocator
							+ " proxy cache time out seconds :"
							+ idleTimeOutSeconds);

					String[] initParams = new String[]{locatorKey + "="
							+ iceLocator};
					if (iceLocator == null) {
						ic = Ice.Util.initialize();
					} else {
						ic = Ice.Util.initialize(initParams);
					}
					createMonitorThread();
				}
			}
		}
		lastAccessTimestamp = System.currentTimeMillis();
		return ic;
	}

	private static void createMonitorThread() {
		nonitorThread = new MonitorThread();
		nonitorThread.setDaemon(true);
		nonitorThread.start();
	}

	public static void closeCommunicator(boolean removeServiceCache) {
		synchronized (ICEClientUtil.class) {
			if (ic != null) {
				safeShutdown();
				nonitorThread.interrupt();
				if (removeServiceCache && !cls2PrxMap.isEmpty()) {
					try {
						cls2PrxMap.clear();
					} catch (Exception e) {
						// ignore
					}
				}
			}
		}

	}

	private static void safeShutdown() {
		try {
			ic.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ic.destroy();
			ic = null;
		}
	}

	/**
	 * 仅限于Ice服务内部之间非异步方法的场景
	 *
	 * @param communicator
	 * @param serviceCls
	 * @return ObjectPrx
	 */
	@SuppressWarnings("rawtypes")
	public static ObjectPrx getSerivcePrx(Ice.Communicator communicator,
										  Class serviceCls, String version) {
		return createIceProxy(communicator, serviceCls, version);

	}

	@SuppressWarnings("rawtypes")
	private static ObjectPrx createIceProxy(Ice.Communicator communicator,
											Class serviceCls, String Version) {
		ObjectPrx proxy = null;
		String clsName = serviceCls.getName();
		String serviceName = serviceCls.getSimpleName();
		int pos = serviceName.lastIndexOf("Prx");
		if (pos <= 0) {
			throw new IllegalArgumentException(
					"Invalid ObjectPrx class ,class name must end with Prx");
		}
		String realSvName = serviceName.substring(0, pos);

		try {
			ObjectPrx base = null;
			if (!isdebug)
				base = communicator.stringToProxy(realSvName);
			else
				base = communicator.stringToProxy(realSvName + ":" + endpoint);

			proxy = (ObjectPrx) Class.forName(clsName + "Helper").newInstance();
			Method m1 = proxy.getClass().getDeclaredMethod("checkedCast",
					ObjectPrx.class, String.class);
			proxy = (ObjectPrx) m1.invoke(proxy, base, Version == null ? null : Version.toUpperCase());
			return proxy;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 用于客户端API获取ICE服务实例的场景
	 *
	 * @param serviceCls
	 * @return ObjectPrx
	 */
	@SuppressWarnings("rawtypes")
	public static ObjectPrx getSerivcePrx(Class serviceCls, String Version) {
		ObjectPrx proxy = cls2PrxMap.get(serviceCls);
		if (proxy != null) {
			lastAccessTimestamp = System.currentTimeMillis();
			return proxy;
		}
		proxy = createIceProxy(getICECommunictor(), serviceCls, Version);
		cls2PrxMap.put(serviceCls, proxy);
		lastAccessTimestamp = System.currentTimeMillis();
		return proxy;
	}


	static class MonitorThread extends Thread {
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(5000L);
					if (lastAccessTimestamp + idleTimeOutSeconds * 1000L < System
							.currentTimeMillis()) {
						closeCommunicator(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	@Value("${Ice.Default.Locator}")
	private void setIceLocator(String iceLocator) {
		ICEClientUtil.iceLocator = iceLocator;
	}

	@Value("${Ice.idleTimeOutSeconds}")
	private void setIdleTimeOutSeconds(long idleTimeOutSeconds) {
		ICEClientUtil.idleTimeOutSeconds = idleTimeOutSeconds;

	}

	@Value("${Ice.debug}")
	public static void setIsdebug(boolean isdebug) {
		ICEClientUtil.isdebug = isdebug;
	}

	public static void setEndpoint(String endpoint) {
		ICEClientUtil.endpoint = endpoint;
	}
}
