package com.yiguo.mvc.controller;

import com.yiguo.mvc.vo.EnterpriseVO;
import com.yiguo.mvc.vo.JobVO;
import com.yiguo.service.MainPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Api(value = "API - MainPageController", description = "首页API详情")
@RequestMapping("/mainpage")
@Controller
public class MainPageController {
	@Autowired
	private MainPageService mainpageService;

	@Cacheable(cacheNames = "offer100", keyGenerator = "cacheKeyGenerator")
	@ApiOperation(value = "首页获取热门公司", produces = "application/json")
	@ResponseBody
	@RequestMapping(value = "/hotEnterprise", method = { RequestMethod.GET })
	public List<EnterpriseVO> getHotEnterprise() throws IOException {
		System.out.println(new Date()+"========================");
		List<EnterpriseVO> enterprisevo=mainpageService.getHotEnterprise();
		System.out.println(new Date()+"========================");
		return enterprisevo;
	}

	@ApiOperation(value = "首页获取热门岗位", produces = "application/json")
	@ResponseBody
	@RequestMapping(value = "/hotJob", method = { RequestMethod.GET })
	@Cacheable(cacheNames = "offer100", keyGenerator = "cacheKeyGenerator")
	public List<JobVO> getHotJob() throws IOException, IllegalAccessException, InvocationTargetException {
		// TODO 获取配置的id
		List<JobVO> jobvo = mainpageService.getHotJob();
	return jobvo;
	}
}
