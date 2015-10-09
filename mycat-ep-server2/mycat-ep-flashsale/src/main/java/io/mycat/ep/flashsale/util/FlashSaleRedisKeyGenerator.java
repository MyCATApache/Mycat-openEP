package io.mycat.ep.flashsale.util;

import io.mycat.ep.flashsale.constant.FlashSaleRedisKeyPrefix;
import me.jor.redis.JedisConnection;

public class FlashSaleRedisKeyGenerator {

	public static String generateFlashSaleQueue(JedisConnection redis,long goodsId) {
		return redis.generateKey(FlashSaleRedisKeyPrefix.FLASH_SALES_Q,goodsId);
	}

}
