package io.mycat.ep.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCatEPTokenUtil {
	public static String generate(long userId,String phone,int os,Date lastLoginTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");


		return sdf.format(lastLoginTime) +
				os +//1
				phone +//11
				userId;
	}
	public static long parseUserIdFromToken(String token){
		return Long.parseLong(token.substring(20));
	}
}
