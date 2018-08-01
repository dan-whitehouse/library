package org.ricone.library.config.request;

public enum ServicePath {

	APIKVS_BY_DISTRICT("district/{id}/apikv", ServicePathType.PREDICATE),
	APIKV_BY_PROVIDER("provider/{predicate_id}/apikv/{id}", ServicePathType.SINGLE_WITH_PREDICATE),
	APIKVS_BY_PROVIDER("provider/{id}/apikv", ServicePathType.PREDICATE),

	APP("app/{id}", ServicePathType.SINGLE),
	APPS("app", ServicePathType.OBJECT),
	APPS_BY_DISTRICT("district/{id}/app", ServicePathType.PREDICATE),
	APPS_BY_PROFILE("profile/{id}/app", ServicePathType.PREDICATE),
	APPS_BY_PROVIDER("provider/{id}/app", ServicePathType.PREDICATE),
	APPS_BY_VENDOR("vendor/{id}/app", ServicePathType.PREDICATE),
	//
	DISTRICT("district/{id}", ServicePathType.SINGLE),
	DISTRICTS("district", ServicePathType.OBJECT),
	DISTRICTS_BY_APP("app/{id}/district", ServicePathType.PREDICATE),
	DISTRICTS_BY_PROVIDER("provider/{id}/district", ServicePathType.PREDICATE),
	//
	PROFILE("profile/{id}", ServicePathType.SINGLE),
	PROFILES("profile", ServicePathType.OBJECT),
	//
	PROVIDER("provider/{id}", ServicePathType.SINGLE),
	PROVIDERS("provider", ServicePathType.OBJECT),
	PROVIDERS_BY_APP("app/{id}/provider", ServicePathType.PREDICATE),
	PROVIDERS_BY_DISTRICT("district/{id}/provider", ServicePathType.PREDICATE),
	//
	SCHOOL("school/{id}", ServicePathType.SINGLE),
	SCHOOLS("school", ServicePathType.OBJECT),
	SCHOOLS_BY_DISTRICT("district/{id}/school", ServicePathType.PREDICATE),
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