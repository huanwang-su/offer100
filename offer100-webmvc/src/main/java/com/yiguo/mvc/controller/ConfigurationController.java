package com.yiguo.mvc.controller;

import com.yiguo.bean.Configuration;
import com.yiguo.service.ConfigurationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "API - ConfigurationController", description = "Configuration详情")
@RequestMapping(value="/configuration")     // 通过这里配置使下面的映射都在/users下
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;
    @ApiOperation(value = "获取配置列表",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Configuration> getConfigurationList() {
        // 处理"/configuration/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        //List<User> r = new ArrayList<User>(users.values());
        List<Configuration> r = configurationService.getAll();
        return r;
    }

    @ApiOperation(value = "创建配置信息",notes = "根据configuration对象创建configuration")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(@RequestBody Configuration configuration) {
        // 处理"/configuration/"的POST请求，用来创建Configuration
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="创建失败";
        Integer count=  configurationService.FindByType(configuration.getType());
        if(count==0) {
            configurationService.insert(configuration);
            f="创建成功";
        }
        //System.out.println(f);
        return f;
    }


    @ApiOperation(value="获取配置详细信息", notes="根据url的id来获取配置详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Configuration getConfiguration(@PathVariable Integer id) {
        // 处理"/configuration/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        Configuration configuration=new Configuration();
        configuration=configurationService.selectByPrimaryKey(id);
        return configuration;
    }

    @ApiOperation(value="更新配置详细信息", notes="根据url的id来指定更新对象，并根据传过来的configuration信息来更新用户详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putConfiguration(@PathVariable Integer id, @ModelAttribute Configuration configuration) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        //user.setId(id);
        int num = configurationService.updateByPrimaryKeySelective(configuration);
        if(num > 0) {
            return "更新成功";
        }else
            return "更新失败";
    }

    @ApiOperation(value="删除配置信息", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String  deleteConfiguration(@PathVariable Integer id) {
        // 处理"/configuration/{id}"的DELETE请求，用来删除User
        int num = configurationService.deleteByPrimaryKey(id);
        if(num > 0) {
            return "删除成功";
        }else
            return "删除失败";
    }
}
