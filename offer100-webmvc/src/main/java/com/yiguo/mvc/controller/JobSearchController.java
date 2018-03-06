package com.yiguo.mvc.controller;

import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.offer100.search.bean.Job;
import com.yiguo.offer100.search.service.JobSearchService;
import com.yiguo.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ${DESCRIPTION}
 * @author wanghuan
 * @create 2018-03-05 
 **/
@RestController
@RequestMapping("/jobSearch")
@Api("岗位搜索接口")
public class JobSearchController {
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private JobSearchService jobSearchService;

    @ApiOperation(value = "岗位搜索", notes = " 1. 等值查询：除了key中的字段，其他均为等值查询\n" +
            " 2. 对于list，list中的每一个字段都满足搜索引擎中的数据", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "job", value = "岗位查询条件，注意:\n" +
                    "education:大专，高中，本科，硕士，博士" +
                    "key:仅用于关键字查询，目前只考虑查询公司和职位的组合查询",
                    dataType = "com.yiguo.offer100.search.bean.Job",
                    paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "rows", value = "条数", dataType = "int", paramType = "query")
    })
    @PutMapping
    public PageInfo<Job> searchJob(@RequestBody(required = false) Job job,
                                   @RequestParam(name = "page") Integer page,
                                   @RequestParam(name = "rows") Integer rows) {

        if(job==null)
            job=new Job();
        PageInfo<Job> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(rows);
        return jobSearchService.search(job,pageInfo);
    }

}
