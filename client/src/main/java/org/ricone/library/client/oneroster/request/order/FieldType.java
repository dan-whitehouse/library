package org.ricone.library.client.oneroster.request.order;

public enum FieldType implements Field {
	SourcedId("sourcedId"),
	Status("status"),
	DateLastModified("dateLastModified"),
	Metadata("metadata");

	private final String value;
	FieldType(String value) {this.value = value;}

	@Override
	public String getValue() {
		return value;
	}

	enum Orgs implements Field {
		name("name"),
		type("type"),
		identifier("identifier"),
		grades("grades"),
		subjects("subjects"),
		org("orgs"),
		subjectCodes("subjectCodes");

		enum Parent implements Field {
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

		enum Children implements Field {
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

	enum AcademicSessions implements Field {
		title("title"),
		startDate("startDate"),
		endDate("endDate"),
		type("type");

		enum Parent implements Field {
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

		enum Children implements Field {
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

	enum Courses implements Field {
		title("title"),
		courseCode("courseCode"),
		grades("grades"),
		subjects("subjects"),
		subjectCodes("subjectCodes");

		enum SchoolYear implements Field {
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

		enum Org implements Field {
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

	enum Classes implements Field {
		title("title"),
		classCode("classCode"),
		classType("classType"),
		location("location"),
		grades("grades"),
		subjects("subjects"),
		subjectCodes("subjectCodes"),
		periods("periods");

		enum Course implements Field {
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

		enum School implements Field {
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

		enum Terms implements Field {
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

	enum Enrollments implements Field {
		role("role"),
		primary("primary"),
		beginDate("beginDate"),
		endDate("endDate");

		enum User implements Field {
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

		enum Clazz implements Field {
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

		enum School implements Field {
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

	enum Users implements Field {
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

		enum UserIds implements Field {
			type("userIds.type"),
			identifier("userIds.identifier");

			private final String value;
			UserIds(String value) {this.value = value;}

			@Override
			public String getValue() {
				return value;
			}
		}

		enum Agents implements Field {
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

		enum Orgs implements Field {
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

	enum Demographics implements Field {
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
