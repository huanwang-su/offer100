package com.yiguo.mvc.controller;

import com.yiguo.bean.*;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.*;
import freemarker.template.Template;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "简历投递接口")
@RequestMapping("/resume_post_record")

public class Resume_post_recordController {
    @Autowired
    Resume_post_recordService resume_post_recordService;
    @Autowired
    JobService jobService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    UserService userService;
    EngineCotroller engineCotroller=new EngineCotroller();
//    @ApiOperation(value = "jobid",notes = "企业管理自己的岗位")
//    @ResponseBody
//    @RequestMapping(value = "/manageJob/{id}", method ={RequestMethod.GET})
//    public List<Job> manageJob(@PathVariable Integer id) {
//        // 处理"/users/"的GET请求，用来获取用户列表
//        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
//        String f="false";
//    Page page=new Page();
//    page.setPageSize(10);
//    Job job=new Job();
//    job.setEnterpriseId(id);
//     List<Job> jobs= jobService.select(job,page);
//
//     return jobs;
//    }
@Value("${spring.mail.username}")
private String Sender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ResumeService resumeService;
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


    @ApiOperation(value = "发送录取简历通知",notes = "通过发送邮件通知，提醒用户录取简历")
    @ResponseBody
    @RequestMapping(value = "/getResumeMail/{resumeid}/{enterpriseId}", method = RequestMethod.GET)
    public void getResumeMail(@PathVariable Integer resumeid,@PathVariable Integer enterpriseId){
        Resume resume = resumeService.selectByPrimaryKey(resumeid);
        User user =  userService.selectByPrimaryKey(resume.getUserId());
        String emailencode=MD5(user.getEmail())+"&";
        String url="https://yiguo.com/password/reset?";
        Resume_post_record resume_post_record =new Resume_post_record();
        resume_post_record.setResumeId(resumeid);
        List<Resume_post_record> resume_post_records= resume_post_recordService.select(resume_post_record,null);
        byte i=(byte)3;
        resume_post_records.get(0).setState(i);
        resume_post_recordService.updateByPrimaryKeySelective(resume_post_records.get(0));
        if(resume_post_recordService.select(resume_post_record,null).get(0).getState()==3) {
            Notification notification = new Notification();
            notification.setTitle("简历通知");
            notification.setContext("简历已经被邀请，等待面试通知");
            notification.setRecieverId(resume.getUserId());
            notification.setSenderId(enterpriseId);
            byte is = (byte) 2;
            notification.setType(is);
            notification.setSendTime(new Date());
            notificationService.insert(notification);
            MimeMessage message = null;
            try {
                message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(Sender);
                helper.setFrom(new InternetAddress(Sender, "offer100", "UTF-8"));
                helper.setTo(user.getEmail());
                helper.setSubject("来自offer100");
                helper.setText("简历通知");
                Map<String, Object> model = new HashedMap();
                model.put("username", "你好，");
                model.put("text", "恭喜您，您的简历已经通过，请等待面试通知");
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
    }
    @ApiOperation(value = "发送打回简历通知",notes = "通过发送邮件，提醒用户简历未通过")
    @ResponseBody
    @RequestMapping(value = "rejectMailNotify/{resumeid}/{enterpriseId}", method = RequestMethod.GET)
    public void getReturnMail(@PathVariable  Integer resumeid,@PathVariable Integer enterpriseId){
        Resume resume = resumeService.selectByPrimaryKey(resumeid);
       User user =  userService.selectByPrimaryKey(resume.getUserId());
        String emailencode=MD5(user.getEmail())+"&";
        String url="https://yiguo.com/password/reset?";
        Resume_post_record resume_post_record =new Resume_post_record();
        resume_post_record.setResumeId(resumeid);
       List<Resume_post_record> resume_post_records= resume_post_recordService.select(resume_post_record,null);
       byte i=(byte)4;
       resume_post_records.get(0).setState(i);
        resume_post_recordService.updateByPrimaryKeySelective(resume_post_records.get(0));


       if(resume_post_recordService.select(resume_post_record,null).get(0).getState()==4) {
           Notification notification = new Notification();
           notification.setTitle("简历通知");
           notification.setContext("简历已经被打回，请再寻找其他公司");
           notification.setRecieverId(resume.getUserId());
           notification.setSenderId(enterpriseId);
           byte is = (byte) 2;
           notification.setType(is);
           notification.setSendTime(new Date());
           notificationService.insert(notification);
           MimeMessage message = null;
           try {
               message = mailSender.createMimeMessage();
               MimeMessageHelper helper = new MimeMessageHelper(message, true);
               helper.setFrom(Sender);
               helper.setFrom(new InternetAddress(Sender, "offer100", "UTF-8"));
               helper.setTo(user.getEmail());
               helper.setSubject("来自于offer100");
               helper.setText("简历通知");
               Map<String, Object> model = new HashedMap();
               model.put("username", "你好，");
               model.put("text", "您的简历没有通过，不好意思");
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
    }
    @ApiOperation(value = "简历发送成功通知",notes = "通过发送邮件，提醒用户简历发送成功")
    @ResponseBody
    @RequestMapping(value = "/{id}/{resumeId}/{jobId}", method = RequestMethod.GET)
    public void getSuccessMail(@PathVariable  Integer id,@PathVariable Integer resumeId,@PathVariable Integer jobId){

        Resume_post_record resume_post_record1 = new Resume_post_record();
        byte is=(byte)1;
        resume_post_record1.setState(is);
        resume_post_record1.setResumeId(resumeId);
        resume_post_record1.setJobId(jobId);
        resume_post_record1.setCreateTime(new Date());
        resume_post_recordService.insert(resume_post_record1);

        Resume_post_record resume_post_record =new Resume_post_record();
        resume_post_record.setResumeId(resumeId);
        int count =resume_post_recordService.selectCount(resume_post_record);
        if(count!=0) {
            List <Resume_post_record> resume_post_records=resume_post_recordService.select(resume_post_record,null);
           if(resume_post_records.get(0).getState()==1){
               Notification notification=new Notification();
               notification.setTitle("简历投递");
               notification.setContext("简历投递成功");
               notification.setRecieverId(id);
               notification.setSenderId(0);
               byte i=(byte)3;
               notification.setType(i);
               notification.setSendTime(new Date());
               notificationService.insert(notification);
           }
            User user = userService.selectByPrimaryKey(id);
            String emailencode = MD5(user.getEmail()) + "&";
            String url = "https://yiguo.com/password/reset?";

            MimeMessage message = null;
            try {
                message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(Sender);
                helper.setFrom(new InternetAddress(Sender, "offer100", "UTF-8"));
                helper.setTo(user.getEmail());
                helper.setSubject("来自于offer100");
                helper.setText("简历通知");
                Map<String, Object> model = new HashedMap();
                model.put("username", "你好，");
                model.put("text", "您的简历已经发送成功");
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
        else{
            User user = userService.selectByPrimaryKey(id);
            String emailencode = MD5(user.getEmail()) + "&";
            String url = "https://yiguo.com/password/reset?";

            MimeMessage message = null;
            try {
                message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(Sender);
                helper.setFrom(new InternetAddress(Sender, "offer100", "UTF-8"));
                helper.setTo(user.getEmail());
                helper.setSubject("来自于offer100");
                helper.setText("简历通知");
                Map<String, Object> model = new HashedMap();
                model.put("username", "你好，");
                model.put("text", "您的简历未能发送成功");
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

    }

    @ApiOperation(value = "简历正在筛选通知",notes = "通过发送邮件，提醒用户简历正在筛选")
    @ResponseBody
    @RequestMapping(value = "/{resumeid}/{enterpriseId}", method = RequestMethod.GET)
    public void getCheckMail(@PathVariable  Integer resumeid,@PathVariable Integer enterpriseId){
        Resume resume = resumeService.selectByPrimaryKey(resumeid);
        User user =  userService.selectByPrimaryKey(resume.getUserId());
        String emailencode=MD5(user.getEmail())+"&";
        String url="https://yiguo.com/password/reset?";
        Resume_post_record resume_post_record =new Resume_post_record();
        resume_post_record.setResumeId(resumeid);
        List<Resume_post_record> resume_post_records= resume_post_recordService.select(resume_post_record,null);
        byte i=(byte)2;
        resume_post_records.get(0).setState(i);
        resume_post_recordService.updateByPrimaryKeySelective(resume_post_records.get(0));

        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setFrom(new InternetAddress(Sender, "offer100", "UTF-8"));
            helper.setTo(user.getEmail());
            helper.setSubject("来自于offer100");
            helper.setText("简历通知");
            Map<String, Object> model = new HashedMap();
            model.put("username", "你好，");
            model.put("text","您的简历正在筛选");
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
    @ApiOperation(value = "企业或用户查看投递情况",notes = "企业或用户查看投递情况")
    @ResponseBody
    @RequestMapping(value = "/manageResume", method ={RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", dataType = "string", paramType = "query", value = "岗位名关键字"),
            @ApiImplicitParam(name = "enterpriseId", value = "企业id", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "状态 1 已投递 2 待沟通 3 已邀请 4 已拒绝", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "条数", dataType = "int", paramType = "query")
    })
    public PageInfo<Map> manageResume(@RequestParam(required = false) String title, @RequestParam(required = false) Integer enterpriseId, @RequestParam(required = false) Integer userId, @RequestParam(required = false) Integer state,
                                      @RequestParam() Integer pageNumber, @RequestParam() Integer pageSize) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        PageInfo<Map> pageinfo=new PageInfo<Map>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        pageinfo.setRows( resume_post_recordService.selectBy(title,enterpriseId,userId,state,page.getStart(), page.getPageSize()));
        int count =resume_post_recordService.selectCounts(title,enterpriseId,userId,state);
        pageinfo.setTotal(count);
        return pageinfo;
    }

    @ApiOperation(value = "企业更新简历状态",notes = "企业更新简历状态")
    @ResponseBody
    @RequestMapping(value = "/manageResume", method ={RequestMethod.PUT})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "简历投递id", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "状态 1 已投递 2 待沟通 3 已邀请 4 已拒绝", dataType = "int", paramType = "query")
    })
    public void updateState(@RequestParam Integer id, @RequestParam Integer state) {
        Resume_post_record resume_post_record=new Resume_post_record();
        resume_post_record.setId(id);
        resume_post_record.setState(state.byteValue());
        resume_post_recordService.updateByPrimaryKeySelective(resume_post_record);
    }
}
