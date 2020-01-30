package org.ricone.library.client.oneroster.request;

import java.util.function.Consumer;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

final class PagingBuilder {
	private int limit;
	private int offset;

	private PagingBuilder() {
		this.limit = 100;
		this.offset = 0;
	}

	public static Builder builder() {
		return new PagingBuilder.Builder();
	}

	int getLimit() {
		return limit;
	}

	int getOffset() {
		return offset;
	}

	public static class Builder {
		private PagingBuilder instance = new PagingBuilder();
		private WithBuilder.Builder parentBuilder;
		private Consumer<PagingBuilder> callback;

		public Builder() {
		}

		public Builder(WithBuilder.Builder b, Consumer<PagingBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}

		public Builder limit(int limit) {
			this.instance.limit = limit;
			return this;
		}

		public Builder offset(int offset) {
			this.instance.offset = offset;
			return this;
		}

		public WithBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
	}		
}