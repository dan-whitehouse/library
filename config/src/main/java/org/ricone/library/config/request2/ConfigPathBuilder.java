package org.ricone.library.config.request2;

import org.ricone.library.exception.MissingArgumentException;
import org.springframework.http.HttpMethod;

public class ConfigPathBuilder extends PathVerifier {
	private ServicePath servicePath;
	private HttpMethod httpMethod;
	private String id;
	private String predicateId;
	private Object body;

	public ConfigPathBuilder(ServicePath servicePath, HttpMethod httpMethod) {
		this.servicePath = servicePath;
		this.httpMethod = httpMethod;
	}

	public ConfigPathBuilder id(String id) {
		this.id = id;
		return this;
	}

	public ConfigPathBuilder predicateId(String predicateId) {
		this.predicateId = predicateId;
		return this;
	}

	public ConfigPathBuilder body(Object body) {
		this.body = body;
		return this;
	}

	public ConfigPathBase build() throws MissingArgumentException, UnsupportedOperationException {
		ConfigPathBase configPathBase = new ConfigPathBase();
		configPathBase.setServicePath(this.servicePath);
		configPathBase.setHttpMethod(this.httpMethod);
		configPathBase.setId(this.id);
		configPathBase.setPredicateId(this.predicateId);
		configPathBase.setBody(this.body);

		if(isUnsupportedHttpMethod(configPathBase)) {
			throw new UnsupportedOperationException("ServicePath " + servicePath + " does not allow the use of HttpMethod." + httpMethod + " on " + this.getClass().getSimpleName());
		}

		if(hasIllegalArgument(configPathBase)) {
			throw new IllegalArgumentException("ServicePath " + servicePath + " does not allow the body method to be set on " + this.getClass().getSimpleName() + " when using HttpMethod." + httpMethod);
		}

		if(isMissingId(configPathBase)) {
			throw new MissingArgumentException("ServicePath " + servicePath + " requires the id method be set on " + this.getClass().getSimpleName() + ". Set a value or try a different ServicePath.");
		}

		if(isMissingBody(configPathBase)) {
			throw new MissingArgumentException("ServicePath " + servicePath + " requires the body method be set on " + this.getClass().getSimpleName() + " when using HttpMethod " + httpMethod);
		}
		return configPathBase;
	}

	@Override
	boolean isMissingId(ConfigPathBase request) {
		if(request.isServicePathType(ServicePathType.SINGLE) && !request.hasId()) {
			return true;
		}
		else if(request.isServicePathType(ServicePathType.SINGLE_WITH_PREDICATE) && !request.hasId()) {
			return true;
		}
		else if(request.isServicePathType(ServicePathType.SINGLE_WITH_PREDICATE) && !request.hasPredicateId()) {
			return true;
		}
		else if(request.isServicePathType(ServicePathType.MANY_WITH_PREDICATE) && !request.hasId()) {
			return true;
		}
		return false;
	}

	@Override
	boolean isMissingBody(ConfigPathBase request) {
		if(!request.hasBody()) {
			return request.isHttpMethodType(HttpMethod.POST) || request.isHttpMethodType(HttpMethod.PUT) || request.isHttpMethodType(HttpMethod.PATCH);
		}
		return false;
	}

	@Override
	boolean hasIllegalArgument(ConfigPathBase request) {
		if(request.hasBody()) {
			return request.isHttpMethodType(HttpMethod.GET) || request.isHttpMethodType(HttpMethod.DELETE) || request.isHttpMethodType(HttpMethod.HEAD);
		}
		return false;
	}

	@Override
	boolean isUnsupportedHttpMethod(ConfigPathBase request) {
		if(!request.hasHttpMethod()) {
			return true;
		}
		if(request.getHttpMethod().equals(HttpMethod.OPTIONS) || request.getHttpMethod().equals(HttpMethod.TRACE)) {
			return true;
		}
		return !request.containsHttpMethodType(httpMethod);
	}
}