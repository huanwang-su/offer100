package com.yiguo.dao;

import com.yiguo.bean.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotificationMapper extends BaseMapper<Integer, Notification> {
    List<Notification> query( );

    int findById(Integer id);
}