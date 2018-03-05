package com.yiguo.offer100.search;

import com.yiguo.offer100.search.bean.Talent;
import com.yiguo.offer100.search.service.JobSearchService;
import com.yiguo.offer100.search.service.TalentSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Offer100SearchApplication.class})
public class Offer100SearchApplicationTests {

	@Autowired
	JobSearchService jobSearchService;
	@Autowired
	TalentSearchService talentSearchService;

	@Test
	public void contextLoads() {
//		System.out.println(jobSearchService.search(null,0,11));
//		System.out.println(talentSearchService.search(null,0,11));
	}

}
