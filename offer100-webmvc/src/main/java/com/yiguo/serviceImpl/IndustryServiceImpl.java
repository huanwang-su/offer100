package com.yiguo.serviceImpl;

import com.yiguo.bean.Industry;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.IndustryMapper;
import com.yiguo.mvc.vo.IndustryVO;
import com.yiguo.service.IndustryService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("Idustry")
@Transactional
public class IndustryServiceImpl extends AbstractBaseServiceImpl<Integer, Industry> implements IndustryService {
    @Autowired
	IndustryMapper dao;

	@Override
	public BaseMapper<Integer, Industry> getDao() {
		return dao;
	}

    @Override
    public List<Industry> getByParentId(Integer id) {
        Industry industry = new Industry();
        industry.setParentId(id);
        return dao.query(industry);
    }

    public IndustryVO getAllIndustry(Integer id) {
        Industry industries = selectByPrimaryKey(id);
        return parseIndustrieToVo(industries);
    }

    private IndustryVO parseIndustrieToVo(Industry src) {
        IndustryVO vo = new IndustryVO();
        try {
            BeanUtils.copyProperties(vo, src);
        } catch (Exception e) {}
        List<Industry> chird = getByParentId(src.getId());
        if(chird.size()==0)
            return vo;
        List<IndustryVO> chirdVO = new ArrayList<IndustryVO>();
        chird.forEach(c -> chirdVO.add(parseIndustrieToVo(c)));
        return vo;
    }

}
