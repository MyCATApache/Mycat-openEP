package io.mycat.ep.constant;

public class MyCatEPCommonConstant {
	public static int getCommonRedisKeyLife(){
		return MyCatEPConstant.getInstance().getIntValue("mycat.ep.common.redis.key.life",3600);
	}
}
