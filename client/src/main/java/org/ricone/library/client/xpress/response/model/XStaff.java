package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.core.Model;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "name", "localId", "stateProvinceId", "otherIds", "sex", "email", "primaryAssignment", "otherAssignments", "metadata"})
@JsonRootName(value = "xStaff")
public class XStaff extends Model {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("name")
	private Name name;
	@JsonProperty("localId")
	private String localId;
	@JsonProperty("stateProvinceId")
	private String stateProvinceId;
	@JsonProperty("otherIds")
	private OtherIds otherIds;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("email")
	private Email email;
	@JsonProperty("primaryAssignment")
	private PrimaryAssignment primaryAssignment;
	@JsonProperty("otherAssignments")
	private OtherAssignments otherAssignments;
	@JsonProperty("appProvisioningInfo")
	private AppProvisioningInfo appProvisioningInfo;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XStaff() { }

	public XStaff(String refId, Name name, String localId, String stateProvinceId, OtherIds otherIds, String sex, Email email, PrimaryAssignment primaryAssignment, OtherAssignments otherAssignments, Metadata metadata) {
		this.refId = refId;
		this.name = name;
		this.localId = localId;
		this.stateProvinceId = stateProvinceId;
		this.otherIds = otherIds;
		this.sex = sex;
		this.email = email;
		this.primaryAssignment = primaryAssignment;
		this.otherAssignments = otherAssignments;
		this.metadata = metadata;
	}

	public XStaff(String refId, AppProvisioningInfo appProvisioningInfo) {
		super();
		this.refId = refId;
		this.appProvisioningInfo = appProvisioningInfo;
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

	@JsonProperty("name")
	public Name getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(Name name) {
		this.name = name;
	}

	@JsonProperty("localId")
	public String getLocalId() {
		return localId;
	}

	@JsonProperty("localId")
	public void setLocalId(String localId) {
		this.localId = localId;
	}

	@JsonProperty("stateProvinceId")
	public String getStateProvinceId() {
		return stateProvinceId;
	}

	@JsonProperty("stateProvinceId")
	public void setStateProvinceId(String stateProvinceId) {
		this.stateProvinceId = stateProvinceId;
	}

	@JsonProperty("otherIds")
	public OtherIds getOtherIds() {
		return otherIds;
	}

	@JsonProperty("otherIds")
	public void setOtherIds(OtherIds otherIds) {
		this.otherIds = otherIds;
	}

	@JsonProperty("sex")
	public String getSex() {
		return sex;
	}

	@JsonProperty("sex")
	public void setSex(String sex) {
		this.sex = sex;
	}

	@JsonProperty("email")
	public Email getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(Email email) {
		this.email = email;
	}

	@JsonProperty("primaryAssignment")
	public PrimaryAssignment getPrimaryAssignment() {
		return primaryAssignment;
	}

	@JsonProperty("primaryAssignment")
	public void setPrimaryAssignment(PrimaryAssignment primaryAssignment) {
		this.primaryAssignment = primaryAssignment;
	}

	@JsonProperty("otherAssignments")
	public OtherAssignments getOtherAssignments() {
		return otherAssignments;
	}

	@JsonProperty("otherAssignments")
	public void setOtherAssignments(OtherAssignments otherAssignments) {
		this.otherAssignments = otherAssignments;
	}

	@JsonProperty("appProvisioningInfo")
	public AppProvisioningInfo getAppProvisioningInfo() {
		return appProvisioningInfo;
	}

	@JsonProperty("appProvisioningInfo")
	public void setAppProvisioningInfo(AppProvisioningInfo appProvisioningInfo) {
		this.appProvisioningInfo = appProvisioningInfo;
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
		return Stream.of(refId, name, localId, stateProvinceId, otherIds, appProvisioningInfo, sex, email, primaryAssignment, otherAssignments, metadata).allMatch(Objects::isNull);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XStaff xStaff = (XStaff) o;
		return Objects.equals(refId, xStaff.refId) &&
				Objects.equals(name, xStaff.name) &&
				Objects.equals(localId, xStaff.localId) &&
				Objects.equals(stateProvinceId, xStaff.stateProvinceId) &&
				Objects.equals(otherIds, xStaff.otherIds) &&
				Objects.equals(sex, xStaff.sex) &&
				Objects.equals(email, xStaff.email) &&
				Objects.equals(primaryAssignment, xStaff.primaryAssignment) &&
				Objects.equals(otherAssignments, xStaff.otherAssignments) &&
				Objects.equals(appProvisioningInfo, xStaff.appProvisioningInfo) &&
				Objects.equals(metadata, xStaff.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(refId, name, localId, stateProvinceId, otherIds, sex, email, primaryAssignment, otherAssignments, appProvisioningInfo, metadata);
	}

	@Override
	public String toString() {
		return "XStaff{" + "refId='" + refId + '\'' + ", name=" + name + ", localId='" + localId + '\'' + ", stateProvinceId='" + stateProvinceId + '\'' + ", otherIds=" + otherIds + ", sex='" + sex + '\'' + ", email=" + email + ", primaryAssignment=" + primaryAssignment + ", otherAssignments=" + otherAssignments + ", metadata=" + metadata + '}';
	}
}