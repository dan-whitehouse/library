package org.ricone.library.client.xpress.request;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
public class PagingInfo {
	private Integer pageNumber;
	private Integer pageSize;

	public PagingInfo(Integer pageNumber, Integer pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	Integer getPageNumber() {
		return pageNumber;
	}

	void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		PagingInfo that = (PagingInfo) o;

		if(!pageNumber.equals(that.pageNumber)) return false;
		return pageSize.equals(that.pageSize);
	}

	@Override
	public int hashCode() {
		int result = pageNumber.hashCode();
		result = 31 * result + pageSize.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "PagingInfo{" + "pageNumber=" + pageNumber + ", pageSize=" + pageSize + '}';
	}
}
