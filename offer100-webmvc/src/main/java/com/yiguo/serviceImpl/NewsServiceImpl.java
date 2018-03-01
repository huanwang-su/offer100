package com.yiguo.serviceImpl;

import com.yiguo.bean.News;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.NewsMapper;
import com.yiguo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("News")
@Transactional
public class NewsServiceImpl extends AbstractBaseServiceImpl<Integer, News> implements NewsService {
	@Autowired
	NewsMapper dao;

	@Override
	public BaseMapper<Integer, News> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
