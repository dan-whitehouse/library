package org.ricone.library.client.oneroster.response.model;

public enum ClassType implements Type {
	homeroom("homeroom"), //The homeroom (form) assigned to the class.
	scheduled("scheduled"); //The class as assigned in the timetable.

	private final String value;
	ClassType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
