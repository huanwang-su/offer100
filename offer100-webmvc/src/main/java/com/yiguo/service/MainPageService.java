package com.yiguo.service;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Industry;
import com.yiguo.bean.Job;
import com.yiguo.bean.Zone;
import com.yiguo.mvc.vo.EnterpriseVO;
import com.yiguo.mvc.vo.JobVO;
import com.yiguo.utils.UtilJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
@Service
public class MainPageService {
	 @Autowired
	private EnterpriseService enterpriseService;
	 @Autowired
	private IndustryService industryService;
	 @Autowired
	private ZoneService zoneService;
	 @Autowired
	private JobService jobService;
    @Autowired
	private Resume_post_recordService resume_post_recordService;
    @Autowired
	private ConfigurationService configurationService;



	public List<EnterpriseVO> getHotEnterprise() throws IOException {
		// TODO 获取配置的id
		List<Integer> enterpriseIds = UtilJson.readValue(configurationService.selectByType("mainpage_hotEnterPrice").getValue(),List.class);
		List<EnterpriseVO> enterprisevo = new ArrayList<>();
		for (int i = 0; i < enterpriseIds.size(); i++) {
			// 获取企业信息
			EnterpriseVO enterprisev = new EnterpriseVO();
			Enterprise enterprise = new Enterprise();
			enterprise = enterpriseService.selectByPrimaryKey(enterpriseIds.get(i));
			Industry industry=industryService.selectByPrimaryKey(enterprise.getIndustryId());
			if (enterprise != null) {
				// 获取岗位信息
				List<Job> job = new ArrayList<>();
				job = jobService.querySearch(enterpriseIds.get(i));
				// 统计发布岗位数量
				int jobCount = job.size();
				float resumeHanderRate = 0;
				int resumeNum = 0;
				int resumeNum1 = 0;

//				for (int j = 0; j < job.size(); j++) {
//
//					// 获取岗位id
//					int jobid = job.get(j).getId();
//					// 获取简历数量
//					resumeNum = resumeNum + resume_post_recordService.selectjob(jobid);
//					// 获取简历数量，且state为1
//					resumeNum1 = resumeNum1 + resume_post_recordService.selectjob1(jobid);
//					// 计算简历处理率
//				}
//				if (resumeNum != 0)
//					resumeHanderRate = (resumeNum - resumeNum1) / resumeNum;

				// 获取企业logo
				String imagelogo = enterprise.getImageLog();

				// 获取企业描述
				String description = enterprise.getDescription();
				// 获取企业名称
				String name = enterprise.getName();
				// 获取企业性质
				int stage1 = enterprise.getStage();
				String stage = "";
				if (stage1 == 0)
					stage = "未融资";
				else if (stage1 == 1)
					stage = "天使轮融资";
				else if (stage1 == 2)
					stage = "A轮融资";
				else if (stage1 == 3)
					stage = "B轮融资";
				else if (stage1 == 4)
					stage = "C轮融资";
				else if (stage1 == 5)
					stage = "D轮融资";
				else if (stage1 == 6)
					stage = "上市";
				enterprisev.setId(enterprise.getId());
				enterprisev.setDescription(description);
				enterprisev.setImageLog(imagelogo);
				enterprisev.setName(name);
				enterprisev.setJobCount(jobCount);
//				enterprisev.setResumeHanderRate(resumeHanderRate);
				enterprisev.setStage(stage);
                enterprisev.setIndustryName(industry.getName());
				enterprisevo.add(enterprisev);
			}
		}
		return enterprisevo;
	}


	public List<JobVO> getHotJob() throws IOException, IllegalAccessException, InvocationTargetException {
		// TODO 获取配置的id
		List<JobVO> jobVo = new ArrayList<>();
		List<Integer> jobIds = UtilJson.readValue(configurationService.selectByType("mainpage_hotjob").getValue(),List.class);
		for (int i = 0; i < jobIds.size(); i++) {
			
			JobVO jobvos = new JobVO();
			
			Job job = jobService.selectByPrimaryKey(jobIds.get(i));
			//BeanUtils.copyProperties(jobvos, job);
			org.springframework.beans.BeanUtils.copyProperties(job, jobvos);
			Enterprise enterprise = enterpriseService.selectByPrimaryKey(job.getEnterpriseId());
			jobvos.setEnterpriseName(enterprise.getName());
			Industry industry = industryService.selectByPrimaryKey(job.getIndustryId());
			jobvos.setIndustryName(industry.getName());
			Zone zone = zoneService.selectByPrimaryKey(job.getZoneId());
			jobvos.setZoneName(zone.getName());
			jobVo.add(jobvos);
		}
		return jobVo;
	}
}
