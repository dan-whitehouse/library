package org.ricone.library.client.oneroster.request;

import java.util.function.Consumer;

final class FilteringBuilder {
	private Filtering filtering;

	public static Builder builder() {
		return new FilteringBuilder.Builder();
	}
	private FilteringBuilder() { }
	public Filtering getFiltering() {
		return filtering;
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
			if(instance.filtering == null) {
				instance.filtering = new Filtering();
			}
			instance.filtering.getFilters().add(new Filter(field, predicate, value));
			return this;
		}

		/*	Allows for adding a Filter object directly to the filter list.
			The best use case for this is when doing paging, as it eliminates the need repeat values.
		 */
		public Builder filter(Filter filter) {
			if(instance.filtering == null) {
				instance.filtering = new Filtering();
			}
			instance.filtering.getFilters().add(filter);
			return this;
		}

		//If two filters are present, the and() option indicates that the filtering contains a logical operation and is: filter1 AND filter2
		public Builder and() {
			if(instance.filtering == null) {
				instance.filtering = new Filtering();
			}
			instance.filtering.setLogicalOperation(LogicalOperation.AND);
			return this;
		}

		//If two filters are present, the or() option indicates that the filtering contains a logical operation and is: filter1 OR filter2
		public Builder or() {
			if(instance.filtering == null) {
				instance.filtering = new Filtering();
			}
			instance.filtering.setLogicalOperation(LogicalOperation.OR);
			return this;
		}

		//Ends the use of the FilteringBuilder, and takes you up a level to the WithBuilder.
		public WithBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
		
	}		
}