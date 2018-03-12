package com.yiguo.mvc.controller;

import com.yiguo.bean.News;
import com.yiguo.bean.Page;
import com.yiguo.service.NewsService;
import com.yiguo.service.NewsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alibaba.dubbo.monitor.MonitorService.*;

@Controller
@RequestMapping(value="/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @ApiOperation(value = "获取通知列表",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<News> getNewsList(@RequestParam(required = false) Integer has,@RequestParam(required = false) Integer next) {
        if (has==null)
            has = 0;
        if (next==null)
            next= 5;
        Page page=new Page();
        page.setStart(has);
        page.setPageSize(next);
        List<News> r = newsService.select(new News(),page);
        return r;
    }

    @ApiOperation(value = "创建通知信息",notes = "根据news对象创建news")
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


    @ApiOperation(value="根据id获取通知详细信息", notes="根据url的id来获取通知详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public News getNews(@PathVariable Integer id) {

        News news = new News();
        return newsService.selectByPrimaryKey(id);

    }

    @ApiOperation(value="更新通知详细信息", notes="根据url的id来指定更新对象，并根据传过来的news信息来更新通知详细信息")
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

    @ApiOperation(value="删除通知信息", notes="根据url的id来指定删除对象")
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
