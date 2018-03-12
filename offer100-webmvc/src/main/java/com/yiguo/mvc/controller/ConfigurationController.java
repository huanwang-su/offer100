package com.yiguo.mvc.controller;

import com.yiguo.bean.Configuration;
import com.yiguo.service.ConfigurationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;
import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;

@RestController
@Api(value = "API - ConfigurationController", description = "Configuration详情")
@RequestMapping(value="/configuration")     // 通过这里配置使下面的映射都在/configuration下
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

        String f=FAILURE;
        Integer count=  configurationService.FindByType(configuration.getType());
        if(count==0) {
            configurationService.insert(configuration);
            f=SUCCESS;
        }
        //System.out.println(f);
        return f;
    }


    @ApiOperation(value="根据id获取配置详细信息", notes="根据url的id来获取配置详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getConfigurationById(@PathVariable Integer id) {
        // 处理"/configuration/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        if( configurationService.findById(id) > 0) {


            Configuration configuration = new Configuration();
            configuration = configurationService.selectByPrimaryKey(id);
            return configuration;

        }
        return "this id does not exist";
    }

   
    @ApiOperation(value="根据type获取配置详细信息", notes="根据url的type来获取配置详细信息")
    @ResponseBody
    @RequestMapping(value = "/type/{type}" , method = RequestMethod.GET)
    //需要添加/type,否则运行时服务器不能正确辨别是本函数的url还是getConfigurationById方法的url
    public Object getConfigurationByType(@PathVariable String type){

        if(configurationService.FindByType(type) > 0) {

            Configuration configuration = new Configuration();
            // System.out.println("123");
            configuration = configurationService.selectByType(type);
            System.out.println(configuration.getValue());
            return configuration;

        }
        return "this type does not exist";
    }

    @ApiOperation(value="更新配置详细信息", notes="根据url的id来指定更新对象，并根据传过来的configuration信息来更新用户详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putConfiguration(@PathVariable Integer id, @ModelAttribute Configuration configuration) {
        if (configurationService.findById(id) > 0){
            configuration.setId(id);
        int num = configurationService.updateByPrimaryKeySelective(configuration);
        if(num > 0) {
            return SUCCESS;
        }else
            return FAILURE;
        }
        return "this id does not exist";
    }

    @ApiOperation(value="根据id删除配置信息", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteConfiguration(@PathVariable Integer id) {
        // 处理"/configuration/{id}"的DELETE请求，用来删除Configuration
        if (configurationService.findById(id) > 0){
        int num = configurationService.deleteByPrimaryKey(id);
        if(num > 0) {
            return SUCCESS;
        }else
            return FAILURE;
        }
        return "this id does not exist";
    }

   @ApiOperation(value="根据type删除配置信息", notes="根据url的type来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/type/{type}", method = RequestMethod.DELETE)
    public String deleteConfiguration(@PathVariable String type) {
        // 处理"/configuration/{id}"的DELETE请求，用来删除Configuration
       if (configurationService.FindByType(type) > 0){
        int num = configurationService.deleteByType(type);
        if(num > 0) {
            return SUCCESS;
        }else
            return FAILURE;
       }
       return "this type does not exist";
    }
}
