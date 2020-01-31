package org.ricone.library.client.xpress.request;

import java.util.function.Consumer;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-31
 */

final class XPagingBuilder {
	private int size;
	private int page;

	private XPagingBuilder() {
		this.size = 250;
		this.page = 1;
	}

	public static Builder builder() {
		return new XPagingBuilder.Builder();
	}

	int getSize() {
		return size;
	}

	int getPage() {
		return page;
	}

	public static class Builder {
		private XPagingBuilder instance = new XPagingBuilder();
		private XWithBuilder.Builder parentBuilder;
		private Consumer<XPagingBuilder> callback;

		public Builder() {
		}

		public Builder(XWithBuilder.Builder b, Consumer<XPagingBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}

		public Builder size(int size) {
			this.instance.size = size;
			return this;
		}

		public Builder page(int page) {
			this.instance.page = page;
			return this;
		}

		public XWithBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
	}		
}