package com.yiguo.serviceImpl;

import com.yiguo.bean.Zone;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.ZoneMapper;
import com.yiguo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Zone")
@Transactional
public class ZoneServiceImpl extends AbstractBaseServiceImpl<Integer, Zone> implements ZoneService {
	@Autowired
	ZoneMapper dao;

@Override
public BaseMapper<Integer, Zone> getDao() {
		// TODO Auto-generated method stub
		return dao;
		}

		}
