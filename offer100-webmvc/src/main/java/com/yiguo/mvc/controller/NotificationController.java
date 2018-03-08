package com.yiguo.mvc.controller;

import com.yiguo.bean.Notification;
import com.yiguo.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;

@RestController
@Api(value = "API - NotificationController", description = "Notification详情")
@RequestMapping(value="/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;
    @ApiOperation(value = "获取通知列表",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Notification> getNotificationList() {

        List<Notification> r = notificationService.getAll();
        return r;
    }

    @ApiOperation(value = "创建通知信息",notes = "根据notification对象创建notification")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postNotification(@RequestBody Notification notification) {
        String f=FAILURE;
        Integer count=  notificationService.insert(notification);
        if(count > 0) {

            f=SUCCESS;
        }
        //System.out.println(f);
        return f;
    }


    @ApiOperation(value="根据id获取通知详细信息", notes="根据url的id来获取通知详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Notification getNotification(@PathVariable Integer id) {

            Notification notification = new Notification();
            return notificationService.selectByPrimaryKey(id);

    }

    @ApiOperation(value="更新通知详细信息", notes="根据url的id来指定更新对象，并根据传过来的notification信息来更新通知详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putNotification(@PathVariable Integer id, @ModelAttribute Notification notification) {
        //先判断该id是否存在，若存在，才能执行下一步更新操作
        if(notificationService.findById(id) > 0){
        //若该条记录存在，则执行更新操作
            int num = notificationService.updateByPrimaryKeySelective(notification);
            if(num > 0) {
                return SUCCESS;
            }else
                return FAILURE;

        }
        //
        return "this id does not exist";

    }

    @ApiOperation(value="删除通知信息", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteNotification(@PathVariable Integer id) {

        if (notificationService.findById(id) > 0) {
            int num = notificationService.deleteByPrimaryKey(id);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";
    }



}
