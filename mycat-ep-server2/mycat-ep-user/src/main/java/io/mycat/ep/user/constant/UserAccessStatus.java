package io.mycat.ep.user.constant;

import io.mycat.ep.constant.MyCatEpModuleCode;

public class UserAccessStatus {
	//============用户注册相关===================
	public static final int REGISTED=   -(MyCatEpModuleCode.USER+0001);
	public static final int UNREGISTED_OR_INCORRECT_PASSWORD= -(MyCatEpModuleCode.USER+0002);
	
}
