package org.ricone.library.client.oneroster.request;

import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.oneroster.response.*;

import java.lang.Class;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public enum ServicePath {
	GET_Org_By_SourcedId("orgs/{id}", ServicePathType.SINGLE, OrgResponse.class, FieldType.Orgs),
	GET_Orgs("orgs", ServicePathType.OBJECT,  OrgsResponse.class, FieldType.Orgs),
	GET_School_By_SourcedId("schools/{id}", ServicePathType.SINGLE,  OrgResponse.class, FieldType.Orgs),
	GET_Schools("schools", ServicePathType.OBJECT,  OrgsResponse.class, FieldType.Orgs),

	GET_AcademicSession_By_SourcedId("academicSessions/{id}", ServicePathType.SINGLE,  AcademicSessionResponse.class, FieldType.AcademicSessions),
	GET_AcademicSessions("academicSessions", ServicePathType.OBJECT,  AcademicSessionsResponse.class, FieldType.AcademicSessions),
	GET_Terms_By_SourcedId("terms/{id}", ServicePathType.SINGLE,  AcademicSessionResponse.class, FieldType.AcademicSessions),
	GET_Terms("terms", ServicePathType.OBJECT,  AcademicSessionsResponse.class, FieldType.AcademicSessions),
	GET_Terms_By_School_SourcedId("schools/{id}/terms", ServicePathType.PREDICATE,  AcademicSessionsResponse.class, FieldType.AcademicSessions),

	GET_Course_By_SourcedId("courses/{id}", ServicePathType.SINGLE,  CourseResponse.class, FieldType.Courses),
	GET_Courses("courses", ServicePathType.OBJECT,  CoursesResponse.class, FieldType.Courses),
	GET_Courses_By_School_SourcedId("schools/{id}/courses", ServicePathType.PREDICATE,  CoursesResponse.class, FieldType.Courses),

	GET_Class_By_SourcedId("classes/{id}", ServicePathType.SINGLE,  ClassResponse.class, FieldType.Classes),
	GET_Classes("classes", ServicePathType.OBJECT,  ClassesResponse.class, FieldType.Classes),
	GET_Classes_By_School_SourcedId("schools/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class, FieldType.Classes),
	GET_Classes_By_Term_SourcedId("terms/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class, FieldType.Classes),
	GET_Classes_By_Course_SourcedId("terms/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class, FieldType.Classes),
	GET_Classes_By_Teacher_SourcedId("teachers/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class, FieldType.Classes),
	GET_Classes_By_Student_SourcedId("students/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class, FieldType.Classes),
	GET_Classes_By_User_SourcedId("users/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class, FieldType.Classes),

	GET_Enrollment_By_SourcedId("enrollments/{id}", ServicePathType.SINGLE,  EnrollmentResponse.class, FieldType.Enrollments),
	GET_Enrollments("enrollments", ServicePathType.OBJECT,  EnrollmentsResponse.class, FieldType.Enrollments),
	GET_Enrollments_By_School_SourcedId("schools/{id}/enrollments", ServicePathType.PREDICATE,  EnrollmentsResponse.class, FieldType.Enrollments),
	GET_Enrollments_By_Class_SourcedId_With_School_SourcedId("schools/{id}/classes/{id}/enrollments", ServicePathType.PREDICATES,  EnrollmentsResponse.class, FieldType.Enrollments),

	GET_User_By_SourcedId("users/{id}", ServicePathType.SINGLE,  UserResponse.class, FieldType.Users),
	GET_Users("users", ServicePathType.OBJECT,  UsersResponse.class, FieldType.Users),
	GET_Teacher_By_SourcedId("teachers/{id}", ServicePathType.SINGLE,  UserResponse.class, FieldType.Users),
	GET_Teachers("teachers", ServicePathType.OBJECT,  UsersResponse.class, FieldType.Users),
	GET_Teachers_By_School_SourcedId("schools/{id}/teachers", ServicePathType.PREDICATE,  UsersResponse.class, FieldType.Users),
	GET_Teachers_By_Class_SourcedId_With_School_SourcedId("schools/{id}/classes/{id}/teachers", ServicePathType.PREDICATES,  UsersResponse.class, FieldType.Users),
	GET_Teachers_By_Class_SourcedId("classes/{id}/teachers", ServicePathType.PREDICATE,  UsersResponse.class, FieldType.Users),
	GET_Student_By_SourcedId("students/{id}", ServicePathType.SINGLE,  UserResponse.class, FieldType.Users),
	GET_Students("students", ServicePathType.OBJECT,  UsersResponse.class, FieldType.Users),
	GET_Students_By_School_SourcedId("schools/{id}/students", ServicePathType.PREDICATE,  UsersResponse.class, FieldType.Users),
	GET_Students_By_Class_SourcedId_With_School_SourcedId("schools/{id}/classes/{id}/students", ServicePathType.PREDICATES,  UsersResponse.class, FieldType.Users),
	GET_Students_By_Class_SourcedId("classes/{id}/students", ServicePathType.PREDICATE,  UsersResponse.class, FieldType.Users),

	GET_Demographic_By_SourcedId("demographics/{id}", ServicePathType.SINGLE,  DemographicResponse.class, FieldType.Users),
	GET_Demographics("demographics", ServicePathType.OBJECT,  DemographicsResponse.class, FieldType.Users);

	private final String value;
	private ServicePathType servicePathType;
	private Class<? extends IResponse<?>> responseClass;
	private FieldType fieldType;

	ServicePath(String value, ServicePathType servicePathType, Class<? extends IResponse<?>> responseClass, FieldType fieldType) {
		this.value = value;
		this.servicePathType = servicePathType;
		this.responseClass = responseClass;
		this.fieldType = fieldType;
	}

	public String getValue() {
		return value;
	}

	public ServicePathType getServicePathType() {
		return servicePathType;
	}
	
	public Class<? extends IResponse<?>> getResponseClass() {
		return responseClass;
	}

	public FieldType getFieldType() {
		return fieldType;
	}
}