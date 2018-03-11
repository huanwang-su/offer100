package com.yiguo.mvc.controller;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Job;
import com.yiguo.bean.Page;
import com.yiguo.bean.Resume_post_record;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.EnterpriseService;
import com.yiguo.service.JobService;
import com.yiguo.service.Resume_post_recordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "API - Resume_post_recordController", description = "简历投递详情")
@RequestMapping("/resume_post_record")

public class Resume_post_recordController {
    @Autowired
    Resume_post_recordService resume_post_recordService;
    @Autowired
    JobService jobService;
    @Autowired
    EnterpriseService enterpriseService;
    @ApiOperation(value = "jobid",notes = "企业管理自己的岗位")
    @ResponseBody
    @RequestMapping(value = "/manageJob/{id}", method ={RequestMethod.GET})
    public List<Job> manageJob(@PathVariable Integer id) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        String f="false";
    Page page=new Page();
    page.setPageSize(10);
    Job job=new Job();
    job.setEnterpriseId(id);
     List<Job> jobs= jobService.select(job,page);

     return jobs;
    }

    @ApiOperation(value = "resumeid",notes = "企业管理自己的简历")
    @ResponseBody
    @RequestMapping(value = "/maageEnterpriseResume/{id}", method ={RequestMethod.GET})
    public List<Resume_post_record> manageEnterpriseResume(@PathVariable Integer id) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递

        Page page=new Page();
        page.setPageSize(10);
        Resume_post_record resume_post_record=new Resume_post_record();
        resume_post_record.setJobId(id);
        List<Resume_post_record> resume_post_records= resume_post_recordService.select(resume_post_record,page);

        return resume_post_records;
    }

    @ApiOperation(value = "resumeid",notes = "公司查看投递情况")
    @ResponseBody
    @RequestMapping(value = "/manageResume", method ={RequestMethod.GET})
    public PageInfo<Map> manageResume(@RequestParam(required = false) String title, @RequestParam(required = false) Integer enterpriseId, @RequestParam(required = false) Integer state, @RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        PageInfo<Map> pageinfo=new PageInfo<Map>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        pageinfo.setRows( resume_post_recordService.selectBy(title,enterpriseId,state));
        int count =resume_post_recordService.selectCounts(title,enterpriseId,state);
        pageinfo.setTotal(count);
        return pageinfo;
    }
}
