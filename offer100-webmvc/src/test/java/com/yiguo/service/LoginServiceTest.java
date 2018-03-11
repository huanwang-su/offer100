package com.yiguo.service;

import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Notification;
import com.yiguo.bean.Page;
import com.yiguo.bean.User;
import com.yiguo.offer100.common.page.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyue on 2018/3/2.
 */
public class LoginServiceTest extends BaseServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void loginUser() {
        Map<String, Object> m = new HashMap<String, Object>();
        String type = "user";
        String username = "tianyongtao";
        String password = "tianyongtao";
        String f = "登录成功";
        if (type.equals("user")) {
            User user = userService.findByUsername(username);
            if (user != null) {
                if (!user.getPassword().equals(password))
                    f = "用户名或者密码不对";
                else if (user.getState() == 0)
                    f = "此用户已经被封，不可用";
            } else
                f = "此用户不存在，请先注册";
            if (f.equals("登录成功")) {
                m.put("username", username);
                m.put("id", user.getId());
                m.put("type", type);
            }
         System.out.print(m+"++++++++++++++++++++++++++++++++++++++++++++++++++=");

}

                }
}
