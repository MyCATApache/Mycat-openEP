package io.mycat.ep.user.util;

import java.util.Date;

import io.mycat.ep.util.MyCatEPTokenUtil;

public class TokenGenerator {
	public static String generate(long userId,String phone,int os,Date lastLoginTime){
		return MyCatEPTokenUtil.generate(userId, phone, os, lastLoginTime);
	}
	public static void main(String[] args) {

		String token = TokenGenerator.generate(123,"13800138000",1,new Date());
		System.out.println(token);
	}
}
