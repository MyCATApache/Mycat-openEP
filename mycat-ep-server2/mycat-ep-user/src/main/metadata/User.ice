[["java:package:io.mycat.ep.v1"]]
module user {
	struct ClientInfo{
		string mac;
		string imei;	
		string openUdid;
		string idfa;
		int os;
		string osVersion;
		string manufacture;
		long platformCode;
		string signature;
		string packageName;
		string phone;
		string verifyCode;
		string password;
		string realname;
	};
	struct UserCommonResult{
		int status;
	};
	struct UserSession{
		int status=1;
		string token;
		long userId;
	};
	interface UserHandler{
		UserSession regist(ClientInfo info);
		UserCommonResult sendSMSCode(string phone);
		UserCommonResult changePassword(long userId, string smsCode,string newPassword);
		UserSession login(ClientInfo info);
	};
	module session{
		interface UserSessionHandler{
			UserSession checkLoginStatus(long userId,string token); 
		};
	};
};
