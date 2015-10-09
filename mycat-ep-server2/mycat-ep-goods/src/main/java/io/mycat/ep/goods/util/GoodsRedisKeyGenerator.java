package io.mycat.ep.goods.util;

import io.mycat.ep.goods.constant.GoodsRedisKeyPrefix;
import me.jor.redis.JedisConnection;

public class GoodsRedisKeyGenerator {

	public static String generateGoodsKey(JedisConnection redis, long goodsId) {
		return redis.generateKey(redis,GoodsRedisKeyPrefix.GOODS,goodsId);
	}

}
