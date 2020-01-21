package org.ricone.library.client.oneroster.request;

import java.util.List;
import java.util.function.Consumer;

final class IdBuilder {
	private List<String> ids;

	public static Builder builder() {
		return new IdBuilder.Builder();
	}

	private IdBuilder() {
	}

	List<String> getIds() {
		return ids;
	}


	public static class Builder {
		private IdBuilder instance = new IdBuilder();
		private RequestBuilder.Builder parentBuilder;
		private Consumer<IdBuilder> callback;

		public Builder() {
		}

		public Builder(RequestBuilder.Builder b, Consumer<IdBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}

		public Builder id(String id) {
			instance.ids.add(id);
			return this;
		}

		public RequestBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
		
	}		
}