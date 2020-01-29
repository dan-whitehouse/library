package org.ricone.library.client.oneroster.request;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
public class Paging {
	private Integer limit;
	private Integer offset;


	protected Paging() {
		this.limit = 100;
		this.offset = 0;
	}

	public Paging(Integer limit, Integer offset) {
		this.limit = limit;
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Paging that = (Paging) o;

		if(!limit.equals(that.limit)) return false;
		return offset.equals(that.offset);
	}

	@Override
	public int hashCode() {
		int result = limit.hashCode();
		result = 31 * result + offset.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Paging{" + "limit=" + limit + ", offset=" + offset + '}';
	}
}
