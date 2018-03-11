package com.yiguo.serviceImpl;

import com.yiguo.bean.Job;
import com.yiguo.dao.BaseMapper;
import com.yiguo.dao.JobMapper;
import com.yiguo.offer100.search.bean.SearchJob;
import com.yiguo.service.EnterpriseService;
import com.yiguo.service.IndustryService;
import com.yiguo.service.JobService;
import com.yiguo.service.ZoneService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("Job")
@Transactional
public class JobServiceImpl extends AbstractBaseServiceImpl<Integer, Job> implements JobService {
    @Autowired
    JobMapper dao;
    @Autowired
    IndustryService industryService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    ZoneService zoneService;

    @Override
    public BaseMapper<Integer, Job> getDao() {
        // TODO Auto-generated method stub
        return dao;
    }

    @Override
    public List<Job> querySearch(Integer enterpriseId) {
        // TODO Auto-generated method stub
        return dao.querySearch(enterpriseId);
    }

    @Override
    public int selectByIds(Job job) {
        return dao.selectByIds(job);
    }

    @Override
    public SearchJob toSolrJob(Job src) {
        SearchJob dest = new SearchJob();
        try {
            BeanUtils.copyProperties(dest, src);
            Optional.ofNullable(src.getIndustryId()).ifPresent(id -> Optional.ofNullable(industryService.getIndustryToRoot(id))
                    .ifPresent(industrys -> {
                        List<String> categorys = new ArrayList<>();
                        dest.setCategory(categorys);
                        industrys.forEach(indu -> categorys.add(indu.getName()));
                    }));
            Optional.ofNullable(src.getZoneId()).ifPresent(id -> Optional.ofNullable(zoneService.getLevelZone(id, 2))
                    .ifPresent(zone -> dest.setZone(zone.getName()+zone.getName().substring(0,zone.getName().length()-1))));
            Optional.ofNullable(src.getEnterpriseId()).ifPresent(id -> Optional.ofNullable(enterpriseService.selectByPrimaryKey(id))
                    .ifPresent(enterprise -> {
                        dest.setEnterprise(enterprise.getName());
                        Optional.ofNullable(enterprise.getIndustryId()).ifPresent(id2 -> Optional.ofNullable(industryService.selectByPrimaryKey(id2))
                                .ifPresent(industry -> dest.setEnterpriseCategory(industry.getName())));
                        dest.setEnterpriseLogo(enterprise.getImageLog());
                    }));
            Optional.ofNullable(src.getEffectiveTime()).ifPresent(time -> {
                Instant instant = time.toInstant();
                ZoneId zone = ZoneId.systemDefault();
                dest.setPublishTime(LocalDateTime.ofInstant(instant, zone).toLocalDate().toEpochDay());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }

}
