package com.yiguo.offer100.search.bean;

import java.io.Serializable;
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

    public Job(){}

    private Job(Builder builder) {
        setId(builder.id);
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
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Job copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
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
        return builder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEnterpriseLogo() {
        return enterpriseLogo;
    }

    public void setEnterpriseLogo(String enterpriseLogo) {
        this.enterpriseLogo = enterpriseLogo;
    }


    public static final class Builder {
        private String id;
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

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
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

        public Job build() {
            return new Job(this);
        }
    }
}
