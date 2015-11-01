package io.mycat.ep.ice.server;

import IceBox.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("classpath*:**/applicationContext*.xml")
@EnableAutoConfiguration
public class Sl4jIceBoxServer {
	private static ConfigurableApplicationContext appContext;
	public static void main(String[] args)
	{
		  appContext= SpringApplication.run(Sl4jIceBoxServer.class,args);
		  Ice.InitializationData initData = new Ice.InitializationData();
	        initData.properties = Ice.Util.createProperties();
	        initData.properties.setProperty("Ice.Admin.DelayCreation", "1");
	        initData.logger=new Sl4jLogerI("system");

	        Server server = new Server();
	        System.exit(server.main("IceBox.Server", args, initData));
	}
}
