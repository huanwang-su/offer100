package com.yiguo.serviceImpl;

import com.yiguo.bean.Answer;
import com.yiguo.dao.AnswerMapper;
import com.yiguo.dao.BaseMapper;
import com.yiguo.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Answer")
@Transactional
public class AnswerServiceImpl extends AbstractBaseServiceImpl<Integer, Answer> implements AnswerService{
    @Autowired
	private AnswerMapper dao;


	@Override
	public BaseMapper<Integer, Answer> getDao() {
		return dao;
	}
}
