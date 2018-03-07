package com.yiguo.serviceImpl;

import com.yiguo.bean.Configuration;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.ConfigurationMapper;
import com.yiguo.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("Configuration")
@Transactional
public class ConfigurationServiceImpl extends AbstractBaseServiceImpl<Integer, Configuration> implements ConfigurationService {
    @Autowired
	private ConfigurationMapper dao;

	@Override
	public BaseMapper<Integer, Configuration > getDao() {
		return dao;
	}

	@Override
	public List<Configuration> getAll() {
		return dao.query();
	}

	@Override
	public Configuration selectByType(String type) { return dao.selectByType(type); }

	@Override
	public int deleteByType(String type) {
		return dao.deleteByType(type);
	}



	@Override
	public int FindByType(String type) { return dao.findByType(type); }


}
