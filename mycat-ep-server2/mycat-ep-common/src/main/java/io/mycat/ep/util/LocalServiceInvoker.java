package io.mycat.ep.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.mail.MethodNotSupportedException;

public class LocalServiceInvoker {
	public static <I,R,P> R invokeLocal(I i,Method method,P param) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, MethodNotSupportedException{
		if(param==null){
			return (R)method.invoke(i);
		}
		return (R)method.invoke(i,param);
	}
}
