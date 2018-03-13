package com.yiguo.mvc.controller;

import com.yiguo.bean.News;
import com.yiguo.bean.Page;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.NewsService;
import com.yiguo.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alibaba.dubbo.monitor.MonitorService.*;

@Controller
@Api(value = "咨询接口")
@RequestMapping(value="/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @ApiOperation(value = "获取咨询列表",notes = "获取所有咨询列表，并且进行分页处理")
    @ResponseBody
    @RequestMapping(value = "getNewsList", method = RequestMethod.GET)
    public PageInfo<News> getNewsList(@RequestParam Integer pageSize,
                                  @RequestParam Integer pageNumber) {

        News news = new News();
        PageInfo<News> pageinfo=new PageInfo<News>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);

        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        pageinfo.setRows( newsService.select(news,page));
        int count=newsService.selectCount(news);
        pageinfo.setTotal(count);


        return pageinfo;
    }

    @ApiOperation(value = "创建咨询信息",notes = "根据news对象创建news")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postNews(@RequestBody News news) {
        String f=FAILURE;
        Integer count=  newsService.insert(news);
        if(count > 0) {

            f=SUCCESS;
        }
        //System.out.println(f);
        return f;
    }


    @ApiOperation(value="查询咨询信息", notes="根据url的id来获取通知详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public News getNews(@RequestParam Integer id) {

        News news = new News();
        return newsService.selectByPrimaryKey(id);

    }

    @ApiOperation(value="更新咨询信息", notes="根据url的id来指定更新对象，并根据传过来的news信息来更新咨询详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putNews(@PathVariable Integer id, @RequestBody News news) {
        //先判断该id是否存在，若存在，才能执行下一步更新操作
        if (newsService.findById(id) > 0) {
            news.setId(id);
            int num = newsService.updateByPrimaryKeySelective(news);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";
        }

    @ApiOperation(value="删除咨询信息", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteNews(@PathVariable Integer id) {

        if (newsService.selectByPrimaryKey(id) != null) {
            int num = newsService.deleteByPrimaryKey(id);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";
    }


}
