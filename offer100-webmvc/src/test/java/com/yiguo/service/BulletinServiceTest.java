package com.yiguo.service;

import com.yiguo.bean.Bulletin;
import com.yiguo.bean.Education;
import com.yiguo.bean.Job;
import com.yiguo.bean.Page;
import com.yiguo.offer100.common.page.PageInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by liyue on 2018/3/2.
 */
public class BulletinServiceTest extends BaseServiceTest {

   @Autowired
    BulletinService bulletinService;
   @Test
   public void getBulletinList() {
       Bulletin bulletin =new Bulletin();
       PageInfo<Bulletin> pageinfo=new PageInfo<Bulletin>();
       pageinfo.setPageNum(0);
       pageinfo.setPageSize(30);
       Page page= new Page();
       page.setPageNumber(0);
       page.setPageSize(30);


       pageinfo.setRows( bulletinService.select(bulletin,page));
       int count=bulletinService.selectCount(bulletin);
       pageinfo.setTotal(count);
     System.out.println(pageinfo.getRows());
       System.out.println(pageinfo.getTotal()+"+++++++++++++++++++++");
   }
}
