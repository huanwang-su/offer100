package com.yiguo.dao;

import com.yiguo.bean.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * user dao接口
 *
 * @author liyue
 * @date 2018-01-19
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<Integer, User> {
	User selectById(Integer Id);

	User selectUserbyId(Integer id);

	void resetpassword(User user);

	void changepassword(User user);

	void updatecreorbounds(User user);
	User findByUsername(String username);
	List<String> selectidbytea(Integer id);

	List<User> query();

	int findById(Integer id);

}