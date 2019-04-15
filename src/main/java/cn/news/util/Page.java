package cn.news.util;

import java.util.List;

public class Page<T> {

	private int currentPageNo;//当前页
	private int totalCount;//总记录数
	private int totalPageCount;//总页数
	private int pageSize;//页大小
	private List<T> pageList;//当前页数据
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo>0) {
			this.currentPageNo = currentPageNo;
		}else {
			this.currentPageNo=1;
		}	
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if(totalCount>0) {
			this.totalCount = totalCount;
		}else {
			this.totalCount=0;
		}
		//计算总页数
		this.setTotalPageCount(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
	

	
	
	
}
