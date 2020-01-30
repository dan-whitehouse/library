package org.ricone.library.client.oneroster.request;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

final class FilteringBuilder {
	private List<Filter> filters;
	private LogicalOperation logicalOperation;

	private FilteringBuilder() {
		this.filters = new ArrayList<>();
		this.logicalOperation = LogicalOperation.NONE;
	}

	public static Builder builder() {
		return new FilteringBuilder.Builder();
	}

	List<Filter> getFilters() {
		return filters;
	}

	LogicalOperation getLogicalOperation() {
		return logicalOperation;
	}

	public static class Builder {
		private FilteringBuilder instance = new FilteringBuilder();
		private WithBuilder.Builder parentBuilder;
		private Consumer<FilteringBuilder> callback;

		public Builder() {
		}

		public Builder(WithBuilder.Builder b, Consumer<FilteringBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}

		//Allows for the Filter to be set without needing to create a Filter object.
		public Builder filter(IField field, Predicate predicate, String value) {
			this.instance.getFilters().add(new Filter(field, predicate, value));
			return this;
		}

		/*	Allows for adding a Filter object directly to the filter list.
			The best use case for this is when doing paging, as it eliminates the need repeat values.
		 */
		public Builder filter(Filter filter) {
			this.instance.getFilters().add(filter);
			return this;
		}

		//If two filters are present, the and() option indicates that the filtering contains a logical operation and is: filter1 AND filter2
		public Builder and() {
			this.instance.logicalOperation = LogicalOperation.AND;
			return this;
		}

		//If two filters are present, the or() option indicates that the filtering contains a logical operation and is: filter1 OR filter2
		public Builder or() {
			this.instance.logicalOperation = LogicalOperation.OR;
			return this;
		}

		//Ends the use of the FilteringBuilder, and takes you up a level to the WithBuilder.
		public WithBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
	}		
}