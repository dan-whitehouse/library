package org.ricone.library.client.oneroster.request;

import org.ricone.library.client.xpress.request.IdType;
import org.ricone.library.client.xpress.request.RequestType;
import org.ricone.library.client.xpress.request.ServicePath;
import org.ricone.library.exception.InvalidPathException;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Request {
	private RequestBuilder request;
	private WithBuilder with;

	public static Builder builder() {
		return new Builder();
	}

	private Request() {
	}

	RequestBuilder request() {
		return request;
	}

	WithBuilder with() {
		return with;
	}

	public static class Builder {
		private Request instance = new Request();

		public RequestBuilder.Builder request() {
			Consumer<RequestBuilder> f = obj -> {
				instance.request = obj;
			};
			return new RequestBuilder.Builder(this, f);
		}

		public WithBuilder.Builder with() {
			Consumer<WithBuilder> f = obj -> {
				instance.with = obj;
			};
			return new WithBuilder.Builder(this, f);
		}

		public Request build() throws InvalidPathException {
			return instance;
		}
	}

	boolean hasId() {
		if(request() == null) {
			return false;
		}
		return request().ids() != null && request().ids().getIds().size() == 1;
	}

	boolean hasIds() {
		if(request() == null) {
			return false;
		}
		return request().ids() != null && request().ids().getIds().size() == 2;
	}

	boolean hasPaging() {
		if(with() == null) {
			return false;
		}
		return with().paging() != null;
	}

	boolean hasSorting() {
		if(with() == null) {
			return false;
		}
		return with().sorting() != null;
	}

	boolean hasFieldSelection() {
		if(with() == null) {
			return false;
		}
		return with().fieldSelection() != null;
	}

	boolean hasFiltering() {
		if(with() == null || with().filtering() == null) {
			return false;
		}
		return with().filtering().getFiltering() != null;
	}
}
