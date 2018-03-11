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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
        con.setType("adImage_mainpage");
        HashMap<String,String> map = new HashMap<>();
        map.put("https://www.lgstatic.com/i/image3/M00/25/49/Cgq2xlqXcbuAZjq4AAk40VkL9gc766.PNG","");
        map.put("https://www.lgstatic.com/i/image3/M00/25/A7/CgpOIFqXqL-ANo1lAApG-4doay4200.JPG","");
        map.put("https://www.lgstatic.com/i/image3/M00/25/DA/CgpOIFqXwd6AWFnoAA1yzkJ2JQg435.JPG","");
        con.setValue(UtilJson.writeValueAsString(map));
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
