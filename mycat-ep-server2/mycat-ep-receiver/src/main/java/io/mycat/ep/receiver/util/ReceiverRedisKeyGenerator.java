package io.mycat.ep.receiver.util;

import io.mycat.ep.receiver.constant.ReceiverRedisKeyPrefix;
import me.jor.redis.JedisConnection;

public class ReceiverRedisKeyGenerator {

	public static String generatePurchaseReceiverKey(JedisConnection redis, long userId) {
		return redis.generateKey(ReceiverRedisKeyPrefix.PURCHASE_RECEIVER,userId);
	}

}
