package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"protection", "otherPurpose", "location", "app_id", "purpose", "encryption", "expirationDate", "correction", "disposal", "protectionSubContractors", "id", "retention", "privacyPolicy"})
public class Privacy implements Serializable {

	@JsonProperty("protection")
	private String protection;
	@JsonProperty("otherPurpose")
	private String otherPurpose;
	@JsonProperty("location")
	private String location;
	@JsonProperty("app_id")
	private String appId;
	@JsonProperty("purpose")
	private String purpose;
	@JsonProperty("encryption")
	private String encryption;
	@JsonProperty("expirationDate")
	private String expirationDate;
	@JsonProperty("correction")
	private String correction;
	@JsonProperty("disposal")
	private String disposal;
	@JsonProperty("protectionSubContractors")
	private String protectionSubContractors;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("retention")
	private String retention;
	@JsonProperty("privacyPolicy")
	private String privacyPolicy;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 2448800736089642246L;

	/**
	 * No args constructor for use in serialization
	 */
	public Privacy() {
	}

	/**
	 * @param id
	 * @param protection
	 * @param protectionSubContractors
	 * @param expirationDate
	 * @param appId
	 * @param privacyPolicy
	 * @param disposal
	 * @param encryption
	 * @param location
	 * @param retention
	 * @param correction
	 * @param purpose
	 * @param otherPurpose
	 */
	public Privacy(String protection, String otherPurpose, String location, String appId, String purpose, String encryption, String expirationDate, String correction, String disposal, String protectionSubContractors, Integer id, String retention, String privacyPolicy) {
		super();
		this.protection = protection;
		this.otherPurpose = otherPurpose;
		this.location = location;
		this.appId = appId;
		this.purpose = purpose;
		this.encryption = encryption;
		this.expirationDate = expirationDate;
		this.correction = correction;
		this.disposal = disposal;
		this.protectionSubContractors = protectionSubContractors;
		this.id = id;
		this.retention = retention;
		this.privacyPolicy = privacyPolicy;
	}

	@JsonProperty("protection")
	public String getProtection() {
		return protection;
	}

	@JsonProperty("protection")
	public void setProtection(String protection) {
		this.protection = protection;
	}

	@JsonProperty("otherPurpose")
	public String getOtherPurpose() {
		return otherPurpose;
	}

	@JsonProperty("otherPurpose")
	public void setOtherPurpose(String otherPurpose) {
		this.otherPurpose = otherPurpose;
	}

	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(String location) {
		this.location = location;
	}

	@JsonProperty("app_id")
	public String getAppId() {
		return appId;
	}

	@JsonProperty("app_id")
	public void setAppId(String appId) {
		this.appId = appId;
	}

	@JsonProperty("purpose")
	public String getPurpose() {
		return purpose;
	}

	@JsonProperty("purpose")
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@JsonProperty("encryption")
	public String getEncryption() {
		return encryption;
	}

	@JsonProperty("encryption")
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

	@JsonProperty("expirationDate")
	public String getExpirationDate() {
		return expirationDate;
	}

	@JsonProperty("expirationDate")
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonProperty("correction")
	public String getCorrection() {
		return correction;
	}

	@JsonProperty("correction")
	public void setCorrection(String correction) {
		this.correction = correction;
	}

	@JsonProperty("disposal")
	public String getDisposal() {
		return disposal;
	}

	@JsonProperty("disposal")
	public void setDisposal(String disposal) {
		this.disposal = disposal;
	}

	@JsonProperty("protectionSubContractors")
	public String getProtectionSubContractors() {
		return protectionSubContractors;
	}

	@JsonProperty("protectionSubContractors")
	public void setProtectionSubContractors(String protectionSubContractors) {
		this.protectionSubContractors = protectionSubContractors;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("retention")
	public String getRetention() {
		return retention;
	}

	@JsonProperty("retention")
	public void setRetention(String retention) {
		this.retention = retention;
	}

	@JsonProperty("privacyPolicy")
	public String getPrivacyPolicy() {
		return privacyPolicy;
	}

	@JsonProperty("privacyPolicy")
	public void setPrivacyPolicy(String privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Privacy{" + "protection='" + protection + '\'' + ", otherPurpose='" + otherPurpose + '\'' + ", location='" + location + '\'' + ", appId='" + appId + '\'' + ", purpose='" + purpose + '\'' + ", encryption='" + encryption + '\'' + ", expirationDate='" + expirationDate + '\'' + ", correction='" + correction + '\'' + ", disposal='" + disposal + '\'' + ", protectionSubContractors='" + protectionSubContractors + '\'' + ", id=" + id + ", retention='" + retention + '\'' + ", privacyPolicy='" + privacyPolicy + '\'' + ", additionalProperties=" + additionalProperties + '}';
	}
}