package org.ricone.library.client.oneroster.response.model;

public enum StatusType implements Type {
	active("active"), //An active record.
	tobedeleted("tobedeleted"); //Denotes that it is safe to delete the record.

	private final String value;
	StatusType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
