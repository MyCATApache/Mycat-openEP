package io.mycat.ep.common.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jor.common.GlobalObject;

public class LogAop {
	private static final Logger log=LoggerFactory.getLogger(LogAop.class);
	
	private Map<String,Object> populateLog(ProceedingJoinPoint point,Object result){
		Map<String,Object> logMap=new HashMap<String,Object>();
		Object[] args=point.getArgs();
		if(args!=null && args.length>1){
			logMap.put("params", args[0]);
		}
		if(result!=null){
			logMap.put("result", result);
		}
		logMap.put("handler",point.getTarget().getClass()+"."+point.getSignature().getName());
		return logMap;
	}
	private void log(ProceedingJoinPoint point,Object result,long start){
		Map<String,Object> logMap=null;
		try{
			logMap=populateLog(point,result);
			logMap.put("consumed", System.currentTimeMillis()-start);
			log.info(GlobalObject.getJsonMapper().writeValueAsString(logMap));
		}catch(Exception e){
			log.error("errorOnLog:"+logMap,e);
		}
	}
	private void log(ProceedingJoinPoint point,Throwable e){
		Map<String,Object> logMap=null;
		try {
			logMap=populateLog(point,null);
			log.info(GlobalObject.getJsonMapper().writeValueAsString(logMap),e);
		} catch (Exception e1) {
			log.error("errorOnLog:"+logMap,e);
		}
	}
	public Object log(ProceedingJoinPoint point) throws Throwable {
		try{
			long start=System.currentTimeMillis();
			Object result=point.proceed();
			log(point,result,start);
			return result;
		}catch(Exception e){
			log(point,e);
			throw e;
		}
	}
}
