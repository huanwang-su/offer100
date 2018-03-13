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

import java.util.*;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;

@Controller
@Api(value = "教育接口")
@RequestMapping(value = "/education")
public class EducationController {
	@Autowired
	EducationService educationService;
	@ApiOperation(value = "获取个人教育经历",notes = "根据User的id来查询受教育程度")
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public List<Education> getEducationByUserId(@PathVariable Integer id) {
		// 处理"/Educations/"的POST请求，用来创建Education
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		List<Education> educations=new ArrayList<Education>();
		educations =educationService.getEducationByUserId(id);
		return  educations;
	}


	@ApiOperation(value = "创建教育",notes = "根据Education对象创建Education")
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String buildEducation(@RequestBody Education education) {
		// 处理"/Educations/"的POST请求，用来创建Education
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		String f="false";
		Integer count=  educationService.selectByIds(education.getUserId());
		if(count==0) {
			educationService.insert(education);
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
	public String updateEducation(@PathVariable Integer id,@RequestBody Education education) {
		// 处理"/Educations/{id}"的PUT请求，用来更新Education信息
		if (educationService.findById(id) > 0) {
			education.setId(id);
			int num = educationService.updateByPrimaryKeySelective(education);
			if (num > 0) {
				return SUCCESS;
			} else
				return FAILURE;
		}
		return "this id does not exist";
	}

	@ApiOperation(value="删除教育", notes="根据url的id来指定删除对象")
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteEducation(@PathVariable Integer id) {
		// 处理"/Educations/{id}"的DELETE请求，用来删除Education

		educationService.deleteByPrimaryKey(id);
		if(educationService.selectByPrimaryKey(id)!=null)
		return FAILURE;
		return SUCCESS;
	}
}
