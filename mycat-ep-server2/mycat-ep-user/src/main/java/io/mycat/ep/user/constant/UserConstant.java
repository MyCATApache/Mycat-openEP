package io.mycat.ep.user.constant;

import io.mycat.ep.constant.MyCatEPConstant;

public class UserConstant {
	private static final MyCatEPConstant constant=MyCatEPConstant.getInstance();
	public static int getSmsCodeLength(){
		return constant.getIntValue("mycat.ep.smscode.length",6);
	}
	public static String getSmsCodeContant(){
		return constant.getValue("mycat.ep.smscode.contant");
	}
	public static String getSmsCodeRange(){
		return constant.getValue("mycat.ep.smscode.range");
	}
	public static int getSmsCodeLife() {
		return constant.getIntValue("mycat.ep.smscode.life",5*60);
	}
	public static int getUserSessionLife() {
		return constant.getIntValue("mycat.ep.session.life",86400);
	}
}
