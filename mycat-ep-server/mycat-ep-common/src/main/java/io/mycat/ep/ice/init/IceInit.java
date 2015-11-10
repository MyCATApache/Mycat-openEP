//package io.mycat.ep.ice.init;
//
//import org.joda.time.Instant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//
//import io.mycat.ep.constant.MagicData;
//import io.mycat.ep.exception.UnknownRPCServiceExcepton;
//import io.mycat.ep.ice.proxy.IceProxy;
//import io.mycat.ep.util.ProcessInfo;
//import me.jor.util.Help;
//
//public class IceInit {
//	private static final Logger log=LoggerFactory.getLogger(IceInit.class);
//	private Ice.Communicator ic;
//	private Ice.Object object;
//	public IceInit(ApplicationContext appContext){
//		String serviceName=ProcessInfo.getAppIceServiceName();
//    	if(Help.isNotEmpty(serviceName)){
//    		object=(Ice.Object)appContext.getBean(serviceName);
//    	}else{
//    		Class<Ice.Object> serviceType=ProcessInfo.getAppIceServiceType();
//    		if(serviceType!=null){
//    			object=appContext.getBeansOfType(serviceType).values().iterator().next();
//    		}else{
//    			throw new UnknownRPCServiceExcepton();
//    		}
//    	}
//	}
//	public IceInit(ApplicationContext appContext,Class<Ice.Object> serviceType){
//		object=appContext.getBeansOfType(serviceType).values().iterator().next();
//	}
//	public IceInit(ApplicationContext appContext,String serviceName){
//		object=(Ice.Object)appContext.getBean(serviceName);
//	}
//
//	public void start(String[] args){
//		ic=Ice.Util.initialize(args);
//		Ice.ObjectAdapter adapter=ic.createObjectAdapterWithEndpoints(
// 				ProcessInfo.getAppIceAdapterName(),
// 				ProcessInfo.getAppIcePort());
//		for(String identity:ProcessInfo.getAppIceIdentity().split(MagicData.APP_ICE_IDENTITY_SPLITOR)){
//			adapter.add(object, Ice.Util.stringToIdentity(identity));
//		}
//		adapter.activate();
//		log.info("APP "+ProcessInfo.getAppIceName()+" STARTED ON:"+Instant.now());
//		ic.waitForShutdown();
//	}
//	public void destroy(){
//		if(ic!=null){
//			ic.destroy();
//		}
//		IceProxy.destroy();
//		log.info("APP "+ProcessInfo.getAppIceName()+" TERMINATED ON:"+Instant.now());
//		System.exit(0);
//	}
//}
