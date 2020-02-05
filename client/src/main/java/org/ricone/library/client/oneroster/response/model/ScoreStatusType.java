package org.ricone.library.client.oneroster.response.model;

public enum ScoreStatusType implements Type {
	exempt("exempt"), //The result is exempt i.e. this score does NOT contribute to any summative assessment.
	fullyGraded("fullyGraded"), //The result is fully graded.
	notSubmitted("notSubmitted"), //The result is not submitted.
	partiallyGraded("partiallyGraded"), //The result is partially graded. Further scoring will be undertaken and this score must NOT be used in summative assessment i.e. it must become 'fully graded'.
	submitted("submitted"); //The result is submitted. This is a FINAL score and can only be changed as part of a formal review process

	private final String value;
	ScoreStatusType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
