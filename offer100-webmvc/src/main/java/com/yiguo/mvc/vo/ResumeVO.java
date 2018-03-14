package com.yiguo.mvc.vo;

import com.yiguo.bean.Education;
import com.yiguo.bean.ProjectExperience;
import com.yiguo.bean.Resume;
import com.yiguo.offer100.search.bean.SearchJob;

import java.util.List;

/**
 * 
 * Job实体类
 * 
 * @author liyue
 * @date 2018-01-23
 */
public class ResumeVO extends Resume {
	private List<Education> educations;
    private List<ProjectExperience> projectExperiences;

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<ProjectExperience> getProjectExperiences() {
        return projectExperiences;
    }

    public void setProjectExperiences(List<ProjectExperience> projectExperiences) {
        this.projectExperiences = projectExperiences;
    }
}