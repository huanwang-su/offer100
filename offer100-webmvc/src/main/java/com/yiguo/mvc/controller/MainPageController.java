package com.yiguo.mvc.controller;

import com.yiguo.bean.Configuration;
import com.yiguo.mvc.vo.EnterpriseVO;
import com.yiguo.mvc.vo.JobVO;
import com.yiguo.service.ConfigurationService;
import com.yiguo.service.MainPageService;
import com.yiguo.utils.UtilJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Api(value = "首页接口")
@RequestMapping("/mainpage")
@Controller
public class MainPageController {
	@Autowired
	private MainPageService mainpageService;
	@Autowired
	private ConfigurationService configurationService;

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

	@ApiOperation(value = "获取广告图片", produces = "application/json")
	@ResponseBody
	@RequestMapping(value = "/getAdImage", method = { RequestMethod.GET })
	@Cacheable(cacheNames = "offer100", keyGenerator = "cacheKeyGenerator")
	public Map getAdImage(@RequestParam String location) throws IOException {
		Configuration conf = configurationService.selectByType("adImage_"+location);
		System.out.println(new Date()+"========================");
		if(conf==null)
			return new HashMap<>();
		return UtilJson.readValue(conf.getValue(),Map.class);
	}

}
