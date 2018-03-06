package com.yiguo.mvc.controller;

import com.yiguo.bean.Job;
import com.yiguo.bean.Page;
import com.yiguo.bean.Resume;
import com.yiguo.service.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(value = "API - ResumeController", description = "简历详情")
@RequestMapping("/resume")
public class ResumeController {
@Autowired
    ResumeService resumeService;
    @ApiOperation(value = "jobid",notes = "企业管理自己的简历")
    @ResponseBody
    @RequestMapping(value = "/{id}", method ={RequestMethod.GET})
    public Resume selectResume(@PathVariable Integer id) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递

        return    resumeService.selectByPrimaryKey(id);
    }
}
