package com.yiguo.service;

import com.yiguo.bean.Page;
import com.yiguo.bean.Zone;
import com.yiguo.bean.Zone;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by liyue on 2018/3/2.
 */
public class ZoneServiceTest extends BaseServiceTest {

     @Autowired
   ZoneService zoneService;

    @Test
   @Ignore
     public void getZone(){
       Zone zone=new Zone();
       zone.setName("琰琰");
       zone.setLevel(1);
       zone.setParentId(2);
      int count=  zoneService.selectByName(zone.getName());
         if(count==0)
          zoneService.insert(zone);
      else
          System.out.print("++++++++++++++++++++++++++++++");

     }
    @Test
   @Ignore
    public void updateZone(){
        Zone zone=new Zone();
        zone.setId(3437);
        zone.setParentId(3);
        zoneService.updateByPrimaryKeySelective(zone);
    }
    @Test

    public void queryZone(){
System.out.print(        zoneService.selectByPrimaryKey(3437));
    }
    @Test
    @Ignore
    public void deleteZone(){
        zoneService.deleteByPrimaryKey(3437);
    }
    @Test

    public void getSonZone(){


        Page page=new Page();

Zone zone =new Zone();
zone.setParentId(3);

        List<Zone> zones= zoneService.select(zone, page);
        System.out.println(zones.size());
  for(int i=0;i<zones.size();i++)
      System.out.println(zones.get(i));
    }

}
