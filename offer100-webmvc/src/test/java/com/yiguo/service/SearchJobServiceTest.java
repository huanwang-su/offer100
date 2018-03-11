package com.yiguo.service;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Job;
import com.yiguo.bean.Page;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.offer100.search.bean.SearchJob;
import com.yiguo.offer100.search.service.JobSearchService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liyue on 2018/3/2.
 */
public class SearchJobServiceTest extends BaseServiceTest {

    @Autowired
    JobSearchService jobSearchService;
    @Autowired
    JobService jobService;
    @Autowired
    EnterpriseService enterpriseService;

    @Test
    @Ignore
    public void getJob() {
        Job job = new Job();
        job.setAddress("dsadasdasdasd");
        job.setEnterpriseId(8);
        job.setTitle("你好啊");
        job.setNature("私企");
        job.setExpirationTime(new Date());
        job.setEffectiveTime(new Date());
        job.setIndustryId(2);
        job.setZoneId(1);
        job.setWage(2);
        job.setWelfare("sasssa");
        job.setPeopleNumber(12);
        job.setEducation("d大学");
        job.setEmail("12121@qqq");
        job.setAddress("beijign1");
        job.setServiceYear("3");
        job.setDescription("dsadsadasdasdsadsadas");
        Enterprise enterprise = enterpriseService.selectByPrimaryKey(job.getEnterpriseId());
        if (enterprise != null) {
            Integer count = jobService.selectByIds(job);
            if (count == 0) {
                jobService.insert(job);
            } else {
                System.out.print("发布岗位失败，已经存在该岗位，不需要重新发布");
            }
        } else
            System.out.print("您未注册，请先注册");
    }

    @Test
    @Ignore
    public void updateZone() {
        Job job = new Job();
        Job job1 = new Job();
        job.setId(11);
        job.setDescription("我爱张舒雯");
        jobService.updateByPrimaryKeySelective(job);
        job1 = jobService.selectByPrimaryKey(job.getId());
        if (job.equals(job1)) {
            System.out.print("为修改成功");
        }

    }

    @Test
    @Ignore
    public void queryZone() {

        System.out.print(jobService.selectByPrimaryKey(11));
    }

    @Test

    public void deleteZone() {
        jobService.deleteByPrimaryKey(11);
        if (jobService.selectByPrimaryKey(11) != null)
            System.out.print("删除失败");
    }

    @Test
    //@Ignore
    public void testSolr() {
        Page page=new Page();
        page.setPageNumber(1);
        page.setPageSize(37);
        List<Job> jobs = jobService.select(new Job(),page);
        List<SearchJob> searchJobList = new ArrayList<>();
        jobs.forEach(job -> {
            searchJobList.add(jobService.toSolrJob(job));
        });
        jobSearchService.saveJobs(searchJobList);
    }
    @Test
    @Ignore
    public void selectJob() {
        int pageSize=30;
        int pageNumber =0;
        Job job=new Job();
        job.setEnterpriseId(1);
        // 处理"/Zones/{id}"的DELETE请求，用来删除Zone

        PageInfo<Job> pageinfo=new PageInfo<Job>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        List<Job> ew=jobService.select(job,page);
        pageinfo.setRows( jobService.select(job,page));
        pageinfo.setTotal(page.getTotal());
        System.out.println(pageinfo.getTotal());
       for(int i=0;i<pageinfo.getRows().size();i++)
           System.out.println(pageinfo.getRows().get(i));
    }
}
