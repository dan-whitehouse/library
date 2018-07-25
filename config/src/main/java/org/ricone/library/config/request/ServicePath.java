package org.ricone.library.config.request;

public enum ServicePath {
    GET_LOGIN("Users/login", ServicePathType.SINGLE), GET_APP("app/{id}", ServicePathType.SINGLE), GET_APPS("app", ServicePathType.SINGLE), GET_APPS_BY_DISTRICT("district/{id}/app", ServicePathType.OBJECT);

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