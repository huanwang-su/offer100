package com.yiguo.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 设置 HTTP 验证规则
    public static final String ROLE_USER="user";
    public static final String ROLE_ENTERPRISE="enterprise";
    public static final String ROLE_ADMIN="admin";
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行

                // 所有 /login 的POST请求 都放行
                .antMatchers(HttpMethod.POST, "/login/**").permitAll()
                .antMatchers("/swagger-ui.html","/v2/api-docs","/swagger-resources","/configuration/security","/configuration/ui").permitAll()
                // 角色检查
                //用户拥有
                .antMatchers("/Receive/**").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.PUT, "/users/**","/resume/**","/project_experience/**","/education/**").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.POST, "/resume/**","/project_experience/**","/education/**").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.DELETE, "/resume/**","/project_experience/**","/education/**").hasRole(ROLE_USER)
                //企业拥有
                .antMatchers("/enterprise/**").hasRole(ROLE_ENTERPRISE)
                .antMatchers(HttpMethod.PUT, "/enterprise/**","/job/**").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.POST, "/enterprise/**","/job/**").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.DELETE, "/enterprise/**","/job/**").hasRole(ROLE_USER)
                //公有
                .antMatchers("/notification/**","/resume_post_record/**").hasAnyRole(ROLE_ENTERPRISE,ROLE_USER,ROLE_ADMIN)
                //管理员
                .antMatchers("/configuration/**").hasRole(ROLE_ADMIN)
                .antMatchers("/**").permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CustomAuthenticationProvider CustomAuthenticationProvider(){
        return new CustomAuthenticationProvider();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(CustomAuthenticationProvider());
    }
}