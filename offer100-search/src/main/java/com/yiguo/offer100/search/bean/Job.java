package com.yiguo.offer100.search.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 查找的岗位实体类
 *
 * @author wanghuan
 * @date 2018-01-17
 */
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    @Field
    private String id;
    @Field
    private String enterpriseId;
    @Field
    private String enterprise;
    @Field
    private String title;
    @Field
    private String nature;
    @Field
    private String zone;
    @Field
    private List<String> category;
    @Field
    private Integer wage;
    /**
     * 本科，大专，高中，硕士，博士
     */
    @Field
    private String education;
    @Field
    private String enterpriseLogo;
    @Field
    private Integer rank;
    /**
     * 仅用于关键字查询，目前只考虑查询公司和职位的组合查询 不用@Field，不保存solr
     */
    private List<String> key;
    @Field
    private Long publishTime;
    private String publishDaysBefore;
    /**
     * 工作年限
     */
    @Field
    private Integer serviceYear;

    @Field
    private Integer peopleNumber;
    @Field
    private String enterpriseCategory;
    @Field
    private String welfare;
    public Job(){}

    private Job(Builder builder) {
        setId(builder.id);
        setEnterpriseId(builder.enterpriseId);
        setEnterprise(builder.enterprise);
        setTitle(builder.title);
        setNature(builder.nature);
        setZone(builder.zone);
        setCategory(builder.category);
        setWage(builder.wage);
        setEducation(builder.education);
        setEnterpriseLogo(builder.enterpriseLogo);
        setRank(builder.rank);
        setKey(builder.key);
        setPublishTime(builder.publishTime);
        setServiceYear(builder.serviceYear);
        setPeopleNumber(builder.peopleNumber);
        setEnterpriseCategory(builder.enterpriseCategory);
        setWelfare(builder.welfare);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Job copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.enterpriseId = copy.getEnterpriseId();
        builder.enterprise = copy.getEnterprise();
        builder.title = copy.getTitle();
        builder.nature = copy.getNature();
        builder.zone = copy.getZone();
        builder.category = copy.getCategory();
        builder.wage = copy.getWage();
        builder.education = copy.getEducation();
        builder.enterpriseLogo = copy.getEnterpriseLogo();
        builder.rank = copy.getRank();
        builder.key = copy.getKey();
        builder.publishTime = copy.getPublishTime();
        builder.serviceYear = copy.getServiceYear();
        builder.peopleNumber = copy.getPeopleNumber();
        builder.enterpriseCategory = copy.getEnterpriseCategory();
        builder.welfare = copy.getWelfare();
        return builder;
    }

    public String getPublishDaysBefore() {
        if (publishDaysBefore==null){
            if (publishTime!=null){
                Period period = Period.between(LocalDate.ofEpochDay(publishTime), LocalDate.now());
                if(period.getYears()>0)
                    publishDaysBefore = period.getYears()+"年前";
                else if(period.getMonths()>0)
                    publishDaysBefore = period.getMonths()+"个月前";
                else if(period.getDays()>0)
                    publishDaysBefore = period.getDays()+"天前";
                else
                    publishDaysBefore = "今天";
            }
        }
        return publishDaysBefore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
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

    public String getEnterpriseLogo() {
        return enterpriseLogo;
    }

    public void setEnterpriseLogo(String enterpriseLogo) {
        this.enterpriseLogo = enterpriseLogo;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getServiceYear() {
        return serviceYear;
    }

    public void setServiceYear(Integer serviceYear) {
        this.serviceYear = serviceYear;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public String getEnterpriseCategory() {
        return enterpriseCategory;
    }

    public void setEnterpriseCategory(String enterpriseCategory) {
        this.enterpriseCategory = enterpriseCategory;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }


    public static final class Builder {
        private String id;
        private String enterpriseId;
        private String enterprise;
        private String title;
        private String nature;
        private String zone;
        private List<String> category;
        private Integer wage;
        private String education;
        private String enterpriseLogo;
        private Integer rank;
        private List<String> key;
        private Long publishTime;
        private Integer serviceYear;
        private Integer peopleNumber;
        private String enterpriseCategory;
        private String welfare;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder enterpriseId(String val) {
            enterpriseId = val;
            return this;
        }

        public Builder enterprise(String val) {
            enterprise = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder nature(String val) {
            nature = val;
            return this;
        }

        public Builder zone(String val) {
            zone = val;
            return this;
        }

        public Builder category(List<String> val) {
            category = val;
            return this;
        }

        public Builder wage(Integer val) {
            wage = val;
            return this;
        }

        public Builder education(String val) {
            education = val;
            return this;
        }

        public Builder enterpriseLogo(String val) {
            enterpriseLogo = val;
            return this;
        }

        public Builder rank(Integer val) {
            rank = val;
            return this;
        }

        public Builder key(List<String> val) {
            key = val;
            return this;
        }

        public Builder publishTime(Long val) {
            publishTime = val;
            return this;
        }

        public Builder serviceYear(Integer val) {
            serviceYear = val;
            return this;
        }

        public Builder peopleNumber(Integer val) {
            peopleNumber = val;
            return this;
        }

        public Builder enterpriseCategory(String val) {
            enterpriseCategory = val;
            return this;
        }

        public Builder welfare(String val) {
            welfare = val;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }
}
