package org.ricone.library.client.oneroster.request;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public enum Field implements IField {
	sourcedId("sourcedId", FieldType.All, FieldClassType.String),
	status("status", FieldType.All, FieldClassType.String),
	dateLastModified("dateLastModified", FieldType.All, FieldClassType.DateTime);

	private final String value;
	private final FieldType type;
	private final FieldClassType fieldClassType;

	Field(String value, FieldType type, FieldClassType fieldClassType) {
		this.value = value;
		this.type = type;
		this.fieldClassType = fieldClassType;
	}

	@Override
	public String getValue() {
		return value;
	}
	@Override
	public FieldType getType() {
		return type;
	}
	@Override
	public FieldClassType getFieldClassType() { return fieldClassType; }


	public enum Metadata implements IField {
		ricone_districtId("metadata.ricone.districtId", FieldType.All, FieldClassType.String),
		ricone_schoolYear("metadata.ricone.schoolYear", FieldType.All, FieldClassType.Number);

		private final String value;
		private final FieldType type2;
		private final FieldClassType fieldClassType2;
		Metadata(String value, FieldType type, FieldClassType fieldClassType) {
			this.value = value;
			this.type2 = type;
			this.fieldClassType2 = fieldClassType;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}
		@Override
		public FieldClassType getFieldClassType() { return fieldClassType2; }
	}

	public enum Orgs implements IField {
		name("name", FieldType.Orgs, FieldClassType.String),
		type("type", FieldType.Orgs, FieldClassType.String),
		identifier("identifier", FieldType.Orgs, FieldClassType.String);

		private final String value;
		private final FieldType type2;
		private final FieldClassType fieldClassType2;
		Orgs(String value, FieldType type, FieldClassType fieldClassType) {
			this.value = value;
			this.type2 = type;
			this.fieldClassType2 = fieldClassType;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}
		@Override
		public FieldClassType getFieldClassType() { return fieldClassType2; }

		public enum Parent implements IField {
			href("parent.href", FieldType.Orgs, FieldClassType.String),
			sourcedId("parent.sourcedId", FieldType.Orgs, FieldClassType.String),
			type("parent.type", FieldType.Orgs, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Parent(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

		public enum Children implements IField {
			href("children.href", FieldType.Orgs, FieldClassType.String),
			sourcedId("children.sourcedId", FieldType.Orgs, FieldClassType.String),
			type("children.type", FieldType.Orgs, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Children(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

	}

	public enum AcademicSessions implements IField {
		title("title", FieldType.AcademicSessions, FieldClassType.String),
		startDate("startDate", FieldType.AcademicSessions, FieldClassType.Date),
		endDate("endDate", FieldType.AcademicSessions, FieldClassType.Date),
		type("type", FieldType.AcademicSessions, FieldClassType.String);

		private final String value;
		private final FieldType type2;
		private final FieldClassType fieldClassType2;
		AcademicSessions(String value, FieldType type, FieldClassType fieldClassType) {
			this.value = value;
			this.type2 = type;
			this.fieldClassType2 = fieldClassType;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}
		@Override
		public FieldClassType getFieldClassType() { return fieldClassType2; }

		public enum Parent implements IField {
			href("parent.href", FieldType.AcademicSessions, FieldClassType.String),
			sourcedId("parent.sourcedId", FieldType.AcademicSessions, FieldClassType.String),
			type("parent.type", FieldType.AcademicSessions, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Parent(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

		public enum Children implements IField {
			href("children.href", FieldType.AcademicSessions, FieldClassType.String),
			sourcedId("children.sourcedId", FieldType.AcademicSessions, FieldClassType.String),
			type("children.type", FieldType.AcademicSessions, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Children(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}


	}

	public enum Courses implements IField {
		title("title", FieldType.Courses, FieldClassType.String),
		courseCode("courseCode", FieldType.Courses, FieldClassType.String),
		grades("grades", FieldType.Courses, FieldClassType.String),
		subjects("subjects", FieldType.Courses, FieldClassType.String),
		subjectCodes("subjectCodes", FieldType.Courses, FieldClassType.String);

		private final String value;
		private final FieldType type2;
		private final FieldClassType fieldClassType2;
		Courses(String value, FieldType type, FieldClassType fieldClassType) {
			this.value = value;
			this.type2 = type;
			this.fieldClassType2 = fieldClassType;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}
		@Override
		public FieldClassType getFieldClassType() { return fieldClassType2; }

		public enum SchoolYear implements IField {
			href("schoolYear.href", FieldType.Courses, FieldClassType.String),
			sourcedId("schoolYear.sourcedId", FieldType.Courses, FieldClassType.String),
			type("schoolYear.type", FieldType.Courses, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			SchoolYear(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

		public enum Org implements IField {
			href("org.href", FieldType.Courses, FieldClassType.String),
			sourcedId("org.sourcedId", FieldType.Courses, FieldClassType.String),
			type("org.type", FieldType.Courses, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Org(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}
	}

	public enum Classes implements IField {
		title("title", FieldType.Classes, FieldClassType.String),
		classCode("classCode", FieldType.Classes, FieldClassType.String),
		classType("classType", FieldType.Classes, FieldClassType.String),
		location("location", FieldType.Classes, FieldClassType.String),
		grades("grades", FieldType.Classes, FieldClassType.String),
		subjects("subjects", FieldType.Classes, FieldClassType.String),
		subjectCodes("subjectCodes", FieldType.Classes, FieldClassType.String),
		periods("periods", FieldType.Classes, FieldClassType.String);

		private final String value;
		private final FieldType type2;
		private final FieldClassType fieldClassType2;
		Classes(String value, FieldType type, FieldClassType fieldClassType) {
			this.value = value;
			this.type2 = type;
			this.fieldClassType2 = fieldClassType;
		}

		@Override
		public String getValue() { return value; }
		@Override
		public FieldType getType() {
			return type2;
		}
		@Override
		public FieldClassType getFieldClassType() { return fieldClassType2; }

		public enum Course implements IField {
			href("course.href", FieldType.Classes, FieldClassType.String),
			sourcedId("course.sourcedId", FieldType.Classes, FieldClassType.String),
			type("course.type", FieldType.Classes, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Course(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() { return value; }
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

		public enum School implements IField {
			href("school.href", FieldType.Classes, FieldClassType.String),
			sourcedId("school.sourcedId", FieldType.Classes, FieldClassType.String),
			type("school.type", FieldType.Classes, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			School(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() { return value; }
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

		public enum Terms implements IField {
			href("terms.href", FieldType.Classes, FieldClassType.String),
			sourcedId("terms.sourcedId", FieldType.Classes, FieldClassType.String),
			type("terms.type", FieldType.Classes, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Terms(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() { return value; }
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}
	}

	public enum Enrollments implements IField {
		role("role", FieldType.Enrollments, FieldClassType.String),
		primary("primary", FieldType.Enrollments, FieldClassType.Boolean),
		beginDate("beginDate", FieldType.Enrollments, FieldClassType.Date),
		endDate("endDate", FieldType.Enrollments, FieldClassType.Date);

		private final String value;
		private final FieldType type2;
		private final FieldClassType fieldClassType2;
		Enrollments(String value, FieldType type, FieldClassType fieldClassType) {
			this.value = value;
			this.type2 = type;
			this.fieldClassType2 = fieldClassType;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}
		@Override
		public FieldClassType getFieldClassType() { return fieldClassType2; }

		public enum User implements IField {
			href("user.href", FieldType.Enrollments, FieldClassType.String),
			sourcedId("user.sourcedId", FieldType.Enrollments, FieldClassType.String),
			type("user.type", FieldType.Enrollments, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			User(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

		public enum Clazz implements IField {
			href("class.href", FieldType.Enrollments, FieldClassType.String),
			sourcedId("class.sourcedId", FieldType.Enrollments, FieldClassType.String),
			type("class.type", FieldType.Enrollments, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Clazz(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

		public enum School implements IField {
			href("school.href", FieldType.Enrollments, FieldClassType.String),
			sourcedId("school.sourcedId", FieldType.Enrollments, FieldClassType.String),
			type("school.type", FieldType.Enrollments, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			School(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}
	}

	public enum Users implements IField {
		username("username", FieldType.Users, FieldClassType.String),
		enabledUser("enabledUser", FieldType.Users, FieldClassType.Boolean),
		givenName("givenName", FieldType.Users, FieldClassType.String),
		familyName("familyName", FieldType.Users, FieldClassType.String),
		middleName("middleName", FieldType.Users, FieldClassType.String),
		role("role", FieldType.Users, FieldClassType.String),
		identifier("identifier", FieldType.Users, FieldClassType.String),
		email("email", FieldType.Users, FieldClassType.String),
		sms("sms", FieldType.Users, FieldClassType.String),
		phone("phone", FieldType.Users, FieldClassType.String),
		grades("grades", FieldType.Users, FieldClassType.String),
		password("password", FieldType.Users, FieldClassType.String);

		private final String value;
		private final FieldType type2;
		private final FieldClassType fieldClassType2;
		Users(String value, FieldType type, FieldClassType fieldClassType) {
			this.value = value;
			this.type2 = type;
			this.fieldClassType2 = fieldClassType;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}
		@Override
		public FieldClassType getFieldClassType() { return fieldClassType2; }

		public enum UserIds implements IField {
			type("userIds.type", FieldType.Users, FieldClassType.String),
			identifier("userIds.identifier", FieldType.Users, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			UserIds(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

		public enum Agents implements IField {
			href("agents.href", FieldType.Users, FieldClassType.String),
			sourcedId("agents.sourcedId", FieldType.Users, FieldClassType.String),
			type("agents.type", FieldType.Users, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Agents(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}

		public enum Orgs implements IField {
			href("orgs.href", FieldType.Users, FieldClassType.String),
			sourcedId("orgs.sourcedId", FieldType.Users, FieldClassType.String),
			type("orgs.type", FieldType.Users, FieldClassType.String);

			private final String value;
			private final FieldType type2;
			private final FieldClassType fieldClassType2;
			Orgs(String value, FieldType type, FieldClassType fieldClassType) {
				this.value = value;
				this.type2 = type;
				this.fieldClassType2 = fieldClassType;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
			@Override
			public FieldClassType getFieldClassType() { return fieldClassType2; }
		}
	}

	public enum Demographics implements IField {
		birthDate("birthDate", FieldType.Demographics, FieldClassType.Date),
		sex("sex", FieldType.Demographics, FieldClassType.String),
		americanIndianOrAlaskaNative("americanIndianOrAlaskaNative", FieldType.Demographics, FieldClassType.Boolean),
		asian("asian", FieldType.Demographics, FieldClassType.Boolean),
		blackOrAfricanAmerican("blackOrAfricanAmerican", FieldType.Demographics, FieldClassType.Boolean),
		nativeHawaiianOrOtherPacificIslander("nativeHawaiianOrOtherPacificIslander", FieldType.Demographics, FieldClassType.Boolean),
		white("white", FieldType.Demographics, FieldClassType.Boolean),
		demographicRaceTwoOrMoreRaces("demographicRaceTwoOrMoreRaces", FieldType.Demographics, FieldClassType.Boolean),
		hispanicOrLatinoEthnicity("hispanicOrLatinoEthnicity", FieldType.Demographics, FieldClassType.Boolean),
		countryOfBirthCode("countryOfBirthCode", FieldType.Demographics, FieldClassType.String),
		stateOfBirthAbbreviation("stateOfBirthAbbreviation", FieldType.Demographics, FieldClassType.String),
		cityOfBirth("cityOfBirth", FieldType.Demographics, FieldClassType.String),
		publicSchoolResidenceStatus("publicSchoolResidenceStatus", FieldType.Demographics, FieldClassType.String);

		private final String value;
		private final FieldType type2;
		private final FieldClassType fieldClassType2;
		Demographics(String value, FieldType type, FieldClassType fieldClassType) {
			this.value = value;
			this.type2 = type;
			this.fieldClassType2 = fieldClassType;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}
		@Override
		public FieldClassType getFieldClassType() { return fieldClassType2; }
	}
}
