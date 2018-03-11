package com.yiguo.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sun.xml.internal.fastinfoset.stax.factory.StAXOutputFactory;
import com.yiguo.bean.Configuration;
import com.yiguo.utils.UtilJson;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConfigurationServiceTest extends BaseServiceTest {

    @Autowired
    private ConfigurationService service;

    @Test
    public void getAll(){

        System.out.println(service.getAll());
    }


   @Test
    public void findByType(){

        Configuration con = service.selectByType("凡凡");
        System.out.println(con);

    }

    @Test
    public void deleteByType(){

        int num = service.deleteByType("凡凡");
        System.out.println(num);

    }




    @Test
    @Ignore
    public void findByPrimaryKey(){

        Configuration con = service.selectByPrimaryKey(1);
        System.out.print(con);
    }

    @Test
    @Ignore
    public void deleteByPrimaryKey(){
        System.out.println(service.selectCount(null));
        int num = service.deleteByPrimaryKey(5);
        System.out.println(service.selectCount(null));
    }


    @Test
    public void insert(){

        Configuration con = new Configuration();
        con.setType("adImage_search");
        HashMap<String,String> map = new HashMap<>();
        map.put("https://static.lagou.com/i/image3/M00/25/52/Cgq2xlqXdguAVAwVAACDmIz1tFI568.PNG","https://activity.lagou.com/activi/promotion2018/pages/pc/index.html#/main");
        map.put("https://static.lagou.com/i/image/M00/41/7E/CgpEMllUxC2AOU7wAABZXy04ZTg283.JPG","https://pro.lagou.com/");
        con.setValue(UtilJson.writeValueAsString(map));

        //热门公司
        List<Integer> hotEnterPrice = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        con.setType("mainpage_hotEnterPrice");
        con.setValue(UtilJson.writeValueAsString(hotEnterPrice));
        System.out.println(service.insert(con));
        List<Integer> hotjob = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        con.setType("mainpage_hotjob");
        con.setValue(UtilJson.writeValueAsString(hotEnterPrice));
        System.out.println(service.insert(con));
        //System.out.println(service.insertSelective());
    }

    @Test
    public void update() throws ParseException {

        Configuration con = new Configuration();
        con.setId(1);
        con.setType("莹莹");
        con.setValue("影");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse("2008-08-08 12:10:12");
        Date date2 = sdf.parse("2018-08-08 12:10:12");
        con.setCreateTime(date1);
        con.setUpdateTime(date2);
        System.out.println(service.updateByPrimaryKey(con));
    }



}
