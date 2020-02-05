package org.ricone.library.client.oneroster.response.model;

public enum GenderType implements Type {
	male("male"), //Gender of Male.
	female("female"); //Gender of Female.

	private final String value;
	GenderType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
