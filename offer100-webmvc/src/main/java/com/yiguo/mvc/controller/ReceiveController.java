package com.yiguo.mvc.controller;

import com.yiguo.bean.Receive;
import com.yiguo.bean.Receive;
import com.yiguo.service.ReceiveService;
import com.yiguo.service.ReceiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(value = "API - ReceiveController", description = "Receive详情")
@RequestMapping(value = "/Receive")
public class ReceiveController {
    @Autowired
    ReceiveService receiveService;



    @ApiOperation(value = "创建收藏",notes = "根据Receive对象创建Receive")
  @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postReceive(@RequestBody Receive receive) {
        // 处理"/Receives/"的POST请求，用来创建Receive
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="false";
        Integer count=  receiveService.selectByIds(receive);
        if(count==0) {
            receiveService.insert(receive);
            f="true";
        }
        return f;
    }

    @ApiOperation(value="获取收藏详细信息", notes="根据url的id来获取收藏详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Receive getReceive(@PathVariable Integer id) {
        // 处理"/Receives/{id}"的GET请求，用来获取url中id值的Receive信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        Receive receive=new Receive();
        receive=receiveService.selectByPrimaryKey(id);
        return receive;
    }

    @ApiOperation(value="更新收藏详细信息", notes="根据url的id来指定更新对象，并根据传过来的Receive信息来更新收藏详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putReceive(@PathVariable Integer id, @ModelAttribute Receive receive) {
        // 处理"/Receives/{id}"的PUT请求，用来更新Receive信息
        receive.setId(id);
        String f="true";
        receiveService.updateByPrimaryKeySelective(receive);
        Receive receive1=new Receive();
        receive1=receiveService.selectByPrimaryKey(id);
        if(receive.equals(receive1))
            f="false";
        return f;
    }

    @ApiOperation(value="删除收藏", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteReceive(@PathVariable Integer id) {
        // 处理"/Receives/{id}"的DELETE请求，用来删除Receive
        receiveService.deleteByPrimaryKey(id);
        String f="true";
        if(receiveService.selectByPrimaryKey(id)!=null)
            f="false";
        return f;
    }

}
