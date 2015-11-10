//package io.mycat.ep;
//
//import org.springframework.beans.BeansException;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//
//import io.mycat.ep.ice.init.IceInit;
//import io.mycat.ep.util.ProcessInfo;
///**
// * 使用Bootstrap启动就不要再使用IceBoxInit，也不需要每个服务模块创建自己的main方法类
// */
//@ComponentScan("classpath*:**/applicationContext*.xml")
//@EnableAutoConfiguration
//public class Bootstrap{
//	private static ConfigurableApplicationContext appContext;//=SpringInitializer.create();
//	private static IceInit iceInit;
//    public static void startup(String[] args) throws BeansException, ClassNotFoundException{
//    	iceInit=new IceInit(appContext);
//    	try{
//    		iceInit.start(args);
//    	}finally{
//    		iceInit.destroy();
//    	}
//    }
//    public static void main(String[] args) throws BeansException, ClassNotFoundException{
//    	ProcessInfo.setMainArgs(args);
//    	appContext=SpringApplication.run(Bootstrap.class, args);
//    	startup(args);
//    }
//}
