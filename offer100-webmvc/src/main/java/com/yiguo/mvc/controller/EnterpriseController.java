package com.yiguo.mvc.controller;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Job;
import com.yiguo.bean.Page;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;

@Controller
@Api(value = "API - EnterpriseController", description = "企业详情")
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;
    @ApiOperation(value = "企业用户登录",notes = "")
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

    @ApiOperation(value = "创建企业",notes = "根据Enterprise对象创建Enterprise")
    @ResponseBody
    @RequestMapping(value = "/admin/enterprise", method = RequestMethod.POST)
    public String postEnterprise(@RequestBody Enterprise  enterprise) {
        // 处理"/Zones/"的POST请求，用来创建Zone
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="注册成功";
            Integer count = enterpriseService.selectByName(enterprise.getName());
            if (count == 0) {
              enterpriseService.insert(enterprise);
            }
            else{
                f="注册失败，未通过审核";
            }

        return f;
    }
    @ApiOperation(value="获取企业详细信息", notes="根据url的id来获取企业详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method ={RequestMethod.GET})
    public Enterprise getEnterprise(@PathVariable Integer id ) {
        // 处理"/Zones/{id}"的GET请求，用来获取url中id值的Zone信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return enterpriseService.selectByPrimaryKey(id);
    }
    @ApiOperation(value="更新Enterprise" + "详细信息", notes="根据url的id来指定更新对象，并根据传过来的Enterprise信息来更新企业详细信息")
    @ResponseBody
    @RequestMapping(value = "/putEnterprise", method = RequestMethod.PUT)
    public String putEnterprise(@ModelAttribute Enterprise enterprise) {
        // 处理"/Zones/{id}"的PUT请求，用来更新Zone信息
        /*String f="修改成功";
        Enterprise enterprise1=new Enterprise();
        enterpriseService.updateByPrimaryKeySelective(enterprise);
        enterprise1=enterpriseService.selectByPrimaryKey(enterprise.getId());
        if(enterprise.equals(enterprise1))
            f="未修改成功";
        return f;*/


        if (enterpriseService.findById(enterprise.getId()) > 0) {
            int num = enterpriseService.updateByPrimaryKeySelective(enterprise);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";


    }
    @ApiOperation(value="删除企业", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteJob(@PathVariable Integer id) {
        // 处理"/Zones/{id}"的DELETE请求，用来删除Zone
        String f="删除成功";
        enterpriseService.deleteByPrimaryKey(id);
        if(enterpriseService.selectByPrimaryKey(id)!=null)
            f="删除失败";
        return f;
    }

    @ApiOperation(value = "审核企业",notes = "审核企业的资质")
    @ResponseBody
    @RequestMapping(value = "/checkEnterprise/{id}/{flag}", method = RequestMethod.GET)
    public String checkEnterprise(@PathVariable Integer id,@PathVariable Integer flag) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        String f = "审核成功";
        if(flag==0) {
            Enterprise enterprise1 = new Enterprise();
            Enterprise enterprise = enterpriseService.selectByPrimaryKey(id);
            enterprise.setState(1);
            enterpriseService.updateByPrimaryKeySelective(enterprise);
        }
        else{
            f="审核未通过";
        }
        return f;
    }
    @ApiOperation(value = "查询企业",notes = "查询得出企业")
    @ResponseBody
    @RequestMapping(value = "/selectEnterprise", method = RequestMethod.GET)
    public PageInfo<Enterprise> selectEnterprise(@RequestBody(required = false) Enterprise enterprise,@RequestParam Integer pageSize,@RequestParam Integer pageNumber) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递=
        enterprise = enterprise==null?new Enterprise():enterprise;
        PageInfo<Enterprise> pageinfo=new PageInfo<Enterprise>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        enterprise.setStage(1);
        pageinfo.setRows( enterpriseService.select(enterprise,page));
        int count=enterpriseService.selectCount(enterprise);
        pageinfo.setTotal(count);
        return  pageinfo;
    }

}
