package org.ricone.library.client.xpress.request;

public enum AUPPType {
	CREATE("createUsers"),
	DELETE("deleteUsers"),
	GET("getUsers");

	private final String value;

	AUPPType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}