package org.ricone.library.client.oneroster.request;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

final class IdBuilder {
	private List<String> ids;

	private IdBuilder() {
		this.ids = new LinkedList<>();
	}

	public static Builder builder() {
		return new IdBuilder.Builder();
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