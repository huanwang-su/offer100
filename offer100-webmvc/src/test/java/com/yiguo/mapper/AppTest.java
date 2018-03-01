package com.yiguo.mapper;

import com.yiguo.Offer100WebmvcApplication;
import com.yiguo.bean.Zone;
import com.yiguo.service.ZoneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

  
@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = {Offer100WebmvcApplication.class})
//相当于  --spring.profiles.active=dev  
@ActiveProfiles(value="dev")

public class AppTest {  

    @Autowired
    private ZoneService zoneService;
    @Test  
    public void testInsert(){  
        Zone enterprise=new Zone();
      enterprise=zoneService.selectByPrimaryKey(1);
      System.out.print(enterprise);
    }  
      
}  