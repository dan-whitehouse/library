package org.ricone.library.client.oneroster.request;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public enum SortOrder {
	ASC("asc"),
	DESC("desc");

	private final String value;
	SortOrder(String value) {this.value = value;}

	public String getValue() {
		return value;
	}
}
