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
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "username", "userIds", "enabledUser", "givenName", "familyName", "middleName", "role", "identifier", "email", "sms", "phone", "agents", "orgs", "grades", "password"})
public class User extends Base implements Serializable {
	private final static long serialVersionUID = 1500791574718760163L;
	@JsonProperty("username")
	private String username;
	@JsonProperty("userIds")
	@JacksonXmlElementWrapper(localName = "userIds") @JacksonXmlProperty(localName = "userId")
	private List<UserId> userIds = new ArrayList<>();
	@JsonProperty("enabledUser")
	private Boolean enabledUser;
	@JsonProperty("givenName")
	private String givenName;
	@JsonProperty("familyName")
	private String familyName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("role")
	private RoleType role;
	@JsonProperty("identifier")
	private String identifier;
	@JsonProperty("email")
	private String email;
	@JsonProperty("sms")
	private String sms;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("agents")
	@JacksonXmlElementWrapper(localName = "agents") @JacksonXmlProperty(localName = "agent")
	private List<GUIDRef> agents = new ArrayList<>();
	@JsonProperty("orgs")
	@JacksonXmlElementWrapper(localName = "orgs") @JacksonXmlProperty(localName = "org")
	private List<GUIDRef> orgs = new ArrayList<>();
	@JsonProperty("grades")
	@JacksonXmlElementWrapper(localName = "grades") @JacksonXmlProperty(localName = "grade")
	private List<String> grades = new ArrayList<>();
	@JsonProperty("password")
	private String password;

	public User() { }

	public User(String username, List<UserId> userIds, Boolean enabledUser, String givenName, String familyName, String middleName, RoleType role, String identifier, String email, String sms, String phone, List<GUIDRef> agents, List<GUIDRef> orgs, List<String> grades, String password) {
		super();
		this.username = username;
		this.userIds = userIds;
		this.enabledUser = enabledUser;
		this.givenName = givenName;
		this.familyName = familyName;
		this.middleName = middleName;
		this.role = role;
		this.identifier = identifier;
		this.email = email;
		this.sms = sms;
		this.phone = phone;
		this.agents = agents;
		this.orgs = orgs;
		this.grades = grades;
		this.password = password;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty("userIds")
	@JacksonXmlElementWrapper(localName = "userIds") @JacksonXmlProperty(localName = "userId")
	public List<UserId> getUserIds() {
		return userIds;
	}

	@JsonProperty("userIds")
	@JacksonXmlElementWrapper(localName = "userIds") @JacksonXmlProperty(localName = "userId")
	public void setUserIds(List<UserId> userIds) {
		this.userIds = userIds;
	}

	@JsonProperty("enabledUser")
	public Boolean getEnabledUser() {
		return enabledUser;
	}

	@JsonProperty("enabledUser")
	public void setEnabledUser(Boolean enabledUser) {
		this.enabledUser = enabledUser;
	}

	@JsonProperty("givenName")
	public String getGivenName() {
		return givenName;
	}

	@JsonProperty("givenName")
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	@JsonProperty("familyName")
	public String getFamilyName() {
		return familyName;
	}

	@JsonProperty("familyName")
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty("role")
	public RoleType getRole() {
		return role;
	}

	@JsonProperty("role")
	public void setRole(RoleType role) {
		this.role = role;
	}

	@JsonProperty("identifier")
	public String getIdentifier() {
		return identifier;
	}

	@JsonProperty("identifier")
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("sms")
	public String getSms() {
		return sms;
	}

	@JsonProperty("sms")
	public void setSms(String sms) {
		this.sms = sms;
	}

	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	@JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty("agents")
	@JacksonXmlElementWrapper(localName = "agents") @JacksonXmlProperty(localName = "agent")
	public List<GUIDRef> getAgents() {
		return agents;
	}

	@JsonProperty("agents")
	@JacksonXmlElementWrapper(localName = "agents") @JacksonXmlProperty(localName = "agent")
	public void setAgents(List<GUIDRef> agents) {
		this.agents = agents;
	}

	@JsonProperty("orgs")
	@JacksonXmlElementWrapper(localName = "orgs") @JacksonXmlProperty(localName = "org")
	public List<GUIDRef> getOrgs() {
		return orgs;
	}

	@JsonProperty("orgs")
	@JacksonXmlElementWrapper(localName = "orgs") @JacksonXmlProperty(localName = "org")
	public void setOrgs(List<GUIDRef> orgs) {
		this.orgs = orgs;
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

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	@Override
	public boolean isEmpty() {
		return Stream.of(super.getSourcedId(), super.getStatus(), super.getDateLastModified(), super.getMetadata(), username, userIds, enabledUser, givenName, familyName, middleName, role, identifier, email, sms, phone, agents, orgs, grades, password).allMatch(Objects::isNull);
	}
}