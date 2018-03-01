package com.yiguo.mvc.vo;

/**
 * 
 * Enterprise接口
 * 
 * @author liyue
 * @date 2018-01-23
 */
public class EnterpriseVO{
	


	private Integer id;
	private String imageLog;
	private String name;
	private String description;
    private Integer jobCount;
    private String stage;
    private Float  resumeHanderRate;
    private String IndustryName;

	public String getIndustryName() {
		return IndustryName;
	}

	public void setIndustryName(String industryName) {
		IndustryName = industryName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	
	public String getImageLog() {
		return imageLog;
	}
	public void setImageLog(String imageLog) {
		this.imageLog = imageLog;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getJobCount() {
		return jobCount;
	}
	public void setJobCount(Integer jobCount) {
		this.jobCount = jobCount;
	}
	public Float getResumeHanderRate() {
		return resumeHanderRate;
	}
	public void setResumeHanderRate(Float resumeHanderRate) {
		this.resumeHanderRate = resumeHanderRate;
	}
	@Override
	public String toString() {
		return "EnterpriseVO [imageLog=" + imageLog + ", name=" + name + ", description=" + description + ", jobCount="
				+ jobCount + ", stage=" + stage + ", resumeHanderRate=" + resumeHanderRate + "]";
	}
	
}