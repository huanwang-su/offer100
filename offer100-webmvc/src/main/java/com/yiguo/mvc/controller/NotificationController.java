package com.yiguo.mvc.controller;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Notification;
import com.yiguo.bean.Page;
import com.yiguo.offer100.common.page.PageInfo;
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
@Api(value = "通知接口")
@RequestMapping(value="/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;
 /*   @ApiOperation(value = "获取通知列表",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Notification> getNotificationList() {

        List<Notification> r = notificationService.getAll();
        return r;
    }*/

    @ApiOperation(value = "创建通知信息",notes = "根据notification对象创建notification")
    @ResponseBody
    @RequestMapping(value = "/buildbNotification", method = RequestMethod.POST)
    public String buildbNotification(@RequestBody Notification notification) {
        String f=FAILURE;
        Integer count=  notificationService.insert(notification);
        if(count > 0) {

            f=SUCCESS;
        }
        //System.out.println(f);
        return f;
    }


    @ApiOperation(value="查询通知信息", notes="根据url的id来获取通知详细信息")
    @ResponseBody
    @RequestMapping(value = "/getNotification/{id}", method = RequestMethod.GET)
    public Notification getNotification(@PathVariable Integer id) {

            Notification notification = new Notification();
            return notificationService.selectByPrimaryKey(id);

    }

    @ApiOperation(value="更新通知信息", notes="根据url的id来指定更新对象，并根据传过来的notification信息来更新通知详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateNotification(@PathVariable Integer id, @RequestBody Notification notification) {
        //先判断该id是否存在，若存在，才能执行下一步更新操作
        if(notificationService.findById(id) > 0){
        //若该条记录存在，则执行更新操作
            notification.setId(id);
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

    @ApiOperation(value = "获取通知列表",notes = "查询得出通知，加入了分页")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public PageInfo<Notification> selectNotifivation(@RequestParam(required = false) Notification notification, @RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递=
        notification = notification==null?new Notification():notification;
        PageInfo<Notification> pageinfo=new PageInfo<Notification>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        pageinfo.setRows( notificationService.select(notification,page));
        int count=notificationService.selectCount(notification);
        pageinfo.setTotal(count);
        return  pageinfo;
    }



}
