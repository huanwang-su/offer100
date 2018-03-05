package com.yiguo.offer100.search.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 人才搜索关键字
 *
 * @author wanghuan
 * @date 2018-01-19
 */
public class Talent implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Field
    private String id;
    @Field
    private List<String> expectZones;
    @Field
    private List<String> expectJobs;
    @Field
    private String currentLocation;
    @Field
    private Integer age;
    /**
     * 工作年限
     */
    @Field
    private Integer workAge;
    /**
     * 0 女 1 男
     */
    @Field
    private Integer sex;
    /**
     * 本科，大专，高中，硕士，博士
     */
    @Field
    private String education;
    @Field
    private Integer rank;
    /**
     * 仅用于关键字查询，目前保留字段，不保存solr
     */
    private List<String> key;
    /**
     * 仅用于年龄范围 不用@Field，不保存solr
     */
    private Integer maxAge;
    private Integer minAge;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<String> getExpectZones() {
        return expectZones;
    }
    public void setExpectZones(List<String> expectZones) {
        this.expectZones = expectZones;
    }
    public String getCurrentLocation() {
        return currentLocation;
    }
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getWorkAge() {
        return workAge;
    }
    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
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
    public Integer getMaxAge() {
        return maxAge;
    }
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }
    public Integer getMinAge() {
        return minAge;
    }
    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }
    
    public List<String> getExpectJobs() {
        return expectJobs;
    }
    public void setExpectJobs(List<String> expectJob) {
        this.expectJobs = expectJob;
    }
    @Override
    public String toString() {
        return "Talent [id=" + id + ", expectZones=" + expectZones + ", expectJob=" + expectJobs + ", currentLocation="
                + currentLocation + ", age=" + age + ", workAge=" + workAge + ", sex=" + sex + ", education="
                + education + ", rank=" + rank + "]";
    }
    
    
}
