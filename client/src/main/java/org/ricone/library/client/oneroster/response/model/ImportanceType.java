package org.ricone.library.client.oneroster.response.model;

public enum ImportanceType implements Type {
	primary("primary"), //A resource of primary usage.
	secondary("secondary"); //A resource of secondary usage/significance.

	private final String value;
	ImportanceType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
