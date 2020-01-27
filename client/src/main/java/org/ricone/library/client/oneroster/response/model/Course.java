package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "title", "schoolYear", "courseCode", "grades", "subjects", "org", "subjectCodes", "resources"})
public class Course extends Base implements Serializable {
	private final static long serialVersionUID = 6014301965221202137L;
	@JsonProperty("title")
	private String title;

	@JsonProperty("schoolYear")
	private GUIDRef schoolYear;

	@JsonProperty("courseCode")
	private String courseCode;

	@JsonProperty("grades")
	@JacksonXmlElementWrapper(localName = "grades") @JacksonXmlProperty(localName = "grade")
	private List<String> grades = new ArrayList<>();

	@JsonProperty("subjects")
	@JacksonXmlElementWrapper(localName = "subjects") @JacksonXmlProperty(localName = "subject")
	private List<String> subjects = new ArrayList<>();

	@JsonProperty("org")
	private GUIDRef org;

	@JsonProperty("subjectCodes")
	@JacksonXmlElementWrapper(localName = "subjectCodes") @JacksonXmlProperty(localName = "subjectCode")
	private List<String> subjectCodes = new ArrayList<>();

	@JsonProperty("resources")
	@JacksonXmlElementWrapper(localName = "resources") @JacksonXmlProperty(localName = "resource")
	private List<GUIDRef> resources = new ArrayList<>();

	/**
	 * No args constructor for use in serialization
	 */
	public Course() {
	}

	/**
	 * @param schoolYear
	 * @param resources
	 * @param title
	 * @param subjectCodes
	 * @param subjects
	 * @param org
	 * @param grades
	 * @param courseCode
	 */
	public Course(String title, GUIDRef schoolYear, String courseCode, List<String> grades, List<String> subjects, GUIDRef org, List<String> subjectCodes, List<GUIDRef> resources) {
		super();
		this.title = title;
		this.schoolYear = schoolYear;
		this.courseCode = courseCode;
		this.grades = grades;
		this.subjects = subjects;
		this.org = org;
		this.subjectCodes = subjectCodes;
		this.resources = resources;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("schoolYear")
	public GUIDRef getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(GUIDRef schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonProperty("courseCode")
	public String getCourseCode() {
		return courseCode;
	}

	@JsonProperty("courseCode")
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	@JsonProperty("grades")
	@JacksonXmlElementWrapper(localName = "grades") @JacksonXmlProperty(localName = "grade")
	public List<String> getGrades() {
		return grades;
	}

	@JsonProperty("grades")
	@JacksonXmlElementWrapper(localName = "grades") @JacksonXmlProperty(localName = "grade")
	public void setGrades(List<String> grades) {
		this.grades = grades;
	}

	@JsonProperty("subjects")
	@JacksonXmlElementWrapper(localName = "subjects") @JacksonXmlProperty(localName = "subject")
	public List<String> getSubjects() {
		return subjects;
	}

	@JsonProperty("subjects")
	@JacksonXmlElementWrapper(localName = "subjects") @JacksonXmlProperty(localName = "subject")
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	@JsonProperty("org")
	public GUIDRef getOrg() {
		return org;
	}

	@JsonProperty("org")
	public void setOrg(GUIDRef org) {
		this.org = org;
	}

	@JsonProperty("subjectCodes")
	@JacksonXmlElementWrapper(localName = "subjectCodes") @JacksonXmlProperty(localName = "subjectCode")
	public List<String> getSubjectCodes() {
		return subjectCodes;
	}

	@JsonProperty("subjectCodes")
	@JacksonXmlElementWrapper(localName = "subjectCodes") @JacksonXmlProperty(localName = "subjectCode")
	public void setSubjectCodes(List<String> subjectCodes) {
		this.subjectCodes = subjectCodes;
	}

	@JsonProperty("resources")
	@JacksonXmlElementWrapper(localName = "resources") @JacksonXmlProperty(localName = "resource")
	public List<GUIDRef> getResources() {
		return resources;
	}

	@JsonProperty("resources")
	@JacksonXmlElementWrapper(localName = "resources") @JacksonXmlProperty(localName = "resource")
	public void setResources(List<GUIDRef> resources) {
		this.resources = resources;
	}

	@JsonIgnore
	@Override
	public boolean isEmptyObject() {
		return Stream.of(super.getSourcedId(), super.getStatus(), super.getDateLastModified(), super.getMetadata(), title, schoolYear, courseCode, grades, subjects, org, subjectCodes).allMatch(Objects::isNull);
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Course)) return false;

		Course course = (Course) o;

		if(title != null ? !title.equals(course.title) : course.title != null) return false;
		if(schoolYear != null ? !schoolYear.equals(course.schoolYear) : course.schoolYear != null) return false;
		if(courseCode != null ? !courseCode.equals(course.courseCode) : course.courseCode != null) return false;
		if(grades != null ? !grades.equals(course.grades) : course.grades != null) return false;
		if(subjects != null ? !subjects.equals(course.subjects) : course.subjects != null) return false;
		if(org != null ? !org.equals(course.org) : course.org != null) return false;
		if(subjectCodes != null ? !subjectCodes.equals(course.subjectCodes) : course.subjectCodes != null) return false;
		return resources != null ? resources.equals(course.resources) : course.resources == null;
	}

	@Override
	public int hashCode() {
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (schoolYear != null ? schoolYear.hashCode() : 0);
		result = 31 * result + (courseCode != null ? courseCode.hashCode() : 0);
		result = 31 * result + (grades != null ? grades.hashCode() : 0);
		result = 31 * result + (subjects != null ? subjects.hashCode() : 0);
		result = 31 * result + (org != null ? org.hashCode() : 0);
		result = 31 * result + (subjectCodes != null ? subjectCodes.hashCode() : 0);
		result = 31 * result + (resources != null ? resources.hashCode() : 0);
		return result;
	}
}