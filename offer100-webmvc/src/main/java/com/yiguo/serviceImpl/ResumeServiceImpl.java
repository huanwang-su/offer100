package com.yiguo.serviceImpl;

import com.yiguo.bean.Page;
import com.yiguo.bean.Resume;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.ResumeMapper;
import com.yiguo.service.ResumeService;
import com.yiguo.utils.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("Resume")
@Transactional
public class ResumeServiceImpl extends AbstractBaseServiceImpl<Integer, Resume> implements ResumeService {
	@Autowired
	ResumeMapper dao;

	@Override
	public BaseMapper<Integer, Resume> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<Resume> getAll() {
		return dao.query();
	}

	@Override
	public int findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<String> selectByUserId(Resume record, Page page) {
		if (page == null)
			page = new Page();
		if ((page.getPageNumber() == null&&page.getStart()==null) || page.getPageSize() == null || page.getPageSize() >= 500) {
			page.setPageNumber(1);
			page.setPageSize(10);
		}
		return dao.selectByUserId(ReflectUtil.generalMap(record, page));
	}


}
