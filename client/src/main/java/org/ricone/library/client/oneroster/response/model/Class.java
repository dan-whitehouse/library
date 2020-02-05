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
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "title", "classCode", "classType", "location", "grades", "subjects", "course", "school", "terms", "subjectCodes", "periods", "resources"})
public class Class extends Base implements Serializable {
	private final static long serialVersionUID = -6826277327573344241L;

	@JsonProperty("title")
	private String title;

	@JsonProperty("classCode")
	private String classCode;

	@JsonProperty("classType")
	private ClassType classType;

	@JsonProperty("location")
	private String location;

	@JsonProperty("grades")
	@JacksonXmlElementWrapper(localName = "grades") @JacksonXmlProperty(localName = "grade")
	private List<String> grades = new ArrayList<>();

	@JsonProperty("subjects")
	@JacksonXmlElementWrapper(localName = "subjects") @JacksonXmlProperty(localName = "subject")
	private List<String> subjects = new ArrayList<>();

	@JsonProperty("course")
	private GUIDRef course;

	@JsonProperty("school")
	private GUIDRef school;

	@JsonProperty("terms")
	@JacksonXmlElementWrapper(localName = "terms") @JacksonXmlProperty(localName = "term")
	private List<GUIDRef> terms = new ArrayList<>();

	@JsonProperty("subjectCodes")
	@JacksonXmlElementWrapper(localName = "subjectCodes") @JacksonXmlProperty(localName = "subjectCode")
	private List<String> subjectCodes = new ArrayList<>();

	@JsonProperty("periods")
	@JacksonXmlElementWrapper(localName = "periods") @JacksonXmlProperty(localName = "period")
	private List<String> periods = new ArrayList<>();

	@JsonProperty("resources")
	@JacksonXmlElementWrapper(localName = "resources") @JacksonXmlProperty(localName = "resource")
	private List<GUIDRef> resources = new ArrayList<>();

	public Class() {
	}

	public Class(String title, String classCode, ClassType classType, String location, List<String> grades, List<String> subjects, GUIDRef course, GUIDRef school, List<GUIDRef> terms, List<String> subjectCodes, List<String> periods, List<GUIDRef> resources) {
		super();
		this.title = title;
		this.classCode = classCode;
		this.classType = classType;
		this.location = location;
		this.grades = grades;
		this.subjects = subjects;
		this.course = course;
		this.school = school;
		this.terms = terms;
		this.subjectCodes = subjectCodes;
		this.periods = periods;
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

	@JsonProperty("classCode")
	public String getClassCode() {
		return classCode;
	}

	@JsonProperty("classCode")
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	@JsonProperty("classType")
	public ClassType getClassType() {
		return classType;
	}

	@JsonProperty("classType")
	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(String location) {
		this.location = location;
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

	@JsonProperty("course")
	public GUIDRef getCourse() {
		return course;
	}

	@JsonProperty("course")
	public void setCourse(GUIDRef course) {
		this.course = course;
	}

	@JsonProperty("school")
	public GUIDRef getSchool() {
		return school;
	}

	@JsonProperty("school")
	public void setSchool(GUIDRef school) {
		this.school = school;
	}

	@JsonProperty("terms")
	@JacksonXmlElementWrapper(localName = "terms") @JacksonXmlProperty(localName = "term")
	public List<GUIDRef> getTerms() {
		return terms;
	}

	@JsonProperty("terms")
	@JacksonXmlElementWrapper(localName = "terms") @JacksonXmlProperty(localName = "term")
	public void setTerms(List<GUIDRef> terms) {
		this.terms = terms;
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

	@JsonProperty("periods")
	@JacksonXmlElementWrapper(localName = "periods") @JacksonXmlProperty(localName = "period")
	public List<String> getPeriods() {
		return periods;
	}

	@JsonProperty("periods")
	@JacksonXmlElementWrapper(localName = "periods") @JacksonXmlProperty(localName = "period")
	public void setPeriods(List<String> periods) {
		this.periods = periods;
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
	public boolean isEmpty() {
		return Stream.of(super.getSourcedId(), super.getStatus(), super.getDateLastModified(), super.getMetadata(), title, classCode, classType, location, grades, subjects, course, school, terms, subjectCodes, periods).allMatch(Objects::isNull);
	}
}