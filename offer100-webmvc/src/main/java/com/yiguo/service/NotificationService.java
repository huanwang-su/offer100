package com.yiguo.service;

import com.yiguo.bean.Notification;

import java.util.List;

public interface NotificationService extends BaseService<Integer, Notification> {

    List<Notification> getAll();

}
