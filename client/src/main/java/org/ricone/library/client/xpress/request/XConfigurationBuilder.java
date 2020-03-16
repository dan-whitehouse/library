package org.ricone.library.client.xpress.request;


import java.util.function.Consumer;

final class XConfigurationBuilder {
	private boolean withAsQueryParameter;

	public static Builder builder() {
		return new XConfigurationBuilder.Builder();
	}
	XConfigurationBuilder() {
		this.withAsQueryParameter = false;
	}

	boolean withAsQueryParameters() {
		return withAsQueryParameter;
	}

	boolean withAsHeaders() {
		return !withAsQueryParameter;
	}

	public static class Builder {
		private XConfigurationBuilder instance = new XConfigurationBuilder();
		private XRequest.Builder parentBuilder;
		private Consumer<XConfigurationBuilder> callback;

		public Builder() { }

		public Builder(XRequest.Builder b, Consumer<XConfigurationBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}

		public Builder withAsQueryParameter(boolean withAsQueryParameter) {
			instance.withAsQueryParameter = withAsQueryParameter;
			return this;
		}

		public XRequest.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}

	}
}