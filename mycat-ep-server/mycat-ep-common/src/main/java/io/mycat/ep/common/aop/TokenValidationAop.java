package io.mycat.ep.common.aop;

import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;

import io.mycat.ep.constant.CommonAccessStatus;
import io.mycat.ep.v1.user.UserSession;
import io.mycat.ep.v1.user.session.proxy.UserSessionHandlerProxy;

public class TokenValidationAop {
	private Set<String> ignoredMethods;
	public Set<String> getIgnoredMethods() {
		return ignoredMethods;
	}
	public void setIgnoredMethods(Set<String> ignoredMethods) {
		this.ignoredMethods = ignoredMethods;
	}
	private boolean ignored(String method){
		return ignoredMethods.contains(method);
	}
	public Object validate(ProceedingJoinPoint point) throws Throwable {
		if(ignored(point.getSignature().getName())){
			return point.proceed();
		}
		Object[] args=point.getArgs();
		Object arg=args[0];
		Class c=arg.getClass();
		Long userId=(Long)c.getMethod("getUserId").invoke(arg);
		String token=(String)c.getMethod("getToken").invoke(arg);
		UserSession session=UserSessionHandlerProxy.check(userId, token);
		if(session.status<CommonAccessStatus.SUCCESS){
			int l=args.length;
			Class[] argTypes=new Class[l];
			for(int i=0;i<l;i++){
				argTypes[i]=args[i].getClass();
			}
			Class returnType=point.getTarget().getClass().getMethod(point.getSignature().getName(),argTypes).getReturnType();
			Object result=returnType.newInstance();
			returnType.getMethod("setStatus", long.class).invoke(result, session.status);
			return result;
		}else{
			return point.proceed();
		}
	}
}
