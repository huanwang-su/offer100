package com.yiguo.mvc.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * Job实体类
 * 
 * @author liyue
 * @date 2018-01-23
 */
public class JobVO implements Serializable {
	private Integer id;
	private Integer enterpriseId;
	private String enterpriseName;
	private String title;
	private String nature;
	private Integer industryId;
	private String industryName;
	private Integer zoneId;
	private String zoneName;
	private Date effectiveTime;
	private Integer wage;
	private String education;
	private String serviceYear;
	private String description;
	private String welfare;
	private String email;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getEnterpriseId() {
        return enterpriseId;
    }
    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public String getEnterpriseName() {
        return enterpriseName;
    }
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
    	
        this.title = title;
    }
    public String getNature() {
        return nature;
    }
    public void setNature(String nature) {
        this.nature = nature;
    }
    public Integer getIndustryId() {
        return industryId;
    }
    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }
   
    public Integer getZoneId() {
        return zoneId;
    }
    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }
   
    public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public Date getEffectiveTime() {
        return effectiveTime;
    }
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
    public Integer getWage() {
        return wage;
    }
    public void setWage(Integer wage) {
        this.wage = wage;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public String getServiceYear() {
        return serviceYear;
    }
    public void setServiceYear(String serviceYear) {
        this.serviceYear = serviceYear;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getWelfare() {
        return welfare;
    }
    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
	@Override
	public String toString() {
		return "JobVO [id=" + id + ", enterpriseId=" + enterpriseId + ", enterpriseName=" + enterpriseName + ", title="
				+ title + ", nature=" + nature + ", industryId=" + industryId + ", industryName=" + industryName
				+ ", zoneId=" + zoneId + ", zoneName=" + zoneName + ", effectiveTime=" + effectiveTime + ", wage="
				+ wage + ", education=" + education + ", serviceYear=" + serviceYear + ", description=" + description
				+ ", welfare=" + welfare + ", email=" + email + "]";
	}
	
}