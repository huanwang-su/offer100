package com.yiguo.mvc.controller;

import com.yiguo.bean.School;
import com.yiguo.bean.Zone;
import com.yiguo.service.SchoolService;
import com.yiguo.service.SchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;

@Controller
@Api(value = "API - SchoolController", description = "School详情")
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;
  

    @ApiOperation(value = "创建学校",notes = "根据School对象创建School")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postSchool(@RequestBody School school) {
        // 处理"/Schools/"的POST请求，用来创建School
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="false";
        Integer count=  schoolService.selectByName(school.getName());
        if(count==0) {
            schoolService.insert(school);
            f="true";
        }
        return f;
    }

    @ApiOperation(value="获取学校详细信息", notes="根据url的id来获取学校详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public School getSchool(@PathVariable Integer id) {
        // 处理"/Schools/{id}"的GET请求，用来获取url中id值的School信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        School school=new School();
        school=schoolService.selectByPrimaryKey(id);

        return school;
    }

    @ApiOperation(value="更新学校详细信息", notes="根据url的id来指定更新对象，并根据传过来的School信息来更新学校详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putSchool(@PathVariable Integer id, @RequestBody School school) {
        // 处理"/Schools/{id}"的PUT请求，用来更新School信息
        if (schoolService.findById(id) > 0) {
            school.setId(id);
            int num = schoolService.updateByPrimaryKeySelective(school);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";
    }

    @ApiOperation(value="删除学校", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteSchool(@PathVariable Integer id) {
        // 处理"/Schools/{id}"的DELETE请求，用来删除School
        schoolService.deleteByPrimaryKey(id);
        String f="true";
        if(schoolService.selectByPrimaryKey(id)!=null)
            f="false";
        return f;
    }
}
