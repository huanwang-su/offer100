package com.yiguo.mapper;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liyue on 2018/2/28.
 */


    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    public class BasicUtClass {
        protected final Logger logger = LoggerFactory.getLogger(this.getClass());
        protected final ObjectMapper objectMapper = new ObjectMapper();

    }

