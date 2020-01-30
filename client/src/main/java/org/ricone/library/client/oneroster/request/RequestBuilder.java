package org.ricone.library.client.oneroster.request;

import java.util.function.Consumer;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

final class RequestBuilder {
	private ServicePath path;
	private IdBuilder ids;

	private RequestBuilder() { }

	public static Builder builder() {
		return new RequestBuilder.Builder();
	}

	public ServicePath path() {
		return path;
	}

	public IdBuilder ids() {
		return ids;
	}

	public static class Builder {
		private RequestBuilder instance = new RequestBuilder();
		private Request.Builder parentBuilder;
		private Consumer<RequestBuilder> callback;

		public Builder() {
		}

		public Builder(Request.Builder b, Consumer<RequestBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}

		public Builder path(ServicePath servicePath) {
			instance.path = servicePath;
			return this;
		}

		public IdBuilder.Builder ids() {
			Consumer<IdBuilder> f = obj -> instance.ids = obj;
			return new IdBuilder.Builder(this, f);
		}

		public Request.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
	}
}