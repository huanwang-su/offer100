package com.yiguo.mvc.controller;

import com.yiguo.bean.Enterprise;
import com.yiguo.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "创建岗位",notes = "根据Job对象创建Job")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putEnterprise(@PathVariable Integer id, @ModelAttribute Enterprise enterprise) {
        // 处理"/Zones/{id}"的PUT请求，用来更新Zone信息
        String f="修改成功";
        Enterprise enterprise1=new Enterprise();
        enterpriseService.updateByPrimaryKeySelective(enterprise);
        enterprise1=enterpriseService.selectByPrimaryKey(id);
        if(enterprise.equals(enterprise1))
            f="未修改成功";
        return f;
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
}
