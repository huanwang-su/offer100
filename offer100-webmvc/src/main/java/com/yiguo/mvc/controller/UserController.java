package com.yiguo.mvc.controller;

import com.yiguo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import  com.yiguo.bean.User;
import java.util.*;

@RestController
@Api(value = "API - UserController", description = "User详情")
@RequestMapping(value="/users")     // 通过这里配置使下面的映射都在/users下
public class UserController {

    // 创建线程安全的Map
    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());
  @Autowired
    UserService userService;
    @ApiOperation(value = "获取用户列表",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        //List<User> r = new ArrayList<User>(users.values());
        List<User> r = userService.query();
        return r;
    }

    @ApiOperation(value = "创建用户",notes = "根据user对象创建user")
  @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        String f="false";
        Integer count=  userService.selectByName(user.getName());
        if(count==0) {
            userService.insert(user);
        f="true";
        }
        return f;
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Integer id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        User user=new User();
        user=userService.selectByPrimaryKey(id);
        return user;
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
   @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Integer putUser(@PathVariable Integer id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        //user.setId(id);
        int num = userService.updateByPrimaryKeySelective(user);
        return num;
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int  deleteUser(@PathVariable Integer id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        int num = userService.deleteByPrimaryKey(id);
        return num;
    }
}
