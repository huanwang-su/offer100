package com.yiguo.bean;

/**
 * 
 * 分页的实体类
 * 
 * @author liyue
 * @date 2018-01-23
 */
public class Page {

	Integer start;
	Integer pageNumber;
	Integer pageSize;
	Integer totalPageCount;
	Integer total;

	public Integer getStart() {
		if (pageNumber != null) {
			if (pageNumber <= 1) {
				start = 0;
			} else {
				start = (pageNumber - 1) * pageSize;
			}
		}
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPageCount() {
		if (total != null && pageSize != null) {
			totalPageCount = total / pageSize + (total % pageSize == 0 ? 0 : 1);
		}
		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}