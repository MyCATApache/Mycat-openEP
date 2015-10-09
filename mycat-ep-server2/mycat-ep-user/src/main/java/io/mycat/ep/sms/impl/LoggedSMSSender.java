package io.mycat.ep.sms.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.mycat.ep.sms.SMSSender;

public class LoggedSMSSender implements SMSSender{

	private Logger log=LoggerFactory.getLogger(LoggedSMSSender.class);
	
	public String send(String phone, String msg) {
		log.info("sms:"+phone+";"+msg);
		return "success";
	}

	public boolean isSuccess(String sendCode) {
		return true;
	}

	public static void main(String[] args) {
		System.out.println((char)2);
	}
}
