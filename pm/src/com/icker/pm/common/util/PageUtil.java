package com.icker.pm.common.util;

/**
 * 用于分页
 * @author Icker
 *
 */
public class PageUtil {
	public final Integer eachPageMaxSize = 3;		//每页最大显示数据条数
	public Integer currentPageNo = 1;		//当前页号
	public Integer totalPageNo;				//总页数（总页号）
	public Integer totalDataSize;			//总数据条数
	
	//便于mysql数据库搜索
	public int currentPageBeginNo = 0;
	public int currentPageEndNo;

	public PageUtil(Integer totalDataSize) {
		super();
		this.totalDataSize = totalDataSize;
	}
	
	/**
	 * 根据总数据条数和每页显示条数计算总页数
	 * @return
	 */
	public Integer getTotalPageNo(){
		if(totalDataSize % eachPageMaxSize == 0) {
			totalPageNo = totalDataSize / eachPageMaxSize;
		} else {
			totalPageNo = totalDataSize / eachPageMaxSize + 1;
		}
		return this.totalPageNo;
	}

	public Integer getTotalDataSize() {
		return totalDataSize;
	}

	public void setTotalDataSize(Integer totalDataSize) {
		this.totalDataSize = totalDataSize;
	}

	public Integer getEachPageMaxSize() {
		return eachPageMaxSize;
	}

	public void setTotalPageNo(Integer totalPageNo) {
		this.totalPageNo = totalPageNo;
	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getCurrentPageBeginNo() {
		return currentPageBeginNo;
	}

	public void setCurrentPageBeginNo(int currentPageBeginNo) {
		this.currentPageBeginNo = currentPageBeginNo;
	}

	public int getCurrentPageEndNo() {
		return currentPageEndNo;
	}

	public void setCurrentPageEndNo(int currentPageEndNo) {
		this.currentPageEndNo = currentPageEndNo;
	}
}
