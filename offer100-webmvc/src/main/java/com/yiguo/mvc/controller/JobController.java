package com.yiguo.mvc.controller;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Job;
import com.yiguo.bean.Page;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.EnterpriseService;
import com.yiguo.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;

@Controller
@Api(value = "岗位接口")
@RequestMapping("/job")
public class JobController {
	@Autowired
    JobService jobService;
	@Autowired
    EnterpriseService enterpriseService;


    @ApiOperation(value = "创建岗位信息",notes = "根据Job对象创建Job")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postJob(@RequestBody Job job) {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f=FAILURE;
        Enterprise enterprise= enterpriseService.selectByPrimaryKey(job.getEnterpriseId());
        if(enterprise!=null) {
            Integer count = jobService.selectByIds(job);
            if (count == 0) {
                jobService.insert(job);
                return SUCCESS;
            }
            else{
                f="this id have exist";
                return f;
            }
        }
        else {
            f = "you don't have id";
        return f;
        }

    }

    @ApiOperation(value="查询岗位信息", notes="根据url的id来获取岗位详细信息")
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
    public String updateJob(@PathVariable Integer id,@RequestBody Job job) {
        /* 处理"/Zones/{id}"的PUT请求，用来更新Zone信息 */
        if (jobService.findById(id) > 0) {
            job.setId(id);
            int num =jobService.updateByPrimaryKeySelective(job);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";
    }
    @ApiOperation(value="删除岗位信息", notes="根据url的id来指定删除对象")
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
    @ApiOperation(value = "筛选岗位信息",notes = "查询得出岗位，加入了分页")
    @ResponseBody
    @RequestMapping(value = "/selectJob", method = RequestMethod.GET)
    public PageInfo<Job> selectJob(@ModelAttribute Job job,@RequestParam Integer pageSize,@RequestParam Integer pageNumber) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递=
        job =job==null?new Job():job;
        PageInfo<Job> pageinfo=new PageInfo<Job>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        pageinfo.setRows( jobService.select(job,page));
        int count=jobService.selectCount(job);
        pageinfo.setTotal(count);
        return  pageinfo;
    }

}
