package io.mycat.ep.sms;

public interface SMSSender {
	/**
	 * 
	 * @param phone  要发短信的手机号
	 * @param msg    要发的短信内容
	 * @return  发短信的状态码
	 */
	public String send(String phone,String msg);
	public boolean isSuccess(String sendCode);
}
