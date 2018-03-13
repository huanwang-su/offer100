package com.yiguo.mvc.controller;

import com.yiguo.bean.Industry;
import com.yiguo.bean.Page;
import com.yiguo.mvc.vo.IndustryVO;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.IndustryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;

/**
 * 行业web接口
 *
 * @author wanghuan
 * @date 2018-01-26
 */
@Api(value = "行业接口")
@Controller
@RequestMapping("/industry")
public class IndustryController {

    @Autowired
    private IndustryService industryService;

    @Cacheable(cacheNames = "offer100", keyGenerator = "cacheKeyGenerator")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation("获取所有行业")
    @ResponseBody
    public IndustryVO getAllIndustry() {
        return industryService.getAllIndustry(0);
    }

    @Cacheable(cacheNames = "offer100", keyGenerator = "cacheKeyGenerator")
    @RequestMapping(value = "/getParentId/{id}", method = RequestMethod.GET)
    @ApiOperation("根据父id获取行业")
    @ResponseBody
    public PageInfo<Industry> getParentId(@RequestParam Integer id,
                                          @RequestParam Integer pageSize,
                                          @RequestParam Integer pageNumber) {

        PageInfo<Industry> pageinfo = new PageInfo<Industry>();
        Industry industry = new Industry();
        industry.setParentId(id);
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page = new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        pageinfo.setTotal(industryService.selectCount(industry));
        pageinfo.setRows(industryService.select(industry, page));
        return pageinfo;
    }

    @Cacheable(cacheNames = "offer100", keyGenerator = "cacheKeyGenerator")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id找到行业详细信息", notes = "通过传入的id值，找到行业详细信息")
    @ResponseBody
    public Object getIndustry(@PathVariable Integer id) {
        String key = FAILURE;
        Industry industry = industryService.selectByPrimaryKey(id);
        if (industry != null)
            return industry;
        else
            return key;
    }

}
