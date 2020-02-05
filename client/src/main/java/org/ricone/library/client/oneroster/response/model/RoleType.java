package org.ricone.library.client.oneroster.response.model;

public enum RoleType implements Type {
	administrator("administrator"), //Administrator in the organization (e.g. School). May be used for enrollment.
	aide("aide"), //Someone who provides appropriate aide to the user but NOT also one of the other roles.
	guardian("guardian"), //Guardian of the user and NOT the Mother or Father. May also be a Relative.
	parent("parent"), //Mother or father of the user.
	proctor("proctor"), //Exam proctor. Added in V1.1. May be used for enrollment.
	relative("relative"), //A relative of the user and NOT the Mother or Father. May also be the Guardian.
	student("student"), //A student at a organization (e.g. School). May be used for enrollment.
	teacher("teacher"); //A Teacher at organization (e.g. School). May be used for enrollment.

	private final String value;
	RoleType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
