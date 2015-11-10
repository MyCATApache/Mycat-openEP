//package io.mycat.ep.util;
//
//import java.lang.annotation.Annotation;
//import java.util.Map;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//
//public class ApplicationContextUtils implements ApplicationContextAware{
//	private static ApplicationContext context;
//
//	public ApplicationContextUtils(){}
//
//	public static <T> T getBean(Class<T> cls){
//		return context.getBean(cls);
//	}
//	public static <T> Map<String,T> getBeans(Class<T> cls){
//		return context.getBeansOfType(cls);
//	}
//	public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotation){
//		return context.getBeansWithAnnotation(annotation);
//	}
//
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		context=applicationContext;
//	}
//
//}
