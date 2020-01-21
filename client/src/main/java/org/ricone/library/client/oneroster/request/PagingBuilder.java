package org.ricone.library.client.oneroster.request;

import java.util.function.Consumer;

final class PagingBuilder {

	private Paging paging;

	public static Builder builder() {
		return new PagingBuilder.Builder();
	}

	private PagingBuilder() {
	}

	public Paging getPaging() {
		return paging;
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
			instance.paging.setLimit(limit);
			return this;
		}

		public Builder offset(int offset) {
			instance.paging.setOffset(offset);
			return this;
		}

		public WithBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
		
	}		
}