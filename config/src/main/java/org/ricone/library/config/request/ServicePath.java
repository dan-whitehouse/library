package org.ricone.library.config.request;

public enum ServicePath {
	GET_APP("app/{id}", ServicePathType.SINGLE),
	GET_APPS("app", ServicePathType.OBJECT),
	GET_APPS_BY_DISTRICT("district/{id}/app", ServicePathType.PREDICATE),
	GET_APPS_BY_PROFILE("profile/{id}/app", ServicePathType.PREDICATE),
	GET_APPS_BY_PROVIDER("provider/{id}/app", ServicePathType.PREDICATE),
	GET_APPS_BY_VENDOR("vendor/{id}/app", ServicePathType.PREDICATE);

	private final String value;
	private ServicePathType servicePathType;

	ServicePath(String value, ServicePathType servicePathType) {
		this.value = value;
		this.servicePathType = servicePathType;
	}

	public String getValue() {
		return value;
	}

	public ServicePathType getServicePathType() {
		return servicePathType;
	}
}