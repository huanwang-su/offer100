package com.yiguo.service;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Job;
import com.yiguo.bean.Page;
import com.yiguo.offer100.common.page.PageInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liyue on 2018/3/2.
 */
public class EnterpriseServiceTest extends BaseServiceTest {


    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    UserService userService;
    @Test
    @Ignore
     public void getEnterprise(){
        String f="注册成功";
        Enterprise enterprise=new Enterprise();
        enterprise.setAddress("北京");
        enterprise.setDescription("我爱张舒雯");
        enterprise.setEmail("212121@qq.cin");
        enterprise.setImageLog("sdadadas");
        enterprise.setImageLicense("asdsadas");
        enterprise.setLinkman("asdasd");
        enterprise.setName("超越");
        enterprise.setUserName("李越");
        enterprise.setUserPassword("qwe1231212");
        enterprise.setPhone("131w1212121");
        enterprise.setIndustryId(1);
        enterprise.setZoneId(2);

            Integer count = enterpriseService.selectByName(enterprise.getName());
            if (count == 0) {
                enterpriseService.insert(enterprise);
            } else {
                f = "注册失败，未通过审核";
            }
     System.out.print(f);
     }
    @Test
      @Ignore
    public void updateZone(){
        String f="修改成功";
        Enterprise enterprise=new Enterprise();
        Enterprise enterprise1=new Enterprise();
        enterprise.setId(8);
        enterprise.setDescription("我叫李越");
        enterpriseService.updateByPrimaryKeySelective(enterprise);
        enterprise1=enterpriseService.selectByPrimaryKey(8);
        if(enterprise.equals(enterprise1))
            f="未修改成功";
        System.out.print(f);
        }


    @Test
    @Ignore
    public void queryZone(){
        System.out.print(enterpriseService.selectByPrimaryKey(8));

    }
    @Test
    @Ignore
    public void deleteZone(){
        String f="删除成功";
        enterpriseService.deleteByPrimaryKey(8);
        if(enterpriseService.selectByPrimaryKey(8)!=null)
            f="删除失败";
        System.out.print(f);
    }
    @Test
    @Ignore
    public void selectPassword(){
        String  f="登录成功";
       Enterprise enterprise=enterpriseService.selectByIds("马化腾","software");
        if(enterprise==null)
            f="登录失败";
        System.out.print(f);
    }
    @Test
    public void selectEnterpris() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递

        PageInfo<Enterprise> pageinfo=new PageInfo<Enterprise>();
        pageinfo.setPageNum(0);
        pageinfo.setPageSize(30);
        Page page= new Page();
        page.setPageNumber(0);
        page.setPageSize(30);
        Enterprise enterprise =new Enterprise();
      enterprise.setState(1);
        enterprise.setStage(1);
      List<Enterprise> ew=enterpriseService.select(enterprise,page);
        System.out.println(ew.size());
        int count=enterpriseService.selectCount(enterprise);
        pageinfo.setTotal(count);
        System.out.println(count);
        System.out.println(pageinfo.getRows());
    }
}
