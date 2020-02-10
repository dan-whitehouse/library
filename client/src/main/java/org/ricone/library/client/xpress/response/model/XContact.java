/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.core.Model;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "name", "otherNames", "localId", "otherIds", "address", "phoneNumber", "otherPhoneNumbers", "email", "otherEmails", "sex", "employerType", "relationships", "metadata"})
@JsonRootName(value = "xContact")
public class XContact extends Model {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("name")
	private Name name;
	@JsonProperty("otherNames")
	private OtherNames otherNames;
	@JsonProperty("localId")
	private String localId;
	@JsonProperty("otherIds")
	private OtherIds otherIds;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("phoneNumber")
	private PhoneNumber phoneNumber;
	@JsonProperty("otherPhoneNumbers")
	private OtherPhoneNumbers otherPhoneNumbers;
	@JsonProperty("email")
	private Email email;
	@JsonProperty("otherEmails")
	private OtherEmails otherEmails;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("employerType")
	private String employerType;
	@JsonProperty("relationships")
	private Relationships relationships;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XContact() {
	}

	public XContact(String refId, Name name, OtherNames otherNames, String localId, OtherIds otherIds, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers, Email email, OtherEmails otherEmails, String sex, String employerType, Relationships relationships, Metadata metadata) {
		this.refId = refId;
		this.name = name;
		this.otherNames = otherNames;
		this.localId = localId;
		this.otherIds = otherIds;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.otherPhoneNumbers = otherPhoneNumbers;
		this.email = email;
		this.otherEmails = otherEmails;
		this.sex = sex;
		this.employerType = employerType;
		this.relationships = relationships;
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

	@JsonProperty("name")
	public Name getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(Name name) {
		this.name = name;
	}

	@JsonProperty("otherNames")
	public OtherNames getOtherNames() {
		return otherNames;
	}

	@JsonProperty("otherNames")
	public void setOtherNames(OtherNames otherNames) {
		this.otherNames = otherNames;
	}

	@JsonProperty("localId")
	public String getLocalId() {
		return localId;
	}

	@JsonProperty("localId")
	public void setLocalId(String localId) {
		this.localId = localId;
	}

	@JsonProperty("otherIds")
	public OtherIds getOtherIds() {
		return otherIds;
	}

	@JsonProperty("otherIds")
	public void setOtherIds(OtherIds otherIds) {
		this.otherIds = otherIds;
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

	@JsonProperty("email")
	public Email getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(Email email) {
		this.email = email;
	}

	@JsonProperty("otherEmails")
	public OtherEmails getOtherEmails() {
		return otherEmails;
	}

	@JsonProperty("otherEmails")
	public void setOtherEmails(OtherEmails otherEmails) {
		this.otherEmails = otherEmails;
	}

	@JsonProperty("sex")
	public String getSex() {
		return sex;
	}

	@JsonProperty("sex")
	public void setSex(String sex) {
		this.sex = sex;
	}

	@JsonProperty("employerType")
	public String getEmployerType() {
		return employerType;
	}

	@JsonProperty("employerType")
	public void setEmployerType(String employerType) {
		this.employerType = employerType;
	}

	@JsonProperty("relationships")
	public Relationships getRelationships() {
		return relationships;
	}

	@JsonProperty("relationships")
	public void setRelationships(Relationships relationships) {
		this.relationships = relationships;
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
		return Stream.of(refId, name, otherNames, localId, otherIds, address, phoneNumber, otherPhoneNumbers, email, otherEmails, sex, employerType, relationships, metadata).allMatch(Objects::isNull);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XContact xContact = (XContact) o;
		return Objects.equals(refId, xContact.refId) &&
				Objects.equals(name, xContact.name) &&
				Objects.equals(otherNames, xContact.otherNames) &&
				Objects.equals(localId, xContact.localId) &&
				Objects.equals(otherIds, xContact.otherIds) &&
				Objects.equals(address, xContact.address) &&
				Objects.equals(phoneNumber, xContact.phoneNumber) &&
				Objects.equals(otherPhoneNumbers, xContact.otherPhoneNumbers) &&
				Objects.equals(email, xContact.email) &&
				Objects.equals(otherEmails, xContact.otherEmails) &&
				Objects.equals(sex, xContact.sex) &&
				Objects.equals(employerType, xContact.employerType) &&
				Objects.equals(relationships, xContact.relationships) &&
				Objects.equals(metadata, xContact.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(refId, name, otherNames, localId, otherIds, address, phoneNumber, otherPhoneNumbers, email, otherEmails, sex, employerType, relationships, metadata);
	}

	@Override
	public String toString() {
		return "XContact{" + "refId='" + refId + '\'' + ", name=" + name + ", otherNames=" + otherNames + ", localId='" + localId + '\'' + ", otherIds=" + otherIds + ", address=" + address + ", phoneNumber=" + phoneNumber + ", otherPhoneNumbers=" + otherPhoneNumbers + ", email=" + email + ", otherEmails=" + otherEmails + ", sex='" + sex + '\'' + ", employerType='" + employerType + '\'' + ", relationships=" + relationships + ", metadata=" + metadata + '}';
	}
}