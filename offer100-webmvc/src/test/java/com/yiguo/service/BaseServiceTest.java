package com.yiguo.service;

import com.yiguo.Offer100WebmvcApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

/**
 * ${DESCRIPTION}
 * @author wanghuan
 * @create 2018-03-01 
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Offer100WebmvcApplication.class)
public class BaseServiceTest {
    protected ThreadLocal<LocalTime> times = new ThreadLocal<>();
    protected void getAndOutputTime(){
        System.out.println(LocalTime.now().getNano()-times.get().getNano());
        times.set(LocalTime.now());
    }
}
