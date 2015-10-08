package io.mycat.ep.v1.user.session.proxy;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import io.mycat.ep.constant.CommonAccessStatus;
import io.mycat.ep.constant.MagicData;
import io.mycat.ep.exception.UserSessionCheckException;
import io.mycat.ep.ice.proxy.IceProxy;
import io.mycat.ep.util.MyCatEPTokenUtil;
import io.mycat.ep.util.ProcessInfo;
import io.mycat.ep.v1.user.UserSession;
import io.mycat.ep.v1.user.session.UserSessionHandlerPrx;
import io.mycat.ep.v1.user.session.UserSessionHandlerPrxHelper;

public class UserSessionHandlerProxy {
	public static final Cache<String, UserSession> cache=CacheBuilder.newBuilder()
			.expireAfterAccess(ProcessInfo.getUserSessionLocalCacheLife(), TimeUnit.SECONDS)
			.maximumSize(ProcessInfo.getUserSessionLocalCacheMaxSize())
			.recordStats()
			.build(new CacheLoader<String,UserSession>(){
				public UserSession load(String token){
					UserSession session=findLoginStatus(token);
					if(session.status>=CommonAccessStatus.SUCCESS){
						return session;
					}
					return null;
				}
			});
	private static UserSessionHandlerPrx prx;
	
	public static UserSessionHandlerPrx init(String... args){
		return init(System.getProperty(MagicData.APP_ICE_USER_SESSION_SERVICE),args);
	}
	public static UserSessionHandlerPrx init(String userSessionService,String... args){
		if(prx==null){
			synchronized(UserSessionHandlerProxy.class){
				if(prx==null){
					prx=IceProxy.init(UserSessionHandlerPrxHelper.class, userSessionService, args);
				}
			}
		}
		return prx;
	}
	private static UserSession findLoginStatus(String token){
		return findLoginStatus(MyCatEPTokenUtil.parseUserIdFromToken(token),token);
	}
	private static UserSession findLoginStatus(long userId,String token){
		return init().checkLoginStatus(userId,token);
	}
	public static UserSession check(long userId,String token){
		try {
			return cache.get(token,()->{
				return findLoginStatus(userId,token);
			});
		} catch (ExecutionException e) {
			throw new UserSessionCheckException(e);
		}
	}
}
