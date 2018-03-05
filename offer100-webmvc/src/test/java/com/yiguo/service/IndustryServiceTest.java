package com.yiguo.service;

import com.yiguo.bean.Industry;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

/**
 * ${DESCRIPTION}
 * @author wanghuan
 * @create 2018-03-01 
 **/
public class IndustryServiceTest extends BaseServiceTest {
    @Autowired
    private IndustryService service;
    private ThreadLocal<LocalTime> times = new ThreadLocal<>();

    @Before
    public void before() {
        times.set(LocalTime.now());
    }

    @Test
    public void testSearchByPID(){
        Industry industry=new Industry();
        industry.setParentId(1);
        System.out.println(service.query(industry));
        getAndOutputTime();
    }

    @Test
    public void getAllIndustry() {
        System.out.println(service.getAllIndustry(1942));
        getAndOutputTime();
        System.out.println(service.getAllIndustry(1927));
        getAndOutputTime();
        System.out.println(service.getAllIndustry(1));
        getAndOutputTime();
        System.out.println(service.getAllIndustry(0));
        getAndOutputTime();
    }

}
