package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.core.Model;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "schoolRefId", "schoolYear", "sessions", "metadata"})
@JsonRootName(value = "xCalendar")
public class XCalendar extends Model {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("schoolRefId")
	private String schoolRefId;
	@JsonProperty("schoolYear")
	private String schoolYear;
	@JsonProperty("sessions")
	private Sessions sessions;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XCalendar() { }

	public XCalendar(String refId, String schoolRefId, String schoolYear, Sessions sessions, Metadata metadata) {
		this.refId = refId;
		this.schoolRefId = schoolRefId;
		this.schoolYear = schoolYear;
		this.sessions = sessions;
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

	@JsonProperty("schoolRefId")
	public String getSchoolRefId() {
		return schoolRefId;
	}

	@JsonProperty("schoolRefId")
	public void setSchoolRefId(String schoolRefId) {
		this.schoolRefId = schoolRefId;
	}

	@JsonProperty("schoolYear")
	public String getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonProperty("sessions")
	public Sessions getSessions() {
		return sessions;
	}

	@JsonProperty("sessions")
	public void setSessions(Sessions sessions) {
		this.sessions = sessions;
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
		return Stream.of(refId, schoolRefId, schoolYear, sessions, metadata).allMatch(Objects::isNull);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XCalendar xCalendar = (XCalendar) o;
		return Objects.equals(refId, xCalendar.refId) &&
				Objects.equals(schoolRefId, xCalendar.schoolRefId) &&
				Objects.equals(schoolYear, xCalendar.schoolYear) &&
				Objects.equals(sessions, xCalendar.sessions) &&
				Objects.equals(metadata, xCalendar.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(refId, schoolRefId, schoolYear, sessions, metadata);
	}

	@Override
	public String toString() {
		return "XCalendar{" + "refId='" + refId + '\'' + ", schoolRefId='" + schoolRefId + '\'' + ", schoolYear='" + schoolYear + '\'' + ", sessions=" + sessions + ", metadata=" + metadata + '}';
	}
}