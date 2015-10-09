package io.mycat.ep.user.dao;

import org.apache.ibatis.annotations.Param;

import io.mycat.ep.user.model.LoginSession;
import io.mycat.ep.user.model.User;

public interface UserDAO {

	Long findIdByPhone(String phone);

	void saveUser(User user);

	Long findIdByPhoneAndPassword(@Param("phone")String phone, @Param("password")String password);

	void saveLoginSession(LoginSession session);

	LoginSession findLoginSession(@Param("userId")long userId);

}
