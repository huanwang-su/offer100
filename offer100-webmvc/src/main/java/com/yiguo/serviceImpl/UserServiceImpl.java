package com.yiguo.serviceImpl;

import com.yiguo.bean.Role;
import com.yiguo.bean.User;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.RoleMapper;
import com.yiguo.dao.UserMapper;
import com.yiguo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("User")
@Transactional
public class UserServiceImpl extends AbstractBaseServiceImpl<Integer, User> implements UserService, UserDetailsService {

@Autowired
	UserMapper dao;
@Autowired
	RoleMapper roleMapper;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.findByUsername(username);
		if (user == null) {
			//避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
			return new User();
		}  //查询用户的角色信息，并返回存入user中
		List<Role> roles = roleMapper.getRoletype(user.getRoleId());

		return user;
	}
}
