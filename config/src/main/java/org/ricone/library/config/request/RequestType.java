package org.ricone.library.config.request;

public enum RequestType {
	GET("XPressRequest"),
	ID("XPressRequestID"),
	CHANGES_SINCE("XPressRequestChangesSince"),
	AUPP("XPressRequestAUPP");

	private final String value;

	public String getValue() {
		return value;
	}

	RequestType(String value) {
		this.value = value;
	}
}