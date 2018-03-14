package com.yiguo.mvc.controller;

import com.yiguo.bean.*;
import com.yiguo.mvc.vo.ResumeVO;
import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.alibaba.dubbo.monitor.MonitorService.SUCCESS;
import static com.alibaba.dubbo.monitor.MonitorService.FAILURE;

@Controller
@RestController
@Api(value = "简历接口")
@RequestMapping(value="/resume")     // 通过这里配置使下面的映射都在/resume下
public class ResumeController {
    @Autowired
    ResumeService resumeService;
    @Autowired
    EducationService educationService;
    @Autowired
    ProjectExperienceService projectExperienceService;
    @Autowired
    JobService jobService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    Resume_post_recordService resume_post_recordService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "用户获取自己简历列表",notes = "通过用户id获取简历列表")
    @ResponseBody
    @RequestMapping(value = "/getResumeList/{id}", method = RequestMethod.GET)
    public PageInfo<Resume> getResumeList(@ PathVariable Integer id,@RequestParam Integer pageSize,@RequestParam Integer pageNumber) {
        //List<User> r = new ArrayList<User>(users.values());
        PageInfo<Resume> pageinfo=new PageInfo<Resume>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        Resume resume=new Resume();
        resume.setUserId(id);
        pageinfo.setRows(resumeService.select(resume,page));
        pageinfo.setTotal(resumeService.selectCount(resume));
        return pageinfo;
    }

    @ApiOperation(value = "用户获取自己简历列表(包括教育，项目经历)")
    @ResponseBody
    @RequestMapping(value = "/getResumeVO/{id}", method = RequestMethod.GET)
    public PageInfo<ResumeVO> getResumeVoList(@ PathVariable Integer id, @RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        //List<User> r = new ArrayList<User>(users.values());
        PageInfo<ResumeVO> pageinfo=new PageInfo<>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        Resume resume=new Resume();
        resume.setUserId(id);
        List<ResumeVO> vos=new ArrayList<>();
        pageinfo.setRows(vos);
        resumeService.select(resume,page).forEach(r->{
            vos.add(resumeToVO(r));
        });
        pageinfo.setTotal(resumeService.selectCount(resume));
        return pageinfo;
    }

    @ApiOperation(value="下载简历文件", notes="上传文件")
    @ResponseBody
    @RequestMapping(value = "/downlownFile/{id}", method = {RequestMethod.GET})
    public PageInfo<String> downlownFile(@PathVariable Integer id,@RequestParam Integer pageSize,@RequestParam Integer pageNumber) {
        PageInfo<String> pageinfo=new PageInfo<String>();
        pageinfo.setPageNum(pageNumber);
        pageinfo.setPageSize(pageSize);
        Page page= new Page();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        Resume resume=new Resume();
        resume.setUserId(id);
        pageinfo.setRows(resumeService.selectByUserId(resume,page));

        pageinfo.setTotal(resumeService.selectCount(resume));
        return pageinfo;
    }
    @ApiOperation(value = "创建简历信息",notes = "根据resume对象创建resume")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String buildResume(@RequestBody Resume resume) {

         //数据库对多数字段有长度限制，超出长度应提示错误（）
        // String f=FAILURE;
        Integer count=  resumeService.insert(resume);
        if(count > 0) {
            return SUCCESS;
        }
        //System.out.println(f);
        return FAILURE;
    }


    @ApiOperation(value="查询简历信息", notes="根据url的id来获取简历详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resume getResume(@PathVariable Integer id) {
        Resume resume=new Resume();
        resume=resumeService.selectByPrimaryKey(id);
        return resume;
    }


    @ApiOperation(value="更新简历信息", notes="根据url的id来指定更新对象，并根据传过来的resume信息来更新简历详细信息")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateResume(@PathVariable Integer id, @RequestBody Resume resume) {
        if(resumeService.findById(id) > 0) {
            resume.setId(id);
            if(userService.findById(resume.getUserId()) > 0 ) {
                int num = resumeService.updateByPrimaryKeySelective(resume);
                if (num > 0) {
                    return SUCCESS;
                } else
                    return FAILURE;

            }
            return "this user_id does not exist";
        }
        return "this id does not exist";
    }

    @ApiOperation(value="删除简历信息", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteResume(@PathVariable Integer id) {
        if(resumeService.findById(id) > 0) {
        int num = resumeService.deleteByPrimaryKey(id);
        if(num > 0) {
            return SUCCESS;
        }else
            return FAILURE;
        }
        return "this id does not exist";
    }

    private ResumeVO resumeToVO(Resume resume){
        ResumeVO resumeVO = new ResumeVO();
        BeanUtils.copyProperties(resume,resumeVO);
        Education education =new Education();
        education.setUserId(resume.getUserId());
        resumeVO.setEducations(educationService.select(education,new Page()));
        ProjectExperience projectExperience=new ProjectExperience();
        projectExperience.setUserId(resume.getUserId());
        resumeVO.setProjectExperiences(projectExperienceService.select(projectExperience,new Page()));
        return resumeVO;
    }

    /*@ApiOperation(value = "resumeid",notes = "用户管理自己的简历")
    @ResponseBody
    @RequestMapping(value = "/maageUserResume/{id}", method ={RequestMethod.GET})
    public List<Resume> manageUserResume(@PathVariable Integer id) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递

        Page page=new Page();
        Resume resume=new Resume();
        resume.setId(id);
        List<Resume> resumes =resumeService.select(resume,page);

        return resumes;
    }
*/




    //求职者投递简历成功后，企业向求职者发送通知
  /*  @ApiOperation(value = "简历投递发通知",notes = "求职者投递简历成功后，企业发送通知给求职者")
    @ResponseBody
    @RequestMapping(value = "/{seekerId}/{jobId}/{type}/{resumeId}", method ={RequestMethod.GET})
    public String resumeDeliver(@PathVariable Integer seekerId,
                              @PathVariable Integer jobId,
                              @PathVariable Byte type,
                              @PathVariable Integer resumeId){

        Notification notification = new Notification();


        Resume_post_record resume_post_record = new Resume_post_record();
        resume_post_record.setResumeId(resumeId);
        resume_post_record.setJobId(jobId);
        resume_post_record.setState(Byte.parseByte("0"));

        if(resume_post_recordService.insert(resume_post_record) > 0){

            //resume_post_record中若是找到该份简历，则说明求职者简历投递成功，否则投递失败
            notification.setTitle("简历投递状态");
            notification.setSendTime(new Date());
            notification.setContext("简历投递成功");
            notification.setSenderId(jobService.selectByPrimaryKey(jobId).getEnterpriseId());
            notification.setRecieverId(seekerId);
            notification.setType(type);
            int num =  notificationService.insert(notification);
            System.out.println(num);
            return "resume was delivered successfully";
        }
        else
        {
            return "resume is fail to deliver";
        }
*/



}
