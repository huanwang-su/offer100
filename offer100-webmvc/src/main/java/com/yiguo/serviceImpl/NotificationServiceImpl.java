package com.yiguo.serviceImpl;

import com.yiguo.bean.Notification;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.NotificationMapper;
import com.yiguo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("Notification")
@Transactional
public class NotificationServiceImpl extends AbstractBaseServiceImpl<Integer, Notification>
		implements NotificationService {
	@Autowired
	NotificationMapper dao;

	@Override
	public BaseMapper<Integer, Notification> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}


	@Override
	public List<Notification> getAll() {
		return dao.query();
	}

}
