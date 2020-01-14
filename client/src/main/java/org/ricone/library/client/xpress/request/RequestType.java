package org.ricone.library.client.xpress.request;

public enum RequestType {
	BASIC("XPressRequest"),
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