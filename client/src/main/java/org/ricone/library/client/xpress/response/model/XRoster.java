package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.core.Model;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "courseRefId", "courseTitle", "sectionRefId", "subject", "schoolRefId", "schoolSectionId", "schoolYear", "meetingTimes", "students", "primaryStaff", "otherStaffs", "metadata"})
@JsonRootName(value = "xRoster")
public class XRoster extends Model {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("courseRefId")
	private String courseRefId;
	@JsonProperty("courseTitle")
	private String courseTitle;
	@JsonProperty("sectionRefId")
	private String sectionRefId;
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("schoolRefId")
	private String schoolRefId;
	@JsonProperty("schoolSectionId")
	private String schoolSectionId;
	@JsonProperty("schoolYear")
	private String schoolYear;
	@JsonProperty("meetingTimes")
	private MeetingTimes meetingTimes;
	@JsonProperty("students")
	private Students students;
	@JsonProperty("primaryStaff")
	private PrimaryStaff primaryStaff;
	@JsonProperty("otherStaffs")
	private OtherStaffs otherStaffs;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XRoster() { }

	public XRoster(String refId, String courseRefId, String courseTitle, String sectionRefId, String subject, String schoolRefId, String schoolSectionId, String schoolYear, MeetingTimes meetingTimes, Students students, PrimaryStaff primaryStaff, OtherStaffs otherStaffs, Metadata metadata) {
		this.refId = refId;
		this.courseRefId = courseRefId;
		this.courseTitle = courseTitle;
		this.sectionRefId = sectionRefId;
		this.subject = subject;
		this.schoolRefId = schoolRefId;
		this.schoolSectionId = schoolSectionId;
		this.schoolYear = schoolYear;
		this.meetingTimes = meetingTimes;
		this.students = students;
		this.primaryStaff = primaryStaff;
		this.otherStaffs = otherStaffs;
		this.metadata = metadata;
	}

	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	public String getRefId() {
		return refId;
	}

	@JsonProperty("@refId")
	public void setRefId(String refId) {
		this.refId = refId;
	}

	@JsonProperty("courseRefId")
	public String getCourseRefId() {
		return courseRefId;
	}

	@JsonProperty("courseRefId")
	public void setCourseRefId(String courseRefId) {
		this.courseRefId = courseRefId;
	}

	@JsonProperty("courseTitle")
	public String getCourseTitle() {
		return courseTitle;
	}

	@JsonProperty("courseTitle")
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	@JsonProperty("sectionRefId")
	public String getSectionRefId() {
		return sectionRefId;
	}

	@JsonProperty("sectionRefId")
	public void setSectionRefId(String sectionRefId) {
		this.sectionRefId = sectionRefId;
	}

	@JsonProperty("subject")
	public String getSubject() {
		return subject;
	}

	@JsonProperty("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@JsonProperty("schoolRefId")
	public String getSchoolRefId() {
		return schoolRefId;
	}

	@JsonProperty("schoolRefId")
	public void setSchoolRefId(String schoolRefId) {
		this.schoolRefId = schoolRefId;
	}

	@JsonProperty("schoolSectionId")
	public String getSchoolSectionId() {
		return schoolSectionId;
	}

	@JsonProperty("schoolSectionId")
	public void setSchoolSectionId(String schoolSectionId) {
		this.schoolSectionId = schoolSectionId;
	}

	@JsonProperty("schoolYear")
	public String getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonProperty("meetingTimes")
	public MeetingTimes getMeetingTimes() {
		return meetingTimes;
	}

	@JsonProperty("meetingTimes")
	public void setMeetingTimes(MeetingTimes meetingTimes) {
		this.meetingTimes = meetingTimes;
	}

	@JsonProperty("students")
	public Students getStudents() {
		return students;
	}

	@JsonProperty("students")
	public void setStudents(Students students) {
		this.students = students;
	}

	@JsonProperty("primaryStaff")
	public PrimaryStaff getPrimaryStaff() {
		return primaryStaff;
	}

	@JsonProperty("primaryStaff")
	public void setPrimaryStaff(PrimaryStaff primaryStaff) {
		this.primaryStaff = primaryStaff;
	}

	@JsonProperty("otherStaffs")
	public OtherStaffs getOtherStaffs() {
		return otherStaffs;
	}

	@JsonProperty("otherStaffs")
	public void setOtherStaffs(OtherStaffs otherStaffs) {
		this.otherStaffs = otherStaffs;
	}

	@JsonProperty("metadata")
	public Metadata getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean isEmpty() {
		return Stream.of(refId, courseRefId, courseTitle, sectionRefId, subject, schoolRefId, schoolSectionId, schoolYear, meetingTimes, students, primaryStaff, otherStaffs, metadata).allMatch(Objects::isNull);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XRoster xRoster = (XRoster) o;
		return Objects.equals(refId, xRoster.refId) &&
				Objects.equals(courseRefId, xRoster.courseRefId) &&
				Objects.equals(courseTitle, xRoster.courseTitle) &&
				Objects.equals(sectionRefId, xRoster.sectionRefId) &&
				Objects.equals(subject, xRoster.subject) &&
				Objects.equals(schoolRefId, xRoster.schoolRefId) &&
				Objects.equals(schoolSectionId, xRoster.schoolSectionId) &&
				Objects.equals(schoolYear, xRoster.schoolYear) &&
				Objects.equals(meetingTimes, xRoster.meetingTimes) &&
				Objects.equals(students, xRoster.students) &&
				Objects.equals(primaryStaff, xRoster.primaryStaff) &&
				Objects.equals(otherStaffs, xRoster.otherStaffs) &&
				Objects.equals(metadata, xRoster.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(refId, courseRefId, courseTitle, sectionRefId, subject, schoolRefId, schoolSectionId, schoolYear, meetingTimes, students, primaryStaff, otherStaffs, metadata);
	}

	@Override
	public String toString() {
		return "XRoster{" + "refId='" + refId + '\'' + ", courseRefId='" + courseRefId + '\'' + ", courseTitle='" + courseTitle + '\'' + ", sectionRefId='" + sectionRefId + '\'' + ", subject='" + subject + '\'' + ", schoolRefId='" + schoolRefId + '\'' + ", schoolSectionId='" + schoolSectionId + '\'' + ", schoolYear='" + schoolYear + '\'' + ", meetingTimes=" + meetingTimes + ", students=" + students + ", primaryStaff=" + primaryStaff + ", otherStaffs=" + otherStaffs + ", metadata=" + metadata + '}';
	}
}