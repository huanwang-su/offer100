package com.yiguo.mvc.controller;

import com.yiguo.bean.Project_experience;
import com.yiguo.service.Project_experienceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;


@RestController
@Api(value = "API - Project_experienceController", description = "Project_experience详情")
@RequestMapping(value="/project_experience")
public class Project_experienceController {

    @Autowired
    Project_experienceService project_experienceService;
    @ApiOperation(value = "获取项目经验列表",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Project_experience> getProjectEList() {

        List<Project_experience> r = project_experienceService.getAll();
        return r;
    }

    @ApiOperation(value = "创建项目经验信息",notes = "根据project_experience对象创建project-experience")
    @ResponseBody

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postProjectE(@RequestBody Project_experience project_experience) {
        String f=FAILURE;
        Integer count=  project_experienceService.insert(project_experience);
        if(count > 0) {

            f=SUCCESS;
        }
        //System.out.println(f);
        return f;
    }


    @ApiOperation(value="根据id获取项目经验详细信息", notes="根据url的id来获取项目经验详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project_experience getProjectE(@PathVariable Integer id) {

        Project_experience project_experience=new Project_experience();
        project_experience=project_experienceService.selectByPrimaryKey(id);
        return project_experience;
    }

    @ApiOperation(value="更新项目经验详细信息", notes="根据url的id来指定更新对象，并根据传过来的project_experience信息来更新项目经验详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putProjrctE(@PathVariable Integer id, @ModelAttribute Project_experience project_experience) {
        if (project_experienceService.findById(id) > 0) {
            int num = project_experienceService.updateByPrimaryKeySelective(project_experience);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";
    }

    @ApiOperation(value="删除项目经验信息", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteProjectE(@PathVariable Integer id) {
        if (project_experienceService.findById(id) > 0) {
            int num = project_experienceService.deleteByPrimaryKey(id);
            if(num > 0) {
                return SUCCESS;
            }else
                return FAILURE;

        }
        return "this id does not exist";
    }

}
