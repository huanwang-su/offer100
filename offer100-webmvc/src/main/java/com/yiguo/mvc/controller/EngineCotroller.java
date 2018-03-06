package com.yiguo.mvc.controller;

import com.sun.javafx.fxml.expression.Expression;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.omg.IOP.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

/**
 * Created by liyue on 2018/3/5.
 */
@Controller
@Api(value = "API - EngineController", description = "找回密码接口")
public class EngineCotroller {
    @Value("${spring.mail.username}")
    private String Sender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private JavaMailSender mailSender;
    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @ApiOperation(value = "用户找回密码",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public void sendEnterpriseSimpleMail(@PathVariable  String email) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        String url="Https://yiguo.com/password/reset?";
         String emailencode=MD5(email);
        message.setFrom("15337100703@163.com");
        message.setTo(email);
        message.setSubject("主题：找回密码邮件");
        message.setText(url+emailencode+"email="+email+"&"+"time="+new Date());

        mailSender.send(message);

    }
    @ApiOperation(value = "用户点击找回密码链接",notes = "")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public void getLink(@PathVariable  String link) throws Exception {




    }

}
