package org.ricone.library.client.oneroster.response.model;

public enum OrgType implements Type {
	department("department"), //Denotes a department. A department may be a subset in a school or a set of schools. Added in V1.1.
	school("school"), //Denotes a school. This is the unit of assignment for classes and enrollments.
	district("district"), //Denotes a school district. Added in V1.1.
	local("local"), //V1.0 instances will use this value to identify districts.
	state("state"), //Denotes a state level organization.
	national("national"); //Denotes a national level organization.

	private final String value;
	OrgType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
