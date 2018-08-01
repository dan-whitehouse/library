package org.ricone.library.config.request;

import org.springframework.util.StringUtils;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
class ConfigPathBase implements ConfigPath {
	private ServicePath servicePath;
	private String id;
	private String predicateId;

	ConfigPathBase() {
	}

	public void setServicePath(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPredicateId(String predicateId) { this.predicateId = predicateId; }

	@Override
	public ServicePath getServicePath() {
		return this.servicePath;
	}

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
	public boolean hasPredicateId() {
		return StringUtils.hasText(predicateId);
	}

	@Override
	public boolean isServicePathType(ServicePathType servicePathType) {
		return servicePath.getServicePathType().equals(servicePathType);
	}
}
