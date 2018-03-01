package com.yiguo.dao;

import com.yiguo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by will on 2017-06-22.
 */
@Repository
@Mapper
public interface MailMapper extends BaseMapper<Integer, User> {

}
