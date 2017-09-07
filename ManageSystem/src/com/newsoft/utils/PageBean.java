package com.newsoft.utils;

import java.util.List;

/*
 * 
 *当前页
 *总页   
 * 信息总条数
 * 页最大条
 */
public class PageBean {

	private Integer currentPage;
	private Integer totalPage;
	private Integer pageSize;
	private Integer totalCount;
	private List list;

	public PageBean(Integer currentPage, Integer pageSize, Integer totalCount) {
		// TODO Auto-generated constructor stub
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;

		// 当前页面条数输入合法性判断
		if (currentPage == null || currentPage < 1) {
			this.currentPage = 1;
		}

		// 页面数据条数合法性判断
		if (pageSize == null) {
			this.pageSize = 4;
		}

		// 计算总页数
		this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;
		// 当前页面条数输入合法性判断
		if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}
	}

	// 计算条数开始索引
	public int getStart() {
		return (this.currentPage - 1) * this.pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
