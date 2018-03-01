package com.yiguo.serviceImpl;

import com.yiguo.bean.Major;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.MajorMapper;
import com.yiguo.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Major")
@Transactional
public class MajorServiceImpl extends AbstractBaseServiceImpl<Integer, Major> implements MajorService {
	@Autowired
	MajorMapper dao;

	@Override
	public BaseMapper<Integer, Major> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
