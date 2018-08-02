package org.ricone.library.config.request2;

import org.springframework.http.HttpMethod;

import java.util.List;

public enum ServicePath {

	APIKV_BY_DISTRICT("district/{predicate_id}/apikv/{id}", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)),
	APIKVS_BY_DISTRICT("district/{id}/apikv", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)),
	APIKV_BY_PROVIDER("provider/{predicate_id}/apikv/{id}", ServicePathType.SINGLE_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)),
	APIKVS_BY_PROVIDER("provider/{id}/apikv", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)),

	APP("app/{id}", ServicePathType.SINGLE, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.HEAD, HttpMethod.DELETE)),
	APPS("app", ServicePathType.MANY, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH)),
	APPS_BY_DISTRICT("district/{id}/app", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)),
	APPS_BY_PROFILE("profile/{id}/app", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)),
	APPS_BY_PROVIDER("provider/{id}/app", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)),
	APPS_BY_VENDOR("vendor/{id}/app", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)),
	//
	DISTRICT("district/{id}", ServicePathType.SINGLE, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.HEAD, HttpMethod.DELETE)),
	DISTRICTS("district", ServicePathType.MANY, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH)),
	DISTRICTS_BY_APP("app/{id}/district", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)),
	DISTRICTS_BY_PROVIDER("provider/{id}/district", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)),
	//
	PROFILE("profile/{id}", ServicePathType.SINGLE, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.HEAD, HttpMethod.DELETE)),
	PROFILES("profile", ServicePathType.MANY, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH)),
	//
	PROVIDER("provider/{id}", ServicePathType.SINGLE, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.HEAD, HttpMethod.DELETE)),
	PROVIDERS("provider", ServicePathType.MANY, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH)),
	PROVIDERS_BY_APP("app/{id}/provider", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET)),
	PROVIDERS_BY_DISTRICT("district/{id}/provider", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET)),
	//
	SCHOOL("school/{id}", ServicePathType.SINGLE, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.HEAD, HttpMethod.DELETE)),
	SCHOOLS("school", ServicePathType.MANY, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH)),
	SCHOOLS_BY_DISTRICT("district/{id}/school", ServicePathType.MANY_WITH_PREDICATE, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)),

	VENDOR("vendor/{id}", ServicePathType.SINGLE, List.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.HEAD, HttpMethod.DELETE)),
	VENDORS("vendor", ServicePathType.MANY, List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH)),
	;


	private final String value;
	private ServicePathType servicePathType;
	private List<HttpMethod> methodTypes;

	ServicePath(String value, ServicePathType servicePathType, List<HttpMethod> methodTypes) {
		this.value = value;
		this.servicePathType = servicePathType;
		this.methodTypes = methodTypes;
	}

	public String getValue() {
		return value;
	}

	public ServicePathType getServicePathType() {
		return servicePathType;
	}

	public List<HttpMethod> getHttpMethodTypes() {
		return methodTypes;
	}
}