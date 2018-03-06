package com.yiguo.offer100.common.page;
import java.io.Serializable;
import java.util.List;

/**
 * ${DESCRIPTION}
 * @author wanghuan
 * @create 2018-03-01 
 **/
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer pageNum=1;
    private Integer pageSize=30;
    private Integer total;
    private List<T> rows;

    public PageInfo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStart() {
        if(pageNum==null)
            pageNum=1;
        if(pageSize==null)
            pageSize=30;
        return (pageNum-1)*pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if(pageNum==null||pageNum<=1)
            this.pageNum = 1;
        else
            this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize==null||pageSize>=30)
            this.pageSize = 30;
        else
            this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", rows=" + rows +
                '}';
    }
}