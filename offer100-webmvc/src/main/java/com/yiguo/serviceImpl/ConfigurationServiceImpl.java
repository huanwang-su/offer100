package com.yiguo.serviceImpl;

import com.yiguo.bean.Configuration;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.ConfigurationMapper;
import com.yiguo.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Configuration")
@Transactional
public class ConfigurationServiceImpl extends AbstractBaseServiceImpl<Integer, Configuration> implements ConfigurationService {
    @Autowired
	private ConfigurationMapper dao;

	@Override
	public BaseMapper<Integer, Configuration > getDao() {
		return dao;
	}

}
