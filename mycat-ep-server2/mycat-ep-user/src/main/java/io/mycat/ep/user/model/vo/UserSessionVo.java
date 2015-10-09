package io.mycat.ep.user.model.vo;

import io.mycat.ep.v1.user.UserSession;

public class UserSessionVo extends UserSession{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1007345386266981545L;

	public UserSessionVo(int status){
		super.status=status;
	}
}
