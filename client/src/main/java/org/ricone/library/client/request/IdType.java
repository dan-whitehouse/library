package org.ricone.library.client.request;

public enum IdType {
	LOCAL("local"),
	BEDS("beds"),
	STATE("state");

	private final String value;

	IdType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}