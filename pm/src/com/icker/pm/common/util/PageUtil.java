package com.icker.pm.common.util;

/**
 * 用于分页
 * @author Icker
 *
 */
public class PageUtil {
	public final Integer eachPageMaxSize = 10;		//每页最大显示数据条数
	public Integer currentPageNo = 1;		//当前页号
	public Integer totalPageNo;				//总页数（总页号）
	public Integer totalDataSize;			//总数据条数
	
	//便于mysql数据库搜索
	public int currentPageBeginNo = 0;
	public int currentPageEndNo;
	
	public PageUtil() {
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPageBeginNo;
		result = prime * result + currentPageEndNo;
		result = prime * result
				+ ((currentPageNo == null) ? 0 : currentPageNo.hashCode());
		result = prime * result
				+ ((eachPageMaxSize == null) ? 0 : eachPageMaxSize.hashCode());
		result = prime * result
				+ ((totalDataSize == null) ? 0 : totalDataSize.hashCode());
		result = prime * result
				+ ((totalPageNo == null) ? 0 : totalPageNo.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "PageUtil [eachPageMaxSize=" + eachPageMaxSize
				+ ", currentPageNo=" + currentPageNo + ", totalPageNo="
				+ totalPageNo + ", totalDataSize=" + totalDataSize
				+ ", currentPageBeginNo=" + currentPageBeginNo
				+ ", currentPageEndNo=" + currentPageEndNo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageUtil other = (PageUtil) obj;
		if (currentPageBeginNo != other.currentPageBeginNo)
			return false;
		if (currentPageEndNo != other.currentPageEndNo)
			return false;
		if (currentPageNo == null) {
			if (other.currentPageNo != null)
				return false;
		} else if (!currentPageNo.equals(other.currentPageNo))
			return false;
		if (eachPageMaxSize == null) {
			if (other.eachPageMaxSize != null)
				return false;
		} else if (!eachPageMaxSize.equals(other.eachPageMaxSize))
			return false;
		if (totalDataSize == null) {
			if (other.totalDataSize != null)
				return false;
		} else if (!totalDataSize.equals(other.totalDataSize))
			return false;
		if (totalPageNo == null) {
			if (other.totalPageNo != null)
				return false;
		} else if (!totalPageNo.equals(other.totalPageNo))
			return false;
		return true;
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
