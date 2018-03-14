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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;

@Controller
@Api(value = "企业接口")
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    JobService jobService;
    @Autowired
    Resume_post_recordService resume_post_recordService;
    /*@ApiOperation(value = "企业用户登录",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getEnterprise(@PathVariable String userName,@PathVariable String userPassword) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        String  f="登录成功";
        Enterprise enterprise=enterpriseService.selectByIds(userName,userPassword);
        if(enterprise==null)
            f="登录失败";
        return f;
    }
*/
    @ApiOperation(value = "获取企业列表",notes = "企业管理自己的岗位")
    @ResponseBody
    @RequestMapping(value = "/manageEnterpriseJob/{id}", method ={RequestMethod.GET})
    public PageInfo<Job> manageEnterpriseJob(@RequestParam Integer id,@RequestParam Integer pageSize,@RequestParam Integer pageNumber) {


        Job job=new Job();
        job.setEnterpriseId(id);
        PageInfo<Job> pageinfo=new PageInfo<Job>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        pageinfo.setRows( jobService.select(job,page));
        pageinfo.setTotal(jobService.selectCount(job));
        return pageinfo;
    }
    @ApiOperation(value = "获取投递简历(jobId)",notes = "企业管理自己的简历")
    @ResponseBody
    @RequestMapping(value = "/manageEnterpriseResume", method ={RequestMethod.GET})
    public PageInfo<Resume_post_record> manageEnterpriseResume(@RequestParam Integer id,@RequestParam Integer state, @RequestParam Integer pageSize, @RequestParam Integer pageNumber) {


        Resume_post_record resume_post_record=new Resume_post_record();
        resume_post_record.setJobId(id);
        resume_post_record.setState(state.byteValue());
        PageInfo<Resume_post_record> pageinfo=new PageInfo<Resume_post_record>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        pageinfo.setRows( resume_post_recordService.select(resume_post_record,page));
        pageinfo.setTotal(resume_post_recordService.selectCount(resume_post_record));
        return pageinfo;
    }
    @ApiOperation(value = "创建企业信息",notes = "根据Enterprise对象创建Enterprise")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String buildEnterprise(@RequestBody Enterprise  enterprise) {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f=SUCCESS;
            Integer count = enterpriseService.selectByName(enterprise.getName());
            if (count == 0) {
              enterpriseService.insert(enterprise);
            }
            else{
                f=FAILURE;
            }

        return f;
    }


    @ApiOperation(value="查询企业信息", notes="根据url的id来获取企业详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method ={RequestMethod.GET})
    public Object getEnterprise(@PathVariable Integer id ) {
        // 处理"/Zones/{id}"的GET请求，用来获取url中id值的Zone信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        if(enterpriseService.findById(id) > 0) {

            return enterpriseService.selectByPrimaryKey(id);
        }

        else
            return "this id does not exist";
    }


    @ApiOperation(value="更新企业信息", notes="根据url的id来指定更新对象，并根据传过来的Enterprise信息来更新企业详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateEnterprise(@PathVariable Integer id,@RequestBody Enterprise enterprise) {

        if (enterpriseService.findById(id) > 0) {
            enterprise.setId(id);
            int num = enterpriseService.updateByPrimaryKeySelective(enterprise);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";


    }
    @ApiOperation(value="删除企业信息", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEnterprise(@PathVariable Integer id) {
        // 处理"/Zones/{id}"的DELETE请求，用来删除Zone
        String f=SUCCESS;
        enterpriseService.deleteByPrimaryKey(id);
        if(enterpriseService.selectByPrimaryKey(id)!=null)
            f=FAILURE;
        return f;
    }

    @ApiOperation(value = "审核企业信息",notes = "审核企业的资质")
    @ResponseBody
    @RequestMapping(value = "/{id}/{flag}", method = RequestMethod.GET)
    public String checkEnterprise(@PathVariable Integer id,@PathVariable Integer flag) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        String f = SUCCESS;
        if(flag==0) {
            Enterprise enterprise1 = new Enterprise();
            Enterprise enterprise = enterpriseService.selectByPrimaryKey(id);
            enterprise.setState(1);
            enterpriseService.updateByPrimaryKeySelective(enterprise);
        }
        else{
            f=FAILURE;
        }
        return f;
    }
    @ApiOperation(value = "筛选企业信息",notes = "查询得出企业")
    @ResponseBody
    @RequestMapping(value = "/selectEnterprise", method = RequestMethod.GET)
    public PageInfo<Enterprise> selectEnterprise(@ModelAttribute Enterprise enterprise,@RequestParam Integer pageSize,@RequestParam Integer pageNumber) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递=
        enterprise = enterprise==null?new Enterprise():enterprise;
        PageInfo<Enterprise> pageinfo=new PageInfo<Enterprise>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        //enterprise.setStage(1);
        pageinfo.setRows( enterpriseService.select(enterprise,page));
        int count=enterpriseService.selectCount(enterprise);
        pageinfo.setTotal(count);
        return  pageinfo;
    }

}
