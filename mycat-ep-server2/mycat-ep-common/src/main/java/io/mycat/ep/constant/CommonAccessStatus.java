package io.mycat.ep.constant;

public class CommonAccessStatus {
	public static final int SUCCESS=1;
	//===========用户登录相关====================
	public static final int UNLOGGED = -(MyCatEpModuleCode.COMMON+0101);
	public static final int INCORRECT_TOKEN = -(MyCatEpModuleCode.COMMON+0102);
}
