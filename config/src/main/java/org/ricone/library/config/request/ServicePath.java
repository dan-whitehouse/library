package org.ricone.library.config.request;

public enum ServicePath {

	APIKV_BY_DISTRICT("district/{predicate_id}/apikv/{id}", ServicePathType.MANY_WITH_PREDICATE),
	APIKVS_BY_DISTRICT("district/{id}/apikv", ServicePathType.MANY_WITH_PREDICATE),
	APIKV_BY_PROVIDER("provider/{predicate_id}/apikv/{id}", ServicePathType.SINGLE_WITH_PREDICATE),
	APIKVS_BY_PROVIDER("provider/{id}/apikv", ServicePathType.MANY_WITH_PREDICATE),

	APP("app/{id}", ServicePathType.SINGLE),
	APPS("app", ServicePathType.MANY),
	APPS_BY_DISTRICT("district/{id}/app", ServicePathType.MANY_WITH_PREDICATE),
	APPS_BY_PROFILE("profile/{id}/app", ServicePathType.MANY_WITH_PREDICATE),
	APPS_BY_PROVIDER("provider/{id}/app", ServicePathType.MANY_WITH_PREDICATE),
	APPS_BY_VENDOR("vendor/{id}/app", ServicePathType.MANY_WITH_PREDICATE),
	//
	DISTRICT("district/{id}", ServicePathType.SINGLE),
	DISTRICTS("district", ServicePathType.MANY),
	DISTRICTS_BY_APP("app/{id}/district", ServicePathType.MANY_WITH_PREDICATE),
	DISTRICTS_BY_PROVIDER("provider/{id}/district", ServicePathType.MANY_WITH_PREDICATE),
	//
	PROFILE("profile/{id}", ServicePathType.SINGLE),
	PROFILES("profile", ServicePathType.MANY),
	//
	PROVIDER("provider/{id}", ServicePathType.SINGLE),
	PROVIDERS("provider", ServicePathType.MANY),
	PROVIDERS_BY_APP("app/{id}/provider", ServicePathType.MANY_WITH_PREDICATE),
	PROVIDERS_BY_DISTRICT("district/{id}/provider", ServicePathType.MANY_WITH_PREDICATE),
	//
	SCHOOL("school/{id}", ServicePathType.SINGLE),
	SCHOOLS("school", ServicePathType.MANY),
	SCHOOLS_BY_DISTRICT("district/{id}/school", ServicePathType.MANY_WITH_PREDICATE),

	VENDOR("vendor/{id}", ServicePathType.SINGLE),
	VENDORS("vendor", ServicePathType.MANY),
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