package com.yiguo.dao;

import com.yiguo.bean.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NotificationMapper extends BaseMapper<Integer, Notification> {

}