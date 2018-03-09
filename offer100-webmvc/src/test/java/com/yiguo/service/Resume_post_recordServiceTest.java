package com.yiguo.service;

import com.yiguo.bean.Page;
import com.yiguo.bean.Resume;
import com.yiguo.bean.Resume_post_record;
import com.yiguo.offer100.common.page.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by liyue on 2018/3/2.
 */
public class Resume_post_recordServiceTest extends BaseServiceTest {

@Autowired
    Resume_post_recordService resume_post_recordService;
@Test
public void manageUserResume() {
    // 处理"/users/"的GET请求，用来获取用户列表
    // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
         String title="经理";
          int enterpriseId=2;
         int state=1;
    PageInfo<Map> pageinfo=new PageInfo<Map>();
    pageinfo.setPageNum(0);
    pageinfo.setPageSize(30);
    Page page= new Page();
    page.setPageNumber(0);
    page.setPageSize(30);
    pageinfo.setRows( resume_post_recordService.selectBy(title,enterpriseId,state));
int count =resume_post_recordService.selectCounts(title,enterpriseId,state);
    pageinfo.setTotal(page.getTotal());
     System.out.println(count);
     System.out.println(pageinfo.getRows());
}
}
