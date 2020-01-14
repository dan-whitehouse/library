package org.ricone.library.client.xpress.request;

public interface OrderByField {
	//TODO - Didn't complete implementation. Not all fields were added to each object.

	enum XLea implements OrderByField {
		RefId("refId"),
		LocalId("localId"),
		StateProvinceId("stateProvinceId"),
		NcesId("ncesId"),
		LeaName("leaName")
		;

		private final String value;
		XLea(String value) {
			this.value = value;
		}
		@Override public String getValue() {
			return value;
		}
	}

	enum XSchool implements OrderByField {
		RefId("refId"),
		LeaRefId("leaRefId"),
		LocalId("localId"),
		StateProvinceId("stateProvinceId"),
		SchoolName("schoolName")
		;

		private final String value;
		XSchool(String value) {
			this.value = value;
		}
		@Override public String getValue() {
			return value;
		}
	}

	enum XCalendar implements OrderByField {
		RefId("refId"),
		SchoolRefId("schoolRefId"),
		SchoolYear("schoolYear"),
		;

		private final String value;
		XCalendar(String value) {
			this.value = value;
		}
		@Override public String getValue() {
			return value;
		}
	}

	enum XCourse implements OrderByField {
		RefId("refId"),
		SchoolRefId("schoolRefId"),
		SchoolCourseId("schoolCourseId"),
		LeaCourseId("leaCourseId"),
		CourseTitle("courseTitle"),
		Description("description"),
		Subject("subject"),
		ScedCourseCode("scedCourseCode"),
		ScedCourseLevelCode("scedCourseLevelCode"),
		ScedCourseSubjectAreaCode("scedCourseSubjectAreaCode")
		;

		private final String value;
		XCourse(String value) {
			this.value = value;
		}
		@Override public String getValue() {
			return value;
		}
	}

	enum XRoster implements OrderByField {
		RefId("refId"),
		CourseRefId("courseRefId"),
		CourseTitle("courseTitle"),
		SectionRefId("sectionRefId"),
		Subject("subject"),
		SchoolRefId("schoolRefId"),
		schoolSectionId("schoolSectionId"),
		SchoolYear("schoolYear"),
		PrimaryStaff_RefId("primaryStaff.staffPersonReference.refId"),
		PrimaryStaff_localId("primaryStaff.staffPersonReference.localId"),
		PrimaryStaff_GivenName("primaryStaff.staffPersonReference.givenName"),
		PrimaryStaff_TeacherOfRecord("primaryStaff.teacherOfRecord"),
		PrimaryStaff_PercentResponsible("primaryStaff.percentResponsible"),
		;

		private final String value;
		XRoster(String value) {
			this.value = value;
		}
		@Override public String getValue() {
			return value;
		}
	}

	enum XStaff implements OrderByField {
		RefId("refId"),
		Name_Type("name.type"),
		Name_Prefix("name.prefix"),
		Name_FamilyName("name.familyName"),
		Name_GivenName("name.givenName"),
		Name_MiddleName("name.middleName"),
		Name_Suffix("name.suffix"),
		LocalId("localId"),
		StateProvinceId("stateProvinceId"),
		Sex("sex"),
		Email_EmailType("email.emailType"),
		Email_EmailAddress("email.emailAddress"),
		PrimaryAssignment_LeaRefId("primaryAssignment.leaRefId"),
		PrimaryAssignment_SchoolRefId("primaryAssignment.schoolRefId"),
		PrimaryAssignment_JobFunction("primaryAssignment.jobFunction"),
		;

		private final String value;
		XStaff(String value) {
			this.value = value;
		}
		@Override public String getValue() {
			return value;
		}
	}

	enum XStudent implements OrderByField {
		RefId("refId"),
		Name_Type("name.type"),
		Name_Prefix("name.prefix"),
		Name_FamilyName("name.familyName"),
		Name_GivenName("name.givenName"),
		Name_MiddleName("name.middleName"),
		Name_Suffix("name.suffix"),
		LocalId("localId"),
		StateProvinceId("stateProvinceId"),
		Email_EmailType("email.emailType"),
		Email_EmailAddress("email.emailAddress"),
		;

		private final String value;
		XStudent(String value) {
			this.value = value;
		}
		@Override public String getValue() {
			return value;
		}
	}

	enum XContact implements OrderByField {
		RefId("refId"),
		Name_Type("name.type"),
		Name_Prefix("name.prefix"),
		Name_FamilyName("name.familyName"),
		Name_GivenName("name.givenName"),
		Name_MiddleName("name.middleName"),
		Name_Suffix("name.suffix"),
		LocalId("localId"),
		Email_EmailType("email.emailType"),
		Email_EmailAddress("email.emailAddress"),
		;

		private final String value;
		XContact(String value) {
			this.value = value;
		}
		@Override public String getValue() {
			return value;
		}
	}

	default String getValue() {
		return "Field_Value_Not_Implemented";
	}
}
