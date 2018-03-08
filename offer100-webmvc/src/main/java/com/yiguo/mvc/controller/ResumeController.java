package com.yiguo.mvc.controller;

import com.yiguo.bean.Resume;
import com.yiguo.service.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;
import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;

@Controller
@RestController
@Api(value = "API - ResumeController", description = "Resume详情")
@RequestMapping(value="/resume")     // 通过这里配置使下面的映射都在/resume下
public class ResumeController {

    @Autowired
    ResumeService resumeService;
    @ApiOperation(value = "获取简历列表",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)

    public List<Resume> getResumeList() {
        //List<User> r = new ArrayList<User>(users.values());
        List<Resume> r = resumeService.getAll();
        return r;
    }

    @ApiOperation(value = "创建简历信息",notes = "根据resume对象创建resume")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postResume(@RequestBody Resume resume) {

         //数据库对多数字段有长度限制，超出长度应提示错误（）
        // String f=FAILURE;
        Integer count=  resumeService.insert(resume);
        if(count > 0) {
            return SUCCESS;
        }
        //System.out.println(f);
        return FAILURE;
    }


    @ApiOperation(value="根据id获取简历详细信息", notes="根据url的id来获取简历详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resume getResume(@PathVariable Integer id) {
        Resume resume=new Resume();
        resume=resumeService.selectByPrimaryKey(id);
        return resume;
    }


    @ApiOperation(value="更新简历详细信息", notes="根据url的id来指定更新对象，并根据传过来的resume信息来更新简历详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putResume(@PathVariable Integer id, @ModelAttribute Resume resume) {
        if(resumeService.findById(id) > 0) {
            int num = resumeService.updateByPrimaryKeySelective(resume);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";
    }

    @ApiOperation(value="删除简历信息", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteResume(@PathVariable Integer id) {
        if(resumeService.findById(id) > 0) {
        int num = resumeService.deleteByPrimaryKey(id);
        if(num > 0) {
            return SUCCESS;
        }else
            return FAILURE;
        }
        return "this id does not exist";
    }

}
