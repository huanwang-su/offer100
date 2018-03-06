package com.yiguo.service;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Date;


/**
 * Created by liyue on 2018/3/5.
 */
public class MailTest extends BaseServiceTest {

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

    @Test
    public void sendSimpleMail() throws Exception {
        String emailencode=MD5("286311613@qq.com")+"&";
        String url="Https://yiguo.com/password/reset?";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("15337100703@163.com");
        message.setTo("286311613@qq.com");
        message.setSubject("主题：找回密码邮件");
        message.setText(url+emailencode+"email="+"286311613@qq.com");

        mailSender.send(message);
    }

}
