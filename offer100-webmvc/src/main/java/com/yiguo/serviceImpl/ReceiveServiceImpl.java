package com.yiguo.serviceImpl;

import com.yiguo.bean.Receive;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.ReceiveMapper;
import com.yiguo.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Collections")
@Transactional
public class ReceiveServiceImpl extends AbstractBaseServiceImpl<Integer,Receive> implements ReceiveService {

	@Autowired
	ReceiveMapper dao;




	@Override
	public BaseMapper<Integer, Receive> getDao() {
		return dao;
	}

	@Override
	public int selectByIds(Receive receive) {
		return dao.selectByIds(receive);
	}
}
