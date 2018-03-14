package com.yiguo.permission;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiguo.utils.UtilJson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.rmi.CORBA.Util;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.yiguo.permission.TokenAuthenticationService.HEADER_STRING;

class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(((HttpServletRequest)req).getMethod().toLowerCase().equals("options"))
            chain.doFilter(req,res);
        else {
            super.doFilter(req, res, chain);
            HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, HEAD, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");
        }
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        // JSON反序列化成 AccountCredentials
        AccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);

        // 返回一个验证令牌
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>(auth.getAuthorities());
        String out = authorities.get(authorities.size() - 1).getAuthority();
        authorities.remove(authorities.size() - 1);
        TokenAuthenticationService.addAuthentication(res, auth.getName(), authorities);
        Map<String,String> map = UtilJson.readValue(out,Map.class);
        map.put("status","1");
        map.put("token",res.getHeader(HEADER_STRING));
        responseOutWithJson(res, map);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String,String> map = new HashMap<>();
        map.put("status","0");
        map.put("msg",failed.getMessage());
        responseOutWithJson(response, map);
        //response.getOutputStream().println("Internal Server Error!!!");
    }

    /**
     * 以JSON格式输出
     * @param response
     */
    protected void responseOutWithJson(HttpServletResponse response,
                                       Object responseObject) {
        //将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (responseObject instanceof String)
                out.append(responseObject.toString());
            else
                out.append(UtilJson.writeValueAsString(responseObject));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}