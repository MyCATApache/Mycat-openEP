package io.mycat.ep.user.util;

import io.mycat.ep.user.constant.UserRedisKeyPrefix;
import me.jor.redis.JedisConnection;

public class UserRedisKeyGenerator {
	public static String generateSmsCodeKey(JedisConnection redis,String phone){
		return redis.generateKey(UserRedisKeyPrefix.SMS_CODE,phone);
	}

	public static String generateUserSessionKey(JedisConnection redis, String userId) {
		return redis.generateKey(UserRedisKeyPrefix.SESSION,userId);
	}

	public static String generateUserSessionKey(JedisConnection redis, long userId) {
		return redis.generateKey(UserRedisKeyPrefix.SESSION,userId);
	}
}
