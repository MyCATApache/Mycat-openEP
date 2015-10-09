package io.mycat.ep.v1.user.impl;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import Ice.Current;
import io.mycat.ep.sms.SMSSender;
import io.mycat.ep.user.constant.UserAccessStatus;
import io.mycat.ep.user.constant.UserConstant;
import io.mycat.ep.user.dao.UserDAO;
import io.mycat.ep.user.model.LoginSession;
import io.mycat.ep.user.model.User;
import io.mycat.ep.user.model.vo.UserSessionVo;
import io.mycat.ep.user.util.TokenGenerator;
import io.mycat.ep.user.util.UserRedisKeyGenerator;
import io.mycat.ep.util.MessageUtil;
import io.mycat.ep.v1.user.ClientInfo;
import io.mycat.ep.v1.user.UserCommonResult;
import io.mycat.ep.v1.user.UserSession;
import io.mycat.ep.v1.user._UserHandlerDisp;
import me.jor.common.CommonConstant;
import me.jor.common.GlobalObject;
import me.jor.redis.JedisConnection;
import me.jor.util.Help;
import me.jor.util.MessageDigestUtil;

public class UserHandlerImpl extends _UserHandlerDisp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2825731175957204308L;
	
	private JedisConnection redis;
	private SMSSender smsSender;
	private UserDAO userDAO;
	

	public JedisConnection getRedis() {
		return redis;
	}

	public void setRedis(JedisConnection redis) {
		this.redis = redis;
	}

	public SMSSender getSmsSender() {
		return smsSender;
	}

	public void setSmsSender(SMSSender smsSender) {
		this.smsSender = smsSender;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserSession regist(ClientInfo info, Current __current) {
		User user=Help.populate(new User(),info,false);
		Date registTime=new Date();
		user.setRegistTime(registTime);
		String phone=user.getPhone();
		user.setPassword(MessageDigestUtil.md5Base64(phone+user.getPassword(), CommonConstant.DEFAULT_CHARSET));
		Long id=userDAO.findIdByPhone(phone);
		if(id==null){
			userDAO.saveUser(user);
			return login(info);
		}else{
			return new UserSessionVo(UserAccessStatus.REGISTED);
		}
	}

	public UserCommonResult sendSMSCode(String phone, Current __current) {
		GlobalObject.getExecutorService().submit(()->{
			String smsCode=RandomStringUtils.random(UserConstant.getSmsCodeLength(),UserConstant.getSmsCodeRange());
			String msg=UserConstant.getSmsCodeContant();
			String result=smsSender.send(phone, MessageUtil.generateMessage(msg, smsCode));
			if(smsSender.isSuccess(result)){
				redis.setex(UserRedisKeyGenerator.generateSmsCodeKey(redis, phone), UserConstant.getSmsCodeLife(), smsCode);
			}
		});
		return null;
	}

	public UserCommonResult changePassword(long userId, String smsCode, String newPassword, Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserSession login(ClientInfo info, Current __current) {
		String phone=info.phone;
		String password=MessageDigestUtil.md5Base64(phone+info.password, CommonConstant.DEFAULT_CHARSET);
		Long id=userDAO.findIdByPhoneAndPassword(phone,password);
		if(id==null){
			return new UserSessionVo(UserAccessStatus.UNREGISTED_OR_INCORRECT_PASSWORD);
		}else{
			LoginSession session=Help.populate(new LoginSession(), info, false);
			session.setId(id);
			Date lastLoginTime=new Date();
			session.setLastLoginTime(lastLoginTime);
			String token=TokenGenerator.generate(id, phone, info.os, lastLoginTime);
			session.setToken(token);
			try {
				redis.setex(UserRedisKeyGenerator.generateUserSessionKey(redis, phone),UserConstant.getUserSessionLife(),
						GlobalObject.getJsonMapper().writeValueAsString(session));
				userDAO.saveLoginSession(session);
			} catch (JsonProcessingException e) {}
			return new UserSession(1,token,id);
		}
	}
}
