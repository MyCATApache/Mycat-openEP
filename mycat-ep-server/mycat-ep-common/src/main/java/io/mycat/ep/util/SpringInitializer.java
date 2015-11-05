package io.mycat.ep.util;

import me.jor.util.Help;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class SpringInitializer {
	public static GenericApplicationContext create(){
		GenericApplicationContext context=new GenericApplicationContext();
		initAnnotatedConf(context);
		initXmlConf(context);
		initGroovyConf(context);
		initPropertiesConf(context);
		context.refresh();
		context.registerShutdownHook();
		return context;
	}
	private static void initAnnotatedConf(GenericApplicationContext context){
		Class componentScanAnnotated=ProcessInfo.getAnnotationInitClass();
		if(componentScanAnnotated!=null){
			AnnotatedGenericBeanDefinition def=new AnnotatedGenericBeanDefinition(ProcessInfo.getAnnotationInitClass());
			AnnotatedBeanDefinitionReader reader=new AnnotatedBeanDefinitionReader(context);
			AnnotationScopeMetadataResolver resolver=new AnnotationScopeMetadataResolver();
			resolver.resolveScopeMetadata(def);
			reader.setScopeMetadataResolver(resolver);
		}
	}
	private static void initXmlConf(GenericApplicationContext context){
		String classPath=ProcessInfo.getXmlClassPath();
		if(Help.isNotEmpty(classPath)){
			XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(context);
			reader.loadBeanDefinitions(new ClassPathResource(classPath));
		}
	}
	private static void initGroovyConf(GenericApplicationContext context){
		String classPath=ProcessInfo.getGroovyClassPath();
		if(Help.isNotEmpty(classPath)){
			GroovyBeanDefinitionReader reader=new GroovyBeanDefinitionReader(context);
			reader.loadBeanDefinitions(new ClassPathResource(classPath));
		}
	}
	private static void initPropertiesConf(GenericApplicationContext context){
		String classPath=ProcessInfo.getPropertiesClassPath();
		if(Help.isNotEmpty(classPath)){
			PropertiesBeanDefinitionReader reader=new PropertiesBeanDefinitionReader(context);
			reader.loadBeanDefinitions(classPath);
		}
	}
}
