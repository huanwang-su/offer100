package com.yiguo.service;

import com.yiguo.bean.User;

import java.util.HashMap;
import java.util.List;

public interface UserService extends BaseService<Integer, User> {
	User getuserinfo(Integer id);

	void resetpassword(Integer id);

	void changepassword(Integer id, String password);

	void updatecreorbounds(User user);

	HashMap<String, Object> getuserdetail(Integer id);

	List<User> query(User User);
}
