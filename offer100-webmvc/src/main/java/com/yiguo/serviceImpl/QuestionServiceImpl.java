package com.yiguo.serviceImpl;

import com.yiguo.bean.Question;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.QuestionMapper;
import com.yiguo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Question")
@Transactional
public class QuestionServiceImpl extends AbstractBaseServiceImpl<Integer, Question> implements QuestionService {
	@Autowired
	QuestionMapper dao;

	@Override
	public BaseMapper<Integer, Question> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
