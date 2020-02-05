package org.ricone.library.client.oneroster.response.model;

public enum SessionType implements Type {
	gradingPeriod("gradingPeriod"), //Denotes a period over which some grade/result is to be awarded.
	semester("semester"), //Denotes a semester period. Typically there a two semesters per schoolYear.
	schoolYear("schoolYear"), //Denotes the school year.
	term("term"); //Denotes a term period. Typically there a three terms per schoolYear.

	private final String value;
	SessionType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
