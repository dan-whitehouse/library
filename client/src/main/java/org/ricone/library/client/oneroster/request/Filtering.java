package org.ricone.library.client.oneroster.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Filtering {
	private List<Filter> filters;
	private LogicalOperation logicalOperation = LogicalOperation.NONE;

	protected Filtering() {
		filters = new ArrayList<>();
	}

	public Filtering(List<Filter> filters) {
		this.filters = filters;
	}

	public Filtering(List<Filter> filters, LogicalOperation logicalOperation) {
		this.filters = filters;
		this.logicalOperation = logicalOperation;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public LogicalOperation getLogicalOperation() {
		return logicalOperation;
	}

	public void setLogicalOperation(LogicalOperation logicalOperation) {
		this.logicalOperation = logicalOperation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Filtering filtering = (Filtering) o;
		return Objects.equals(filters, filtering.filters) &&
				logicalOperation == filtering.logicalOperation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(filters, logicalOperation);
	}

	@Override
	public String toString() {
		return "Filtering{" +
				"filters=" + filters +
				", logicalOperation=" + logicalOperation +
				'}';
	}
}
