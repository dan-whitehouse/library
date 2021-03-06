package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "role", "primary", "user", "class", "school", "beginDate", "endDate"})
public class Enrollment extends Base implements Serializable {
	private final static long serialVersionUID = -471764017962444775L;
	@JsonProperty("role")
	private RoleType role;
	@JsonProperty("primary")
	private Boolean primary;
	@JsonProperty("user")
	private GUIDRef user;
	@JsonProperty("class")
	private GUIDRef _class;
	@JsonProperty("school")
	private GUIDRef school;
	@JsonProperty("beginDate")
	private LocalDate beginDate;
	@JsonProperty("endDate")
	private LocalDate endDate;

	public Enrollment() { }

	public Enrollment(RoleType role, Boolean primary, GUIDRef user, GUIDRef _class, GUIDRef school, LocalDate beginDate, LocalDate endDate) {
		super();
		this.role = role;
		this.primary = primary;
		this.user = user;
		this._class = _class;
		this.school = school;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	@JsonProperty("role")
	public RoleType getRole() {
		return role;
	}

	@JsonProperty("role")
	public void setRole(RoleType role) {
		this.role = role;
	}

	@JsonProperty("primary")
	public Boolean getPrimary() {
		return primary;
	}

	@JsonProperty("primary")
	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	@JsonProperty("user")
	public GUIDRef getUser() {
		return user;
	}

	@JsonProperty("user")
	public void setUser(GUIDRef user) {
		this.user = user;
	}

	@JsonProperty("class")
	public GUIDRef getClass_() {
		return _class;
	}

	@JsonProperty("class")
	public void setClass_(GUIDRef _class) {
		this._class = _class;
	}

	@JsonProperty("school")
	public GUIDRef getSchool() {
		return school;
	}

	@JsonProperty("school")
	public void setSchool(GUIDRef school) {
		this.school = school;
	}

	@JsonProperty("beginDate")
	public LocalDate getBeginDate() {
		return beginDate;
	}

	@JsonProperty("beginDate")
	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	@JsonProperty("endDate")
	public LocalDate getEndDate() {
		return endDate;
	}

	@JsonProperty("endDate")
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@JsonIgnore
	@Override
	public boolean isEmpty() {
		return Stream.of(super.getSourcedId(), super.getStatus(), super.getDateLastModified(), super.getMetadata(), role, primary, user, _class, school, beginDate, endDate).allMatch(Objects::isNull);
	}
}
