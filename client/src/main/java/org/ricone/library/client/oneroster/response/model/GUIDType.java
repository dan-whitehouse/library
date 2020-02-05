package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GUIDType implements Type {
	academicSession("academicSession"),
	category("category"),
	clazz("class"),
	course("course"),
	demographics("demographics"),
	enrollment("enrollment"),
	gradingPeriod("gradingPeriod"),
	lineItem("lineItem"),
	org("org"),
	resource("resource"),
	result("result"),
	student("student"),
	teacher("teacher"),
	term("term"),
	user("user");

	private final String value;
	GUIDType(String value) {
		this.value = value;
	}

	@Override @JsonValue
	public String getValue() {
		return value;
	}
}
