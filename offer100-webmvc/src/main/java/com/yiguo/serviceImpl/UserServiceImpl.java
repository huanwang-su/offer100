package com.yiguo.serviceImpl;

import com.yiguo.bean.User;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.UserMapper;
import com.yiguo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("User")
@Transactional
public class UserServiceImpl extends AbstractBaseServiceImpl<Integer, User> implements UserService {

@Autowired
	UserMapper dao;

	@Override
	public User getuserinfo(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectUserbyId(id);
	}




	@Override
	public void resetpassword(Integer id) {
		// TODO Auto-generated method stub
		User usr = new User();
		usr.setId(id);
		dao.resetpassword(usr);

	}

	@Override
	public void changepassword(Integer id, String password) {
		// TODO Auto-generated method stub
		User usr = new User();
		usr.setId(id);
		usr.setPassword(password);
		dao.changepassword(usr);
	}

	@Override
	public void updatecreorbounds(User user) {
		// TODO Auto-generated method stub
		dao.updatecreorbounds(user);
	}

	@Override
	public HashMap<String, Object> getuserdetail(Integer id) {
		HashMap<String, Object> userlist = new HashMap<String, Object>();
		User userinfo = dao.selectUserbyId(id);

		userlist.put("user", userinfo);
		return userlist;
	}

	@Override
	public List<User> query() {
		return dao.query();
	}

	@Override
	public BaseMapper<Integer, User> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public int findById(Integer id) {
		return dao.findById(id);
	}

}
