package com.yiguo.mvc.controller;

import com.sun.javafx.fxml.expression.Expression;
import freemarker.template.Template;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.omg.IOP.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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
    public static String TimeDifference(long start, long end) {
        String f="true";
        long between = end - start;
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        if(day>0)
            f="false";
        else if(hour>1)
            f="false";
        else if(min>30)
            f="false";

        return f;
    }

    @ApiOperation(value = "用户找回密码",notes = "")
    @ResponseBody
    @RequestMapping(value = "/sendTemplateMail", method = RequestMethod.GET)
    public void sendTemplateMail(@PathVariable  String email){
        String emailencode=MD5(email)+"&";
        String url="https://yiguo.com/password/reset?";

        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setFrom(new InternetAddress(Sender, "offer100", "UTF-8"));
            helper.setTo(email);
            helper.setSubject("找回密码邮件");
            helper.setText(url+emailencode+"email="+"286311613@qq.com");
            Map<String, Object> model = new HashedMap();
            model.put("username", "尊敬的用户，");
            model.put("text",url+emailencode+"email="+email);
            //修改 application.properties 文件中的读取路径
            FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
            configurer.setTemplateLoaderPath("classpath:templates");
            //读取 html 模板
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
    @ApiOperation(value = "发送录取简历通知",notes = "")
    @ResponseBody
    @RequestMapping(value = "/sendGetMail", method = RequestMethod.GET)
    public void sendGetMail(@PathVariable  String email){
        String emailencode=MD5(email)+"&";
        String url="https://yiguo.com/password/reset?";

        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setFrom(new InternetAddress(Sender, "offer100", "UTF-8"));
            helper.setTo(email);
            helper.setSubject("简历通过邮件");
            helper.setText("恭喜您，您的简历已经通过，请等待面试通知");
            Map<String, Object> model = new HashedMap();
            model.put("username", "你好，");
            model.put("text","恭喜您，您的简历已经通过，请等待面试通知");
            //修改 application.properties 文件中的读取路径
            FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
            configurer.setTemplateLoaderPath("classpath:templates");
            //读取 html 模板
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail1.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
    @ApiOperation(value = "发送打回简历通知",notes = "")
    @ResponseBody
    @RequestMapping(value = "/sendUpMail", method = RequestMethod.GET)
    public void sendUpMail(@PathVariable  String email){
        String emailencode=MD5(email)+"&";
        String url="https://yiguo.com/password/reset?";

        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setFrom(new InternetAddress(Sender, "offer100", "UTF-8"));
            helper.setTo(email);
            helper.setSubject("简历通过邮件");
            helper.setText("您的简历没有通过，不好意思");
            Map<String, Object> model = new HashedMap();
            model.put("username", "你好，");
            model.put("text","您的简历没有通过，不好意思");
            //修改 application.properties 文件中的读取路径
            FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
            configurer.setTemplateLoaderPath("classpath:templates");
            //读取 html 模板
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail1.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
    @ApiOperation(value = "用户点击找回密码链接",notes = "")
    @ResponseBody
    @RequestMapping(value = "/getLink", method = RequestMethod.GET)
    public String getLink(@PathVariable  String link ,@PathVariable Integer id ) throws Exception {
        String reset = "true";
        Date date = new Date();
        long time = date.getTime();
        String[] links = link.split("&");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = (Date) formatter.parse(links[1]);
        long time1 = date1.getTime();
        String f = TimeDifference(time, time1);
        if (f.equals("false"))
            reset = "链接失效";
        else
            reset = "可以进行重新设定";

        return reset;
    }

}
