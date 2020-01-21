package org.ricone.library.client.oneroster.request;

import java.util.function.Consumer;

final class WithBuilder {

	private PagingBuilder paging;
	private SortingBuilder sorting;
	private FieldSelectionBuilder fieldSelection;
	private FilteringBuilder filtering;

	public static Builder builder() {
		return new WithBuilder.Builder();
	}

	private WithBuilder() {
	}

	public PagingBuilder paging() {
		return paging;
	}

	public SortingBuilder sorting() {
		return sorting;
	}

	public FieldSelectionBuilder fieldSelection() {
		return fieldSelection;
	}

	public FilteringBuilder filtering() {
		return filtering;
	}

	public static class Builder {
		private WithBuilder instance = new WithBuilder();
		private Request.Builder parentBuilder;
		private Consumer<WithBuilder> callback;

		public Builder() {
		}

		public Builder(Request.Builder b, Consumer<WithBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}

		public PagingBuilder.Builder paging() {
			Consumer<PagingBuilder> f = obj -> { instance.paging = obj;};
			return new PagingBuilder.Builder(this, f);
		}

		public SortingBuilder.Builder sorting() {
			Consumer<SortingBuilder> f = obj -> { instance.sorting = obj;};
			return new SortingBuilder.Builder(this, f);
		}

		public FieldSelectionBuilder.Builder fieldSelection() {
			Consumer<FieldSelectionBuilder> f = obj -> { instance.fieldSelection = obj;};
			return new FieldSelectionBuilder.Builder(this, f);
		}

		public FilteringBuilder.Builder filtering() {
			Consumer<FilteringBuilder> f = obj -> { instance.filtering = obj;};
			return new FilteringBuilder.Builder(this, f);
		}
		
		public Request.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
	}		
}