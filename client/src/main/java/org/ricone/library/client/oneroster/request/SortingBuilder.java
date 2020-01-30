package org.ricone.library.client.oneroster.request;

import java.util.function.Consumer;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

final class SortingBuilder {
	private IField field;
	private SortOrder orderBy;

	public static Builder builder() {
		return new SortingBuilder.Builder();
	}

	private SortingBuilder() {
		this.orderBy = SortOrder.ASC;
	}

	public IField getField() {
		return field;
	}
	public SortOrder getOrderBy() {
		return orderBy;
	}

	public static class Builder {
		private SortingBuilder instance = new SortingBuilder();
		private WithBuilder.Builder parentBuilder;
		private Consumer<SortingBuilder> callback;

		public Builder() {
		}

		public Builder(WithBuilder.Builder b, Consumer<SortingBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}

		public Builder field(IField field) {
			this.instance.field = field;
			return this;
		}

		public Builder orderBy(SortOrder sortOrder) {
			this.instance.orderBy = sortOrder;
			return this;
		}

		public WithBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
	}		
}