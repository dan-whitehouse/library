package org.ricone.library.client.oneroster.request;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

final class FieldSelectionBuilder {
	private List<IField> fields;

	private FieldSelectionBuilder() {
		this.fields = new ArrayList<>();
	}

	public static Builder builder() {
		return new FieldSelectionBuilder.Builder();
	}

	List<IField> getFields() {
		return fields;
	}

	public static class Builder {
		private FieldSelectionBuilder instance = new FieldSelectionBuilder();
		private WithBuilder.Builder parentBuilder;
		private Consumer<FieldSelectionBuilder> callback;

		public Builder() {
		}

		public Builder(WithBuilder.Builder b, Consumer<FieldSelectionBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}

		public Builder field(IField field) {
			this.instance.fields.add(field);
			return this;
		}

		public WithBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
	}		
}