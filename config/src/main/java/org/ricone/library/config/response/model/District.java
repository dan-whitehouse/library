package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"county", "dnsFriendlyName", "city", "fedEntityId", "province", "enabled", "id", "fedBypassApi", "fedBypassLinking", "postcode", "officePhone", "name", "provider_id", "status", "street"})
public class District implements Serializable {

	@JsonProperty("county")
	private String county;
	@JsonProperty("dnsFriendlyName")
	private String dnsFriendlyName;
	@JsonProperty("city")
	private String city;
	@JsonProperty("fedEntityId")
	private String fedEntityId;
	@JsonProperty("province")
	private String province;
	@JsonProperty("enabled")
	private Boolean enabled;
	@JsonProperty("id")
	private String id;
	@JsonProperty("fedBypassApi")
	private String fedBypassApi;
	@JsonProperty("fedBypassLinking")
	private String fedBypassLinking;
	@JsonProperty("postcode")
	private String postcode;
	@JsonProperty("officePhone")
	private String officePhone;
	@JsonProperty("name")
	private String name;
	@JsonProperty("provider_id")
	private String providerId;
	@JsonProperty("status")
	private String status;
	@JsonProperty("street")
	private String street;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -8479831273303342702L;

	/**
	 * No args constructor for use in serialization
	 */
	public District() {
	}

	/**
	 * @param enabled
	 * @param status
	 * @param fedBypassLinking
	 * @param street
	 * @param fedBypassApi
	 * @param fedEntityId
	 * @param postcode
	 * @param officePhone
	 * @param city
	 * @param id
	 * @param county
	 * @param name
	 * @param dnsFriendlyName
	 * @param province
	 * @param providerId
	 */
	public District(String county, String dnsFriendlyName, String city, String fedEntityId, String province, Boolean enabled, String id, String fedBypassApi, String fedBypassLinking, String postcode, String officePhone, String name, String providerId, String status, String street) {
		super();
		this.county = county;
		this.dnsFriendlyName = dnsFriendlyName;
		this.city = city;
		this.fedEntityId = fedEntityId;
		this.province = province;
		this.enabled = enabled;
		this.id = id;
		this.fedBypassApi = fedBypassApi;
		this.fedBypassLinking = fedBypassLinking;
		this.postcode = postcode;
		this.officePhone = officePhone;
		this.name = name;
		this.providerId = providerId;
		this.status = status;
		this.street = street;
	}

	@JsonProperty("county")
	public String getCounty() {
		return county;
	}

	@JsonProperty("county")
	public void setCounty(String county) {
		this.county = county;
	}

	@JsonProperty("dnsFriendlyName")
	public String getDnsFriendlyName() {
		return dnsFriendlyName;
	}

	@JsonProperty("dnsFriendlyName")
	public void setDnsFriendlyName(String dnsFriendlyName) {
		this.dnsFriendlyName = dnsFriendlyName;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("fedEntityId")
	public String getFedEntityId() {
		return fedEntityId;
	}

	@JsonProperty("fedEntityId")
	public void setFedEntityId(String fedEntityId) {
		this.fedEntityId = fedEntityId;
	}

	@JsonProperty("province")
	public String getProvince() {
		return province;
	}

	@JsonProperty("province")
	public void setProvince(String province) {
		this.province = province;
	}

	@JsonProperty("enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	@JsonProperty("enabled")
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("fedBypassApi")
	public String getFedBypassApi() {
		return fedBypassApi;
	}

	@JsonProperty("fedBypassApi")
	public void setFedBypassApi(String fedBypassApi) {
		this.fedBypassApi = fedBypassApi;
	}

	@JsonProperty("fedBypassLinking")
	public String getFedBypassLinking() {
		return fedBypassLinking;
	}

	@JsonProperty("fedBypassLinking")
	public void setFedBypassLinking(String fedBypassLinking) {
		this.fedBypassLinking = fedBypassLinking;
	}

	@JsonProperty("postcode")
	public String getPostcode() {
		return postcode;
	}

	@JsonProperty("postcode")
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@JsonProperty("officePhone")
	public String getOfficePhone() {
		return officePhone;
	}

	@JsonProperty("officePhone")
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("provider_id")
	public String getProviderId() {
		return providerId;
	}

	@JsonProperty("provider_id")
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("street")
	public String getStreet() {
		return street;
	}

	@JsonProperty("street")
	public void setStreet(String street) {
		this.street = street;
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
		return "District{" + "county='" + county + '\'' + ", dnsFriendlyName='" + dnsFriendlyName + '\'' + ", city='" + city + '\'' + ", fedEntityId='" + fedEntityId + '\'' + ", province='" + province + '\'' + ", enabled=" + enabled + ", id='" + id + '\'' + ", fedBypassApi='" + fedBypassApi + '\'' + ", fedBypassLinking='" + fedBypassLinking + '\'' + ", postcode='" + postcode + '\'' + ", officePhone='" + officePhone + '\'' + ", name='" + name + '\'' + ", providerId='" + providerId + '\'' + ", status='" + status + '\'' + ", street='" + street + '\'' + ", additionalProperties=" + additionalProperties + '}';
	}
}