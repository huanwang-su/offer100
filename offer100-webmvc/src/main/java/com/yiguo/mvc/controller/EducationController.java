package com.yiguo.mvc.controller;

import com.yiguo.bean.Education;
import com.yiguo.service.EducationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.yiguo.service.EducationService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(value = "API - EducationController", description = "Education详情")
@RequestMapping(value = "/education")
public class EducationController {
	@Autowired
	EducationService educationService;
	// 创建线程安全的Map
	static Map<Integer, Education> Educations = Collections.synchronizedMap(new HashMap<Integer, Education>());


	@ApiOperation(value = "创建教育",notes = "根据Education对象创建Education")
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postEducation(@RequestBody Education Education) {
		// 处理"/Educations/"的POST请求，用来创建Education
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		String f="false";
		Integer count=  educationService.selectByIds(Education.getUserId());
		if(count==0) {
			educationService.insert(Education);
			f="true";
		}
		return f;
	}

	@ApiOperation(value="获取教育详细信息", notes="根据url的id来获取教育详细信息")
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Education getEducation(@PathVariable Integer id) {
		// 处理"/Educations/{id}"的GET请求，用来获取url中id值的Education信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		Education Education=new Education();
		Education=educationService.selectByPrimaryKey(id);
		return Education;
	}

	@ApiOperation(value="更新教育详细信息", notes="根据url的id来指定更新对象，并根据传过来的Education信息来更新教育详细信息")
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putEducation(@PathVariable Integer id, @ModelAttribute Education education) {
		// 处理"/Educations/{id}"的PUT请求，用来更新Education信息

		String f="true";
		educationService.updateByPrimaryKeySelective(education);
		Education education1=new Education();
         education1=educationService.selectByPrimaryKey(id);
         if(education.equals(education1))
         	f="false";
		return f;
	}

	@ApiOperation(value="删除教育", notes="根据url的id来指定删除对象")
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteEducation(@PathVariable Integer id) {
		// 处理"/Educations/{id}"的DELETE请求，用来删除Education
		String f="true";
		educationService.deleteByPrimaryKey(id);
		if(educationService.selectByPrimaryKey(id)!=null)
		f="false";
			return f;
	}
}
