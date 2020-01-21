package org.ricone.library.client.oneroster.request;

import java.util.function.Consumer;

final class FilteringBuilder {

	private Filtering filtering;

	public static Builder builder() {
		return new FilteringBuilder.Builder();
	}

	private FilteringBuilder() {
	}

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


		public Builder filter(IField field, Predicate predicate, String value) {
			instance.filtering.getFilters().add(new Filter(field, predicate, value));
			return this;
		}

		public Builder and() {
			instance.filtering.setLogicalOperation(LogicalOperation.AND);
			return this;
		}

		public Builder or() {
			instance.filtering.setLogicalOperation(LogicalOperation.OR);
			return this;
		}

		public WithBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
		
	}		
}