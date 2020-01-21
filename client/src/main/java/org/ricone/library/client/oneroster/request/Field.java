package org.ricone.library.client.oneroster.request;

public enum Field implements IField {
	sourcedId("sourcedId"),
	status("status"),
	dateLastModified("dateLastModified"),
	metadata("metadata");

	private final String value;
	Field(String value) {this.value = value;}

	@Override
	public String getValue() {
		return value;
	}

	public enum Orgs implements IField {
		name("name"),
		type("type"),
		identifier("identifier");

		public enum Parent implements IField {
			href("parent.href"),
			sourcedId("parent.sourcedId"),
			type("parent.identifier");

			private final String value;
			Parent(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		public enum Children implements IField {
			href("children.href"),
			sourcedId("children.sourcedId"),
			type("children.identifier");

			private final String value;
			Children(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}


		private final String value;
		Orgs(String value) {this.value = value;}

		@Override
		public String getValue() {
			return value;
		}
	}

	public enum AcademicSessions implements IField {
		title("title"),
		startDate("startDate"),
		endDate("endDate"),
		type("type");

		public enum Parent implements IField {
			href("parent.href"),
			sourcedId("parent.sourcedId"),
			type("parent.identifier");

			private final String value;
			Parent(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		public enum Children implements IField {
			href("children.href"),
			sourcedId("children.sourcedId"),
			type("children.identifier");

			private final String value;
			Children(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		private final String value;
		AcademicSessions(String value) {this.value = value;}

		@Override
		public String getValue() {
			return value;
		}
	}

	public enum Courses implements IField {
		title("title"),
		courseCode("courseCode"),
		grades("grades"),
		subjects("subjects"),
		subjectCodes("subjectCodes");

		public enum SchoolYear implements IField {
			href("schoolYear.href"),
			sourcedId("schoolYear.sourcedId"),
			type("schoolYear.identifier");

			private final String value;
			SchoolYear(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		public enum Org implements IField {
			href("org.href"),
			sourcedId("org.sourcedId"),
			type("org.identifier");

			private final String value;
			Org(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		private final String value;
		Courses(String value) {this.value = value;}

		@Override
		public String getValue() {
			return value;
		}
	}

	public enum Classes implements IField {
		title("title"),
		classCode("classCode"),
		classType("classType"),
		location("location"),
		grades("grades"),
		subjects("subjects"),
		subjectCodes("subjectCodes"),
		periods("periods");

		public enum Course implements IField {
			href("course.href"),
			sourcedId("course.sourcedId"),
			type("course.identifier");

			private final String value;
			Course(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		public enum School implements IField {
			href("school.href"),
			sourcedId("school.sourcedId"),
			type("school.identifier");

			private final String value;
			School(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		public enum Terms implements IField {
			href("terms.href"),
			sourcedId("terms.sourcedId"),
			type("terms.identifier");

			private final String value;
			Terms(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		private final String value;
		Classes(String value) {this.value = value;}

		@Override
		public String getValue() {
			return value;
		}
	}

	public enum Enrollments implements IField {
		role("role"),
		primary("primary"),
		beginDate("beginDate"),
		endDate("endDate");

		public enum User implements IField {
			href("user.href"),
			sourcedId("user.sourcedId"),
			type("user.identifier");

			private final String value;
			User(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		public enum Clazz implements IField {
			href("class.href"),
			sourcedId("class.sourcedId"),
			type("class.identifier");

			private final String value;
			Clazz(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		public enum School implements IField {
			href("school.href"),
			sourcedId("school.sourcedId"),
			type("school.identifier");

			private final String value;
			School(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}


		private final String value;
		Enrollments(String value) {this.value = value;}

		@Override
		public String getValue() {
			return value;
		}
	}

	public enum Users implements IField {
		username("username"),
		enabledUser("enabledUser"),
		givenName("givenName"),
		familyName("familyName"),
		middleName("middleName"),
		role("role"),
		identifier("identifier"),
		email("email"),
		sms("sms"),
		phone("phone"),
		grades("grades"),
		password("password");

		public enum UserIds implements IField {
			type("userIds.type"),
			identifier("userIds.identifier");

			private final String value;
			UserIds(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		public enum Agents implements IField {
			href("agents.href"),
			sourcedId("agents.sourcedId"),
			type("agents.identifier");

			private final String value;
			Agents(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		public enum Orgs implements IField {
			href("orgs.href"),
			sourcedId("orgs.sourcedId"),
			type("orgs.identifier");

			private final String value;
			Orgs(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		private final String value;
		Users(String value) {this.value = value;}

		@Override
		public String getValue() {
			return value;
		}
	}

	public enum Demographics implements IField {
		birthDate("birthDate"),
		sex("sex"),
		americanIndianOrAlaskaNative("americanIndianOrAlaskaNative"),
		asian("asian"),
		blackOrAfricanAmerican("blackOrAfricanAmerican"),
		nativeHawaiianOrOtherPacificIslander("nativeHawaiianOrOtherPacificIslander"),
		white("white"),
		demographicRaceTwoOrMoreRaces("demographicRaceTwoOrMoreRaces"),
		hispanicOrLatinoEthnicity("hispanicOrLatinoEthnicity"),
		countryOfBirthCode("countryOfBirthCode"),
		stateOfBirthAbbreviation("stateOfBirthAbbreviation"),
		cityOfBirth("cityOfBirth"),
		publicSchoolResidenceStatus("publicSchoolResidenceStatus");

		private final String value;
		Demographics(String value) {this.value = value;}

		@Override
		public String getValue() {
			return value;
		}
	}
}
