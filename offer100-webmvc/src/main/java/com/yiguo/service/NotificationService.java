package com.yiguo.service;

import com.yiguo.bean.Notification;

import java.util.List;

public interface NotificationService extends BaseService<Integer, Notification> {

    List<Notification> getAll();

    //查找数据库中是否存在该id
    int findById(Integer id);

}
