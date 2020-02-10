package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.core.Model;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "leaRefId", "localId", "stateProvinceId", "otherIds", "schoolName", "gradeLevels", "address", "phoneNumber", "otherPhoneNumbers", "metadata"})
@JsonRootName(value = "xSchool")
public class XSchool extends Model {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("leaRefId")
	private String leaRefId;
	@JsonProperty("localId")
	private String localId;
	@JsonProperty("stateProvinceId")
	private String stateProvinceId;
	@JsonProperty("otherIds")
	private OtherIds otherIds;
	@JsonProperty("schoolName")
	private String schoolName;
	@JsonProperty("gradeLevels")
	private GradeLevels gradeLevels;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("phoneNumber")
	private PhoneNumber phoneNumber;
	@JsonProperty("otherPhoneNumbers")
	private OtherPhoneNumbers otherPhoneNumbers;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XSchool() { }

	public XSchool(String refId, String leaRefId, String localId, String stateProvinceId, OtherIds otherIds, String schoolName, GradeLevels gradeLevels, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers, Metadata metadata) {
		this.refId = refId;
		this.leaRefId = leaRefId;
		this.localId = localId;
		this.stateProvinceId = stateProvinceId;
		this.otherIds = otherIds;
		this.schoolName = schoolName;
		this.gradeLevels = gradeLevels;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.otherPhoneNumbers = otherPhoneNumbers;
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

	@JsonProperty("leaRefId")
	public String getLeaRefId() {
		return leaRefId;
	}

	@JsonProperty("leaRefId")
	public void setLeaRefId(String leaRefId) {
		this.leaRefId = leaRefId;
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

	@JsonProperty("schoolName")
	public String getSchoolName() {
		return schoolName;
	}

	@JsonProperty("schoolName")
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@JsonProperty("gradeLevels")
	public GradeLevels getGradeLevels() {
		return gradeLevels;
	}

	@JsonProperty("gradeLevels")
	public void setGradeLevels(GradeLevels gradeLevels) {
		this.gradeLevels = gradeLevels;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonProperty("phoneNumber")
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("otherPhoneNumbers")
	public OtherPhoneNumbers getOtherPhoneNumbers() {
		return otherPhoneNumbers;
	}

	@JsonProperty("otherPhoneNumbers")
	public void setOtherPhoneNumbers(OtherPhoneNumbers otherPhoneNumbers) {
		this.otherPhoneNumbers = otherPhoneNumbers;
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
		return Stream.of(refId, leaRefId, localId, stateProvinceId, otherIds, schoolName, gradeLevels, address, phoneNumber, otherPhoneNumbers, metadata).allMatch(Objects::isNull);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XSchool xSchool = (XSchool) o;
		return Objects.equals(refId, xSchool.refId) &&
				Objects.equals(leaRefId, xSchool.leaRefId) &&
				Objects.equals(localId, xSchool.localId) &&
				Objects.equals(stateProvinceId, xSchool.stateProvinceId) &&
				Objects.equals(otherIds, xSchool.otherIds) &&
				Objects.equals(schoolName, xSchool.schoolName) &&
				Objects.equals(gradeLevels, xSchool.gradeLevels) &&
				Objects.equals(address, xSchool.address) &&
				Objects.equals(phoneNumber, xSchool.phoneNumber) &&
				Objects.equals(otherPhoneNumbers, xSchool.otherPhoneNumbers) &&
				Objects.equals(metadata, xSchool.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(refId, leaRefId, localId, stateProvinceId, otherIds, schoolName, gradeLevels, address, phoneNumber, otherPhoneNumbers, metadata);
	}

	@Override
	public String toString() {
		return "XSchool{" + "refId='" + refId + '\'' + ", leaRefId='" + leaRefId + '\'' + ", localId='" + localId + '\'' + ", stateProvinceId='" + stateProvinceId + '\'' + ", otherIds=" + otherIds + ", schoolName='" + schoolName + '\'' + ", gradeLevels=" + gradeLevels + ", address=" + address + ", phoneNumber=" + phoneNumber + ", otherPhoneNumbers=" + otherPhoneNumbers + ", metadata=" + metadata + '}';
	}
}