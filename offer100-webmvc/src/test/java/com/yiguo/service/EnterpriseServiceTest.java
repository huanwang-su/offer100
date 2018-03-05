package com.yiguo.service;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Job;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
    public void selectPassword(){
        String  f="登录成功";
       Enterprise enterprise=enterpriseService.selectByIds("马化腾","software");
        if(enterprise==null)
            f="登录失败";
        System.out.print(f);
    }
}
