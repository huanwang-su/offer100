package com.yiguo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ${DESCRIPTION}
 * @author wanghuan
 * @create 2018-03-01 
 **/
public class IndustryServiceTest extends BaseServiceTest{
    @Autowired
    private IndustryService service;

    @Test
    public void getAllIndustry(){
        System.out.println(service.getAllIndustry(1950));
    }
}
