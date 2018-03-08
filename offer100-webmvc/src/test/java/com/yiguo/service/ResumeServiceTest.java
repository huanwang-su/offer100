package com.yiguo.service;

import com.yiguo.bean.Page;
import com.yiguo.bean.Receive;
import com.yiguo.bean.Resume;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by liyue on 2018/3/2.
 */
public class ResumeServiceTest extends BaseServiceTest {

@Autowired
    ResumeService resumeService;
@Test
public void manageUserResume() {
    // 处理"/users/"的GET请求，用来获取用户列表
    // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递

    Page page=new Page();
    Resume resume=new Resume();
    resume.setUserId(123);
    List<Resume> resumes =resumeService.select(resume,page);

  for(int i=0;i<resumes.size();i++)
      System.out.println(resumes.get(i));
}
}
