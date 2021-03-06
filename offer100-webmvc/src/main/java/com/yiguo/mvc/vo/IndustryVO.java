package com.yiguo.mvc.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * Industry实体类
 * 
 * @author liyue
 * @date 2018-01-23
 */
public class IndustryVO {
    private Integer id;

    private String name;

    private String label;

    private String description;

    private Integer parentId;

    private Integer level;

    private List<IndustryVO> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<IndustryVO> getchildren() {
        return children;
    }

    public void setChildren(List<IndustryVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "IndustryVO [id=" + id + ", name=" + name + ", children=" + children + "]";
    }

}