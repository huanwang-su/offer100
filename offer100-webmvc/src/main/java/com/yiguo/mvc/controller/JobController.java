package com.yiguo.mvc.controller;

import com.yiguo.bean.*;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.EnterpriseService;
import com.yiguo.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "API - JobController", description = "企业岗位详情")
@RequestMapping("/job")
public class JobController {
	@Autowired
    JobService jobService;
	@Autowired
    EnterpriseService enterpriseService;
    @ApiOperation(value = "创建岗位",notes = "根据Job对象创建Job")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postJob(@RequestBody Job job) {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="false";
        Enterprise enterprise= enterpriseService.selectByPrimaryKey(job.getEnterpriseId());
        if(enterprise!=null) {
            org.springframework.beans.BeanUtils.copyProperties(enterprise, job);
            Integer count = jobService.selectByIds(job);
            if (count == 0) {
                jobService.insert(job);
            }
            else{
                f="发布岗位失败，已经存在该岗位，不需要重新发布";
            }
        }
        else
            f="您未注册，请先注册";
        return f;
    }
    @ApiOperation(value="获取岗位详细信息", notes="根据url的id来获取岗位详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method ={RequestMethod.GET})
    public Job getJob(@PathVariable Integer id ) {
        // 处理"/Zones/{id}"的GET请求，用来获取url中id值的Zone信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return jobService.selectByPrimaryKey(id);
    }
    @ApiOperation(value="更新job" + "详细信息", notes="根据url的id来指定更新对象，并根据传过来的Job信息来更新岗位详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putJob(@PathVariable Integer id, @RequestBody Job job) {
        /* 处理"/Zones/{id}"的PUT请求，用来更新Zone信息 */
        String f="修改成功";
       Job job1=new Job();
        jobService.updateByPrimaryKeySelective(job);
        job1=jobService.selectByPrimaryKey(id);
        if(job.equals(job1))
            f="未修改成功";
        return f;
    }
    @ApiOperation(value="删除岗位", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteJob(@PathVariable Integer id) {
        // 处理"/Zones/{id}"的DELETE请求，用来删除Zone
        String f="删除成功";
        jobService.deleteByPrimaryKey(id);
        if(jobService.selectByPrimaryKey(id)!=null)
            f="删除失败";
        return f;
    }
    @ApiOperation(value="筛选岗位", notes="筛选岗位")
    @ResponseBody
    @RequestMapping(value = "/selectJob/{id}", method = RequestMethod.DELETE)
    public PageInfo<Job> selectJob(@ModelAttribute Job job,@PathVariable Integer pageSize,Integer pageNumber) {
        // 处理"/Zones/{id}"的DELETE请求，用来删除Zone
             PageInfo<Job> pageinfo=new PageInfo<Job>();
             pageinfo.setPageNum(pageNumber);
             pageinfo.setPageSize(pageSize);
             Page page= new Page();
             page.setPageNumber(pageNumber);
             page.setPageSize(pageSize);
             pageinfo.setRows( jobService.select(job,page));
             pageinfo.setTotal(page.getTotal());
        return  pageinfo;
    }
}
