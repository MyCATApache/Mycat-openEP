package io.mycat.ep.v1.user.session.impl;

import java.io.IOException;

import Ice.Current;
import io.mycat.ep.constant.CommonAccessStatus;
import io.mycat.ep.user.constant.UserConstant;
import io.mycat.ep.user.dao.UserDAO;
import io.mycat.ep.user.model.LoginSession;
import io.mycat.ep.user.model.vo.UserSessionVo;
import io.mycat.ep.user.util.UserRedisKeyGenerator;
import io.mycat.ep.v1.user.UserSession;
import io.mycat.ep.v1.user.session._UserSessionHandlerDisp;
import me.jor.common.GlobalObject;
import me.jor.redis.JedisConnection;
import me.jor.util.Help;

public class UserSessionHandlerImpl extends _UserSessionHandlerDisp{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2877252048993741736L;

	private JedisConnection redis;
	private UserDAO userDAO;
	
	public JedisConnection getRedis() {
		return redis;
	}

	public void setRedis(JedisConnection redis) {
		this.redis = redis;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	private LoginSession findLoginSession(long userId){
		try{
			String key=UserRedisKeyGenerator.generateUserSessionKey(redis, userId);
			String json=redis.get(key);
			if(Help.isEmpty(json)){
				LoginSession session=userDAO.findLoginSession(userId);
				redis.setex(key,UserConstant.getUserSessionLife(),GlobalObject.getJsonMapper().writeValueAsString(session));
				return session;
			}else{
				return GlobalObject.getJsonMapper().readValue(json, LoginSession.class);
			}
		}catch(IOException e){
			return null;
		}
	}
	
	public UserSession checkLoginStatus(long userId,String token, Current __current) {
		LoginSession session=findLoginSession(userId);
		if(session==null){
			new UserSessionVo(CommonAccessStatus.UNLOGGED);
		}
		
		String t=session.getToken();
		if(t.equals(token)){
			return new UserSessionVo(1);
		}else{
			return new UserSessionVo(CommonAccessStatus.INCORRECT_TOKEN);
		}
	}

}
