package io.mycat.ep.user.util;

import java.util.Date;

import io.mycat.ep.util.MyCatEPTokenUtil;

public class TokenGenerator {
	public static String generate(long userId,String phone,int os,Date lastLoginTime){
		return MyCatEPTokenUtil.generate(userId, phone, os, lastLoginTime);
	}
	public static void main(String[] args) {
		System.out.println(Integer.toString(86400,36));
	}
}
