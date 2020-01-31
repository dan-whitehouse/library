package org.ricone.library.client.xpress.request;


import java.util.function.Consumer;

public final class XRequestBuilder {

	private ServicePath path;
	private String id;
	private IdType idType;

	public static Builder builder() {
		return new XRequestBuilder.Builder();
	}

	private XRequestBuilder() {
		this.idType = IdType.RefId;
	}

	ServicePath getPath() {
		return path;
	}

	String getId() {
		return id;
	}

	IdType getIdType() {
		return idType;
	}

	public static class Builder {
		private XRequestBuilder instance = new XRequestBuilder();
		private XRequest.Builder parentBuilder;
		private Consumer<XRequestBuilder> callback;

		public Builder() {
		}

		public Builder(XRequest.Builder b, Consumer<XRequestBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}


		public Builder path(ServicePath servicePath) {
			instance.path = servicePath;
			return this;
		}

		public Builder id(String id) {
			instance.id = id;
			return this;
		}

		public Builder id(String id, IdType type) {
			instance.id = id;
			instance.idType = type;
			return this;
		}

		public XRequest.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
		
	}		
}