package com.yiguo.mvc.controller;

import com.yiguo.bean.Job;
import com.yiguo.bean.Page;
import com.yiguo.bean.Receive;
import com.yiguo.bean.Receive;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.JobService;
import com.yiguo.service.ReceiveService;
import com.yiguo.service.ReceiveService;
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
@Api(value = "收藏接口")
@RequestMapping(value = "/Receive")
public class ReceiveController {
    @Autowired
    ReceiveService receiveService;
    @Autowired
    JobService jobService;
    @ApiOperation(value = "创建收藏", notes = "根据Receive对象创建Receive")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String buildReceive(@RequestBody Receive receive) {
        // 处理"/Receives/"的POST请求，用来创建Receive
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f = "false";
        Integer count = receiveService.selectByIds(receive);
        if (count == 0) {
            receiveService.insert(receive);
            f = "true";
        }
        return f;
    }

    @ApiOperation(value = "获取收藏信息列表", notes = "根据筛选条件获取收藏详细信息")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public PageInfo<Job> getReceive(@ModelAttribute Receive receive, @RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        // 处理"/Receives/{id}"的GET请求，用来获取url中id值的Receive信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        Page page = new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        PageInfo<Job> pageInfo = new PageInfo<Job>();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNumber);
        List<Receive> receives =receiveService.select(receive,null);
        List<Job> jobs=new ArrayList<Job>();
        pageInfo.setRows(jobs);
        for(int i=0;i<receives.size();i++)
            jobs.add( jobService.selectByPrimaryKey(receives.get(i).getJobId()));
        pageInfo.setTotal(receives.size());
        return pageInfo;

    }

    /*@ApiOperation(value="更新收藏详细信息", notes="根据url的id来指定更新对象，并根据传过来的Receive信息来更新收藏详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putReceive(@PathVariable Integer id, @RequestBody Receive receive) {
        // 处理"/Receives/{id}"的PUT请求，用来更新Receive信息
        if (receiveService.findById(id) > 0) {
            receive.setId(id);
            int num = receiveService.updateByPrimaryKeySelective(receive);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";
    }*/

    @ApiOperation(value = "删除收藏信息", notes = "根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{userid}/{jobid}", method = RequestMethod.DELETE)
    public String deleteReceive(@PathVariable Integer userid,@PathVariable Integer jobid) {
        // 处理"/Receives/{id}"的DELETE请求，用来删除Receive
        Receive receive=new Receive();
        receive.setUserId(userid);
        receive.setJobId(jobid);
       List<Receive> receives=receiveService.select(receive,null);
       int id=receives.get(0).getId();
       receiveService.deleteByPrimaryKey(id);
        String f = "true";
        if (receiveService.selectByPrimaryKey(id) != null)
            f = "false";
        return f;
    }

}
