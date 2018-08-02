package org.ricone.library.config.request2;

import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
class ConfigPathBase implements ConfigPath {
	private ServicePath servicePath;
	private HttpMethod httpMethod;
	private String id;
	private String predicateId;
	private Object body;

	ConfigPathBase() {
	}

	public void setServicePath(ServicePath servicePath) { this.servicePath = servicePath; }

	public void setHttpMethod(HttpMethod httpMethod) { this.httpMethod = httpMethod; }

	public void setId(String id) {
		this.id = id;
	}

	public void setPredicateId(String predicateId) { this.predicateId = predicateId; }

	public void setBody(Object body) { this.body = body; }


	@Override
	public ServicePath getServicePath() {
		return this.servicePath;
	}

	@Override
	public HttpMethod getHttpMethod() { return this.httpMethod; }

	@Override
	public Object getBody() { return this.body; }

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getPredicateId() {
		return this.predicateId;
	}

	@Override
	public boolean hasId() {
		return StringUtils.hasText(id);
	}

	@Override
	public boolean hasHttpMethod() { return httpMethod != null; }

	@Override
	public boolean hasBody() {
		return body != null;
	}

	@Override
	public boolean hasPredicateId() {
		return StringUtils.hasText(predicateId);
	}

	@Override
	public boolean isServicePathType(ServicePathType servicePathType) {
		return servicePath.getServicePathType().equals(servicePathType);
	}

	@Override
	public boolean containsHttpMethodType(HttpMethod httpMethod) {
		return servicePath.getHttpMethodTypes().contains(httpMethod);
	}
}
