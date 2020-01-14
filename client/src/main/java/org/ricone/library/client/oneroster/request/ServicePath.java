package org.ricone.library.client.oneroster.request;

import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.client.xpress.request.RequestType;
import org.ricone.library.client.xpress.request.ServicePathType;

import java.lang.Class;
import java.util.List;

public enum ServicePath {
	GET_Org_By_SourcedId("orgs/{id}", ServicePathType.SINGLE, OrgResponse.class),
	GET_Orgs("orgs", ServicePathType.OBJECT,  OrgsResponse.class),
	GET_School_By_SourcedId("schools/{id}", ServicePathType.SINGLE,  OrgResponse.class),
	GET_Schools("schools", ServicePathType.OBJECT,  OrgsResponse.class),

	//----
	GET_AcademicSessions_By_SourcedId("academicSessions/{id}", ServicePathType.SINGLE,  AcademicSessionResponse.class),
	GET_AcademicSessions("academicSessions", ServicePathType.OBJECT,  AcademicSessionsResponse.class),
	GET_Terms_By_SourcedId("terms/{id}", ServicePathType.SINGLE,  AcademicSessionResponse.class),
	GET_Terms("terms", ServicePathType.OBJECT,  AcademicSessionsResponse.class),
	GET_Terms_By_School_SourcedId("schools/{id}/terms", ServicePathType.PREDICATE,  AcademicSessionsResponse.class),

	GET_Courses_By_SourcedId("courses/{id}", ServicePathType.SINGLE,  CourseResponse.class),
	GET_Courses("courses", ServicePathType.OBJECT,  CoursesResponse.class),
	GET_Courses_By_School_SourcedId("schools/{id}/courses", ServicePathType.PREDICATE,  CoursesResponse.class),

	GET_Classes_By_SourcedId("classes/{id}", ServicePathType.SINGLE,  ClassResponse.class),
	GET_Classes("classes", ServicePathType.OBJECT,  CoursesResponse.class),
	GET_Classes_By_School_SourcedId("schools/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class),
	GET_Classes_By_Term_SourcedId("terms/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class),
	GET_Classes_By_Course_SourcedId("terms/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class),
	GET_Classes_By_Teacher_SourcedId("teachers/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class),
	GET_Classes_By_Student_SourcedId("students/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class),
	GET_Classes_By_User_SourcedId("users/{id}/classes", ServicePathType.PREDICATE,  ClassesResponse.class),

	GET_Enrollment_By_SourcedId("enrollments/{id}", ServicePathType.SINGLE,  EnrollmentResponse.class),
	GET_Enrollments("enrollments", ServicePathType.OBJECT,  EnrollmentsResponse.class),
	GET_Enrollments_By_School_SourcedId("schools/{id}/enrollments", ServicePathType.PREDICATE,  EnrollmentsResponse.class),
	GET_Enrollments_By_Class_SourcedId_With_School_SourcedId("schools/{id}/classes/{id}/enrollments", ServicePathType.PREDICATE,  EnrollmentsResponse.class),

	GET_Users_By_SourcedId("users/{id}", ServicePathType.SINGLE,  UserResponse.class),
	GET_Users("users", ServicePathType.OBJECT,  UsersResponse.class),
	GET_Teacher_By_SourcedId("teachers/{id}", ServicePathType.SINGLE,  UserResponse.class),
	GET_Teachers("teachers", ServicePathType.OBJECT,  UsersResponse.class),
	GET_Teachers_By_School_SourcedId("schools/{id}/teachers", ServicePathType.PREDICATE,  UsersResponse.class),
	GET_Teachers_By_Class_SourcedId_With_School_SourcedId("schools/{id}/classes/{id}/teachers", ServicePathType.PREDICATE,  UsersResponse.class),
	GET_Teachers_By_Class_SourcedId("classes/{id}/teachers", ServicePathType.PREDICATE,  UsersResponse.class),
	GET_Student_By_SourcedId("students/{id}", ServicePathType.SINGLE,  UserResponse.class),
	GET_Students("students", ServicePathType.OBJECT,  UsersResponse.class),
	GET_Students_By_School_SourcedId("schools/{id}/students", ServicePathType.PREDICATE,  UsersResponse.class),
	GET_Students_By_Class_SourcedId_With_School_SourcedId("schools/{id}/classes/{id}/students", ServicePathType.PREDICATE,  UsersResponse.class),
	GET_Students_By_Class_SourcedId("classes/{id}/students", ServicePathType.PREDICATE,  UsersResponse.class),

	GET_Demographics_By_SourcedId("demographics/{id}", ServicePathType.SINGLE,  UserResponse.class),
	GET_demographics("demographics", ServicePathType.OBJECT,  UsersResponse.class)
	;

	private final String value;
	private ServicePathType servicePathType;
	private Class responseClass;

	ServicePath(String value, ServicePathType servicePathType, Class responseClass) {
		this.value = value;
		this.servicePathType = servicePathType;
		this.responseClass = responseClass;
	}

	public String getValue() {
		return value;
	}

	public ServicePathType getServicePathType() {
		return servicePathType;
	}
	
	public Class getResponseClass() {
		return responseClass;
	}
}