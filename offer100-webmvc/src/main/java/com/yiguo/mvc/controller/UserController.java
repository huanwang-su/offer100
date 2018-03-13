package com.yiguo.mvc.controller;

import com.yiguo.bean.User;
import com.yiguo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;
import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;

@RestController
@Api(value = "API - UserController", description = "User详情")
@RequestMapping(value="/users")     // 通过这里配置使下面的映射都在/users下
public class UserController {


  @Autowired
    UserService userService;
    /*@ApiOperation(value = "获取用户列表",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        //List<User> r = new ArrayList<User>(users.values());
        List<User> r = userService.query();
        return r;
    }*/

    @ApiOperation(value = "创建用户",notes = "根据user对象创建user")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String buildUser(@RequestBody User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="注册失败，重新注册";
        User user1=  userService.findByUsername(user.getUsername());
        if(user1==null) {
            userService.insert(user);
            f="注册成功";
        }
        else f="此用户名已经注册，请更换";
        //System.out.println(f);
        return f;
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@RequestParam Integer id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        User user=new User();
        user=userService.selectByPrimaryKey(id);
        return user;
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
   @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable Integer id, @RequestBody User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        //user.setId(id);

        if(userService.findById(id) > 0) {
            user.setId(id);
            int num = userService.updateByPrimaryKeySelective(user);
            if (num > 0) {
                return SUCCESS;
            } else
                return FAILURE;
        }
        return "this id does not exist";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String  deleteUser(@PathVariable Integer id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        if(userService.findById(id) > 0) {
        int num = userService.deleteByPrimaryKey(id);
        if(num > 0) {
            return SUCCESS;
        }else
            return FAILURE;
        }
        return "this id does not exist";
    }
}
