package com.yiguo.mvc.controller;

import com.yiguo.mvc.vo.IndustryVO;
import com.yiguo.service.IndustryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 行业web接口
 *
 * @author wanghuan
 * @date 2018-01-26
 */
@Api("行业web接口")
@Controller
@RequestMapping("/industry")
public class IndustryController {
    
    @Autowired private IndustryService industryService;
    @RequestMapping(value="/getAll",method=RequestMethod.GET)
    @ApiOperation("获取所有行业")
    @ResponseBody
    public IndustryVO getAllIndustry() {
        return industryService.getAllIndustry(0);
    }
    

}
