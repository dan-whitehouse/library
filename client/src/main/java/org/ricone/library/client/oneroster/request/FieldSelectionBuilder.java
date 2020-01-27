package org.ricone.library.client.oneroster.request;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

final class FieldSelectionBuilder {

	private List<IField> fields;

	public static Builder builder() {
		return new FieldSelectionBuilder.Builder();
	}

	private FieldSelectionBuilder() {
	}

	public List<IField> getFields() {
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
			if(instance.fields == null) {
				instance.fields = new ArrayList<>();
			}
			instance.fields.add(field);
			return this;
		}

		public WithBuilder.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
		
	}		
}