package com.yiguo.service;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Notification;
import com.yiguo.bean.Page;
import com.yiguo.offer100.common.page.PageInfo;
import org.aspectj.weaver.ast.Not;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liyue on 2018/3/2.
 */
public class NotificationServiceTest extends BaseServiceTest {


@Autowired
NotificationService notificationService;
    @Test
    public void selectNotification() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递

        PageInfo<Notification> pageinfo=new PageInfo<Notification>();
        pageinfo.setPageNum(0);
        pageinfo.setPageSize(30);
        Page page= new Page();
        page.setPageNumber(0);
        page.setPageSize(30);
        Notification notification=new Notification();
    notification.setTitle("111");

        int count=notificationService.selectCount(notification);
        pageinfo.setRows(notificationService.select(notification,page));
        pageinfo.setTotal(count);
        System.out.println(count);
        System.out.println(pageinfo.getRows());
    }
}
