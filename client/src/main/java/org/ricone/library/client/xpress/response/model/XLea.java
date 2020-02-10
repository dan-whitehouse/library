package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.core.Model;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "localId", "stateProvinceId", "ncesId", "leaName", "address", "phoneNumber", "otherPhoneNumbers", "metadata"})
@JsonRootName(value = "xLea")
public class XLea extends Model {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("localId")
	private String localId;
	@JsonProperty("stateProvinceId")
	private String stateProvinceId;
	@JsonProperty("ncesId")
	private String ncesId;
	@JsonProperty("leaName")
	private String leaName;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("phoneNumber")
	private PhoneNumber phoneNumber;
	@JsonProperty("otherPhoneNumbers")
	private OtherPhoneNumbers otherPhoneNumbers;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XLea() { }

	public XLea(String refId, String localId, String stateProvinceId, String ncesId, String leaName, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers, Metadata metadata) {
		this.refId = refId;
		this.localId = localId;
		this.stateProvinceId = stateProvinceId;
		this.ncesId = ncesId;
		this.leaName = leaName;
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

	@JsonProperty("ncesId")
	public String getNcesId() {
		return ncesId;
	}

	@JsonProperty("ncesId")
	public void setNcesId(String ncesId) {
		this.ncesId = ncesId;
	}

	@JsonProperty("leaName")
	public String getLeaName() {
		return leaName;
	}

	@JsonProperty("leaName")
	public void setLeaName(String leaName) {
		this.leaName = leaName;
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
		return Stream.of(refId, localId, stateProvinceId, ncesId, leaName, address, phoneNumber, otherPhoneNumbers, metadata).allMatch(Objects::isNull);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XLea xLea = (XLea) o;
		return Objects.equals(refId, xLea.refId) &&
				Objects.equals(localId, xLea.localId) &&
				Objects.equals(stateProvinceId, xLea.stateProvinceId) &&
				Objects.equals(ncesId, xLea.ncesId) &&
				Objects.equals(leaName, xLea.leaName) &&
				Objects.equals(address, xLea.address) &&
				Objects.equals(phoneNumber, xLea.phoneNumber) &&
				Objects.equals(otherPhoneNumbers, xLea.otherPhoneNumbers) &&
				Objects.equals(metadata, xLea.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(refId, localId, stateProvinceId, ncesId, leaName, address, phoneNumber, otherPhoneNumbers, metadata);
	}

	@Override
	public String toString() {
		return "XLea{" + "refId='" + refId + '\'' + ", localId='" + localId + '\'' + ", stateProvinceId='" + stateProvinceId + '\'' + ", ncesId='" + ncesId + '\'' + ", leaName='" + leaName + '\'' + ", address=" + address + ", phoneNumber=" + phoneNumber + ", otherPhoneNumbers=" + otherPhoneNumbers + ", metadata=" + metadata + '}';
	}
}