package org.ricone.library.client.oneroster.request;

public class Sorting {
	private IField field;
	private SortOrder orderBy;

	protected Sorting() {
		orderBy = SortOrder.ASC;
	}

	public Sorting(IField field, SortOrder orderBy) {
		this.field = field;
		this.orderBy = orderBy;
	}

	public IField getField() {
		return field;
	}

	public void setField(IField field) {
		this.field = field;
	}

	public SortOrder getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(SortOrder orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Sorting)) return false;

		Sorting sorting = (Sorting) o;

		if(field != null ? !field.equals(sorting.field) : sorting.field != null) return false;
		return orderBy == sorting.orderBy;
	}

	@Override
	public int hashCode() {
		int result = field != null ? field.hashCode() : 0;
		result = 31 * result + (orderBy != null ? orderBy.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Sorting{" + "field='" + field + '\'' + ", orderBy=" + orderBy + '}';
	}
}
