package com.yiguo.service;

import com.yiguo.bean.Industry;
import com.yiguo.mvc.vo.IndustryVO;

import java.util.List;

public interface IndustryService extends BaseService<Integer, Industry> {
    
    List<Industry> getByParentId(Integer id);

    IndustryVO getAllIndustry(Integer id);

    List<Industry> getIndustryToRoot(Integer id);
}
