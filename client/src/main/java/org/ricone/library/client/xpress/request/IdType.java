package org.ricone.library.client.xpress.request;

public enum IdType {
	RefId("refId"),
	Local("local"),
	State("state"),
	BEDS("beds");

	private final String value;

	IdType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}