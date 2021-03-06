package com.yiguo.permission;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 5;     // 5天
    static final String SECRET = "P@ssw02d";            // JWT密码
    static final String TOKEN_PREFIX = "TOKEN_PREFIX";        // Token前缀
    static final String HEADER_STRING = "Access-Token";// 存放Token的Header Key

    // JWT生成方法
    static void addAuthentication(HttpServletResponse response, String username, Collection<? extends GrantedAuthority> authorities, String info) {

        // 生成JWT
        String JWT = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", authorities)
                .claim("info", info)
                // 用户名写入标题
                .setSubject(username)
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        // 将 JWT 写入 head
        response.setHeader(HEADER_STRING, JWT);
    }

    //获取token和其中的信息
    public static Map<String, String> getTokenInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        String token = request.getHeader(HEADER_STRING);
        if (token == null)
            return map;
        map.put("token", token);
        // 解析 Token
        Claims claims = Jwts.parser()
                // 验签
                .setSigningKey(SECRET)
                // 去掉 Bearer
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        // 拿用户名
        map.put("user", claims.getSubject());
        map.put("info", claims.get("info", String.class));
        return map;
    }


    // JWT验证方法
    static Authentication getAuthentication(HttpServletRequest request) {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            // 拿用户名
            String user = claims.getSubject();
            String info = claims.get("info", String.class);
            if (request.getSession().getAttribute("info") == null || !request.getSession().getAttribute(token).equals(info))
                return null;
            // 得到 权限（角色）
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) claims.get("authorities");

            // 返回验证令牌
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorities) :
                    null;
        }
        return null;
    }
}