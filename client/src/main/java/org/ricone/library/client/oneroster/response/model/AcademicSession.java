package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "title", "startDate", "endDate", "type", "parent", "children", "schoolYear"})
public class AcademicSession extends Base implements Serializable {
	private final static long serialVersionUID = 5476752215341220106L;

	@JsonProperty("title")
	@ApiModelProperty(position = 5, value = "", example = "Spring Term")
	private String title;

	@JsonProperty("startDate")
	@ApiModelProperty(position = 6, value = "", example = "2019-01-01")
	private LocalDate startDate;

	@JsonProperty("endDate")
	@ApiModelProperty(position = 7, value = "", example = "2019-04-30")
	private LocalDate endDate;

	@JsonProperty("type")
	@ApiModelProperty(position = 8, value = "The set of permitted tokens for the type of academic session")
	private SessionType type;

	@JsonProperty("parent")
	@ApiModelProperty(position = 9, value = "Link to parent AcademicSession i.e. an AcademicSession 'sourcedId'")
	private GUIDRef parent;

	@JsonProperty("children")
	@JacksonXmlElementWrapper(localName = "children") @JacksonXmlProperty(localName = "child")
	@ApiModelProperty(position = 10, value = "Links to children AcademicSession i.e. an AcademicSession 'sourcedId'")
	private List<GUIDRef> children = new ArrayList<>();

	@JsonProperty("schoolYear")
	@ApiModelProperty(position = 11, value = "The school year for the academic session. This year should include the school year end", example = "2019")
	private Integer schoolYear;

	/**
	 * No args constructor for use in serialization
	 */
	public AcademicSession() {
	}

	/**
	 * @param schoolYear
	 * @param startDate
	 * @param title
	 * @param children
	 * @param parent
	 * @param endDate
	 * @param type
	 */
	public AcademicSession(String title, LocalDate startDate, LocalDate endDate, SessionType type, GUIDRef parent, List<GUIDRef> children, Integer schoolYear) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.parent = parent;
		this.children = children;
		this.schoolYear = schoolYear;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("startDate")
	public LocalDate getStartDate() {
		return startDate;
	}

	@JsonProperty("startDate")
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@JsonProperty("endDate")
	public LocalDate getEndDate() {
		return endDate;
	}

	@JsonProperty("endDate")
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@JsonProperty("type")
	public SessionType getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(SessionType type) {
		this.type = type;
	}

	@JsonProperty("parent")
	public GUIDRef getParent() {
		return parent;
	}

	@JsonProperty("parent")
	public void setParent(GUIDRef parent) {
		this.parent = parent;
	}

	@JsonProperty("children")
	@JacksonXmlElementWrapper(localName = "children") @JacksonXmlProperty(localName = "child")
	public List<GUIDRef> getChildren() {
		return children;
	}

	@JsonProperty("children")
	@JacksonXmlElementWrapper(localName = "children") @JacksonXmlProperty(localName = "child")
	public void setChildren(List<GUIDRef> children) {
		this.children = children;
	}

	@JsonProperty("schoolYear")
	public Integer getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(Integer schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonIgnore
	@Override
	public boolean isEmptyObject() {
		return Stream.of(super.getSourcedId(), super.getStatus(), super.getDateLastModified(), super.getMetadata(), title, startDate, endDate, type, parent, children, schoolYear).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "AcademicSession{" + "title='" + title + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + ", type=" + type + ", parent=" + parent + ", children=" + children + ", schoolYear=" + schoolYear + '}';
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof AcademicSession)) return false;

		AcademicSession that = (AcademicSession) o;

		if(title != null ? !title.equals(that.title) : that.title != null) return false;
		if(startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
		if(endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
		if(type != that.type) return false;
		if(parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
		if(children != null ? !children.equals(that.children) : that.children != null) return false;
		return schoolYear != null ? schoolYear.equals(that.schoolYear) : that.schoolYear == null;
	}

	@Override
	public int hashCode() {
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (parent != null ? parent.hashCode() : 0);
		result = 31 * result + (children != null ? children.hashCode() : 0);
		result = 31 * result + (schoolYear != null ? schoolYear.hashCode() : 0);
		return result;
	}
}