package io.mycat.ep.ice.server;

import Ice.Communicator;
import Ice.DispatchInterceptor;
import Ice.Identity;
import Ice.ObjectAdapter;
import IceBox.Service;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public abstract class AbstractIceBoxService   implements Service {
	protected ObjectAdapter _adapter;
	protected Identity id;
	protected static org.slf4j.Logger logger = LoggerFactory
			.getLogger(AbstractIceBoxService.class);
	protected static Sl4jLogerI iceLogger=new Sl4jLogerI("communicator");

	@Override
	public void start(String name, Communicator communicator, String[] args) {
		Ice.Util.setProcessLogger(iceLogger);
		// 创建objectAdapter，这里和service同名
		_adapter = communicator.createObjectAdapter(name);
		// 创建servant并激活
		Ice.Object object = this.createMyIceServiceObj(args);
		id = communicator.stringToIdentity(name);
		// _adapter.add(object, communicator.stringToIdentity(name));
		DispatchInterceptor interceptor = PerfDispatchInterceptor.addICEObject(id, object);

		_adapter.add(interceptor, id);

		addMyIceServiceObjFacets(_adapter,id);

		_adapter.activate();
		logger.info(name + "service started ,with param size " + args.length
				+ " detail:" + Arrays.toString(args));
	}

	protected abstract void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id);

	/**
	 * 创建具体的ICE 服务实例对象
	 * 
	 * @param args
	 *            服务的配置参数，来自icegrid.xml文件
	 * @return Ice.Object
	 */
	public abstract Ice.Object createMyIceServiceObj(String[] args);

	@Override
	public void stop() {
		logger.info("stopping service " + id + " ....");
		_adapter.destroy();
		PerfDispatchInterceptor.removeICEObject(id);
		logger.info("stopped service " + id + " stoped");

	}

}
