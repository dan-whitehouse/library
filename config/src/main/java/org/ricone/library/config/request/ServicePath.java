package org.ricone.library.config.request;

public enum ServicePath {
	GET_APP("app/{id}", ServicePathType.SINGLE),
	GET_APPS("app", ServicePathType.OBJECT),
	GET_APPS_BY_DISTRICT("district/{id}/app", ServicePathType.PREDICATE),
	GET_APPS_BY_PROFILE("profile/{id}/app", ServicePathType.PREDICATE),
	GET_APPS_BY_PROVIDER("provider/{id}/app", ServicePathType.PREDICATE),
	GET_APPS_BY_VENDOR("vendor/{id}/app", ServicePathType.PREDICATE),
	//
	GET_DISTRICT("district/{id}", ServicePathType.SINGLE),
	GET_DISTRICTS("district", ServicePathType.OBJECT),
	GET_DISTRICTS_BY_APP("app/{id}/district", ServicePathType.PREDICATE),
	GET_DISTRICTS_BY_PROVIDER("provider/{id}/district", ServicePathType.PREDICATE),
	//
	GET_PROFILE("profile/{id}", ServicePathType.SINGLE),
	GET_PROFILES("profile", ServicePathType.OBJECT),
	//
	GET_PROVIDER("provider/{id}", ServicePathType.SINGLE),
	GET_PROVIDERS("provider", ServicePathType.OBJECT),
	GET_PROVIDERS_BY_APP("app/{id}/provider", ServicePathType.PREDICATE),
	GET_PROVIDERS_BY_DISTRICT("district/{id}/provider", ServicePathType.PREDICATE),
	//
	GET_SCHOOL("school/{id}", ServicePathType.SINGLE),
	GET_SCHOOLS("school", ServicePathType.OBJECT),
	GET_SCHOOLS_BY_DISTRICT("district/{id}/school", ServicePathType.PREDICATE),
	;


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