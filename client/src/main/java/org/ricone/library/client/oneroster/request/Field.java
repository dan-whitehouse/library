package org.ricone.library.client.oneroster.request;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public enum Field implements IField {
	sourcedId("sourcedId", FieldType.All),
	status("status", FieldType.All),
	dateLastModified("dateLastModified", FieldType.All),
	metadata("metadata", FieldType.All);

	private final String value;
	private final FieldType type;

	Field(String value, FieldType type) {
		this.value = value;
		this.type = type;
	}

	@Override
	public String getValue() {
		return value;
	}
	@Override
	public FieldType getType() {
		return type;
	}

	public enum Orgs implements IField {
		name("name", FieldType.Orgs),
		type("type", FieldType.Orgs),
		identifier("identifier", FieldType.Orgs);

		private final String value;
		private final FieldType type2;
		Orgs(String value, FieldType type) {
			this.value = value;
			this.type2 = type;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}

		public enum Parent implements IField {
			href("parent.href", FieldType.Orgs),
			sourcedId("parent.sourcedId", FieldType.Orgs),
			type("parent.identifier", FieldType.Orgs);

			private final String value;
			private final FieldType type2;
			Parent(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}

		public enum Children implements IField {
			href("children.href", FieldType.Orgs),
			sourcedId("children.sourcedId", FieldType.Orgs),
			type("children.identifier", FieldType.Orgs);

			private final String value;
			private final FieldType type2;

			Children(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}

	}

	public enum AcademicSessions implements IField {
		title("title", FieldType.AcademicSessions),
		startDate("startDate", FieldType.AcademicSessions),
		endDate("endDate", FieldType.AcademicSessions),
		type("type", FieldType.AcademicSessions);

		private final String value;
		private final FieldType type2;

		AcademicSessions(String value, FieldType type) {
			this.value = value;
			this.type2 = type;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}

		public enum Parent implements IField {
			href("parent.href", FieldType.AcademicSessions),
			sourcedId("parent.sourcedId", FieldType.AcademicSessions),
			type("parent.identifier", FieldType.AcademicSessions);

			private final String value;
			private final FieldType type2;

			Parent(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}

		public enum Children implements IField {
			href("children.href", FieldType.AcademicSessions),
			sourcedId("children.sourcedId", FieldType.AcademicSessions),
			type("children.identifier", FieldType.AcademicSessions);

			private final String value;
			private final FieldType type2;

			Children(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}


	}

	public enum Courses implements IField {
		title("title", FieldType.Courses),
		courseCode("courseCode", FieldType.Courses),
		grades("grades", FieldType.Courses),
		subjects("subjects", FieldType.Courses),
		subjectCodes("subjectCodes", FieldType.Courses);

		private final String value;
		private final FieldType type2;

		Courses(String value, FieldType type) {
			this.value = value;
			this.type2 = type;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}

		public enum SchoolYear implements IField {
			href("schoolYear.href", FieldType.Courses),
			sourcedId("schoolYear.sourcedId", FieldType.Courses),
			type("schoolYear.identifier", FieldType.Courses);

			private final String value;
			private final FieldType type2;

			SchoolYear(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}

		public enum Org implements IField {
			href("org.href", FieldType.Courses),
			sourcedId("org.sourcedId", FieldType.Courses),
			type("org.identifier", FieldType.Courses);

			private final String value;
			private final FieldType type2;

			Org(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}
	}

	public enum Classes implements IField {
		title("title", FieldType.Classes),
		classCode("classCode", FieldType.Classes),
		classType("classType", FieldType.Classes),
		location("location", FieldType.Classes),
		grades("grades", FieldType.Classes),
		subjects("subjects", FieldType.Classes),
		subjectCodes("subjectCodes", FieldType.Classes),
		periods("periods", FieldType.Classes);

		private final String value;
		private final FieldType type2;

		Classes(String value, FieldType type) {
			this.value = value;
			this.type2 = type;
		}

		@Override
		public String getValue() { return value; }
		@Override
		public FieldType getType() {
			return type2;
		}

		public enum Course implements IField {
			href("course.href", FieldType.Classes),
			sourcedId("course.sourcedId", FieldType.Classes),
			type("course.identifier", FieldType.Classes);

			private final String value;
			private final FieldType type2;

			Course(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() { return value; }
			@Override
			public FieldType getType() {
				return type2;
			}
		}

		public enum School implements IField {
			href("school.href", FieldType.Classes),
			sourcedId("school.sourcedId", FieldType.Classes),
			type("school.identifier", FieldType.Classes);

			private final String value;
			private final FieldType type2;

			School(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() { return value; }
			@Override
			public FieldType getType() {
				return type2;
			}
		}

		public enum Terms implements IField {
			href("terms.href", FieldType.Classes),
			sourcedId("terms.sourcedId", FieldType.Classes),
			type("terms.identifier", FieldType.Classes);

			private final String value;
			private final FieldType type2;

			Terms(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() { return value; }
			@Override
			public FieldType getType() {
				return type2;
			}
		}
	}

	public enum Enrollments implements IField {
		role("role", FieldType.Enrollments),
		primary("primary", FieldType.Enrollments),
		beginDate("beginDate", FieldType.Enrollments),
		endDate("endDate", FieldType.Enrollments);

		private final String value;
		private final FieldType type2;

		Enrollments(String value, FieldType type) {
			this.value = value;
			this.type2 = type;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}

		public enum User implements IField {
			href("user.href", FieldType.Enrollments),
			sourcedId("user.sourcedId", FieldType.Enrollments),
			type("user.identifier", FieldType.Enrollments);

			private final String value;
			private final FieldType type2;

			User(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}

		public enum Clazz implements IField {
			href("class.href", FieldType.Enrollments),
			sourcedId("class.sourcedId", FieldType.Enrollments),
			type("class.identifier", FieldType.Enrollments);

			private final String value;
			private final FieldType type2;

			Clazz(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}

		public enum School implements IField {
			href("school.href", FieldType.Enrollments),
			sourcedId("school.sourcedId", FieldType.Enrollments),
			type("school.identifier", FieldType.Enrollments);

			private final String value;
			private final FieldType type2;

			School(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}
	}

	public enum Users implements IField {
		username("username", FieldType.Users),
		enabledUser("enabledUser", FieldType.Users),
		givenName("givenName", FieldType.Users),
		familyName("familyName", FieldType.Users),
		middleName("middleName", FieldType.Users),
		role("role", FieldType.Users),
		identifier("identifier", FieldType.Users),
		email("email", FieldType.Users),
		sms("sms", FieldType.Users),
		phone("phone", FieldType.Users),
		grades("grades", FieldType.Users),
		password("password", FieldType.Users);

		private final String value;
		private final FieldType type2;

		Users(String value, FieldType type) {
			this.value = value;
			this.type2 = type;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}

		public enum UserIds implements IField {
			type("userIds.type", FieldType.Users),
			identifier("userIds.identifier", FieldType.Users);

			private final String value;
			private final FieldType type2;

			UserIds(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}

		public enum Agents implements IField {
			href("agents.href", FieldType.Users),
			sourcedId("agents.sourcedId", FieldType.Users),
			type("agents.identifier", FieldType.Users);

			private final String value;
			private final FieldType type2;

			Agents(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}

		public enum Orgs implements IField {
			href("orgs.href", FieldType.Users),
			sourcedId("orgs.sourcedId", FieldType.Users),
			type("orgs.identifier", FieldType.Users);

			private final String value;
			private final FieldType type2;

			Orgs(String value, FieldType type) {
				this.value = value;
				this.type2 = type;
			}

			@Override
			public String getValue() {
				return value;
			}
			@Override
			public FieldType getType() {
				return type2;
			}
		}
	}

	public enum Demographics implements IField {
		birthDate("birthDate", FieldType.Demographics),
		sex("sex", FieldType.Demographics),
		americanIndianOrAlaskaNative("americanIndianOrAlaskaNative", FieldType.Demographics),
		asian("asian", FieldType.Demographics),
		blackOrAfricanAmerican("blackOrAfricanAmerican", FieldType.Demographics),
		nativeHawaiianOrOtherPacificIslander("nativeHawaiianOrOtherPacificIslander", FieldType.Demographics),
		white("white", FieldType.Demographics),
		demographicRaceTwoOrMoreRaces("demographicRaceTwoOrMoreRaces", FieldType.Demographics),
		hispanicOrLatinoEthnicity("hispanicOrLatinoEthnicity", FieldType.Demographics),
		countryOfBirthCode("countryOfBirthCode", FieldType.Demographics),
		stateOfBirthAbbreviation("stateOfBirthAbbreviation", FieldType.Demographics),
		cityOfBirth("cityOfBirth", FieldType.Demographics),
		publicSchoolResidenceStatus("publicSchoolResidenceStatus", FieldType.Demographics);

		private final String value;
		private final FieldType type2;

		Demographics(String value, FieldType type) {
			this.value = value;
			this.type2 = type;
		}

		@Override
		public String getValue() {
			return value;
		}
		@Override
		public FieldType getType() {
			return type2;
		}
	}
}
