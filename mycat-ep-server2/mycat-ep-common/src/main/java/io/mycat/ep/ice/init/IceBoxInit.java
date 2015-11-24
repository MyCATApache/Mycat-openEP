package io.mycat.ep.ice.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import io.mycat.ep.util.SpringInitializer;

/**
 * 以如下命令启动IceBox管理的RPC服务：<br/>
 * java -cp "$APP_HOME/classes/*;$APP_HOME/lib/*;$ICE_HOME/lib/*" IceBox.Server --Ice.Config=config.properties -DAsyncLogger.WaitStrategy=Block
 * --Ice.Config的每个配置项也可以写到命令行里
 * IceInit不用icebox管理服务，因此需要自己管理
 * @author me
 *
 */
public class IceBoxInit implements IceBox.Service{
	private static final Logger log=LoggerFactory.getLogger(IceBoxInit.class);
	private static final ConfigurableApplicationContext appContext=SpringInitializer.create();
	private Ice.ObjectAdapter adapter;
	
	private Collection<Ice.Object> objects;
	
	public IceBoxInit(String... serviceNames){
		objects=new ArrayList<Ice.Object>();
		for(String serviceName:serviceNames){
			objects.add((Ice.Object)appContext.getBean(serviceName));
		}
	}
	public IceBoxInit(ConfigurableApplicationContext appContext,Class<Ice.Object>... serviceTypes){
		objects=new ArrayList<Ice.Object>();
		for(Class<Ice.Object> serviceType:serviceTypes){
			objects.addAll(appContext.getBeansOfType(serviceType).values());
		}
	}
	
	public void start(String name, Ice.Communicator communicator, String[] args) {
		adapter=communicator.createObjectAdapter(name);
		for(Ice.Object object:objects){//不建议将多个服务注册到一个Endpoint，所以objects.size()最好是1
			adapter.add(object, communicator.stringToIdentity(name));
		}
		adapter.activate();
		log.info(name, "service started, with param:"+Arrays.toString(args));
	}

	public void stop() {
		adapter.destroy();
	}
	
}
