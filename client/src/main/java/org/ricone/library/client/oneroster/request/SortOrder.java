package org.ricone.library.client.oneroster.request;

public enum SortOrder {
	ASC("asc"),
	DESC("desc");

	private final String value;
	SortOrder(String value) {this.value = value;}

	public String getValue() {
		return value;
	}
}
