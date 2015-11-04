package io.mycat.ep.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	private static ClassPathXmlApplicationContext ctx;
	private static final String configPath="classpath:config/application*.xml";
	public static synchronized <T> T getBean(Class<T> beanCls) {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext(configPath);
			ctx.registerShutdownHook();
		}

		return ctx.getBean(beanCls);
	}

	public static synchronized <T> T getBean(String beanName) {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext(configPath);
			ctx.registerShutdownHook();
		}

		return (T)ctx.getBean(beanName);
	}
	
	public static  synchronized void shutdown()
	{
		if(ctx!=null)
		{
			ctx.close();
			ctx=null;
		}
	}
}
