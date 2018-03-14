package com.yiguo.permission;

import com.yiguo.mvc.controller.LoginController;
import com.yiguo.utils.UtilJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.yiguo.permission.WebSecurityConfig.*;

// 自定义身份认证验证组件
class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginController loginController;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        HashMap<String,Object> map = new HashMap<>();
        map.put("username",name);
        map.put("password",password);
        map.put("type","user");
        Map<String,Object> map2 =loginController.loginUser(map);
        if(map2.get("msg").equals("登录成功")){
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new StringGrantedAuthority(ROLE_USER) );
            authorities.add( new StringGrantedAuthority(UtilJson.writeValueAsString(map2)) );
            // 生成令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
            return auth;
        }else if(map2.get("msg").toString().startsWith("此用户不存在")){
            map.put("type","enterprise");
            map2 =loginController.loginUser(map);
            if(map2.get("msg").equals("登录成功")) {
                // 这里设置权限和角色
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new StringGrantedAuthority(ROLE_ENTERPRISE));
                authorities.add( new StringGrantedAuthority(UtilJson.writeValueAsString(map2)) );
                // 生成令牌
                Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
                return auth;
            }
        }
        throw new BadCredentialsException(map2.get("msg").toString());
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}