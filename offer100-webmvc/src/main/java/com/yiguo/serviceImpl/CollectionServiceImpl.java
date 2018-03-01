package com.yiguo.serviceImpl;

import com.yiguo.bean.Collection;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.CollectionMapper;
import com.yiguo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Collections")
@Transactional
public class CollectionServiceImpl extends AbstractBaseServiceImpl<Integer, Collection> implements CollectionService {

	@Autowired
	CollectionMapper dao;

	@Override
	public BaseMapper<Integer, Collection> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
