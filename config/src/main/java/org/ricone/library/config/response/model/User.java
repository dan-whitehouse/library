package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"email", "userType", "provider_id", "district_id", "vendor_id", "realm", "username", "emailVerified", "id"})
public class User implements Serializable {

	@JsonProperty("email")
	private String email;
	@JsonProperty("userType")
	private String userType;
	@JsonProperty("provider_id")
	private String providerId;
	@JsonProperty("district_id")
	private String districtId;
	@JsonProperty("vendor_id")
	private String vendorId;
	@JsonProperty("realm")
	private String realm;
	@JsonProperty("username")
	private String username;
	@JsonProperty("emailVerified")
	private Boolean emailVerified;
	@JsonProperty("id")
	private Integer id;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 6716529391478828267L;

	/**
	 * No args constructor for use in serialization
	 */
	public User() {
	}

	/**
	 * @param id
	 * @param username
	 * @param districtId
	 * @param realm
	 * @param email
	 * @param providerId
	 * @param vendorId
	 * @param emailVerified
	 * @param userType
	 */
	public User(String email, String userType, String providerId, String districtId, String vendorId, String realm, String username, Boolean emailVerified, Integer id) {
		super();
		this.email = email;
		this.userType = userType;
		this.providerId = providerId;
		this.districtId = districtId;
		this.vendorId = vendorId;
		this.realm = realm;
		this.username = username;
		this.emailVerified = emailVerified;
		this.id = id;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("userType")
	public String getUserType() {
		return userType;
	}

	@JsonProperty("userType")
	public void setUserType(String userType) {
		this.userType = userType;
	}

	@JsonProperty("provider_id")
	public String getProviderId() {
		return providerId;
	}

	@JsonProperty("provider_id")
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@JsonProperty("district_id")
	public String getDistrictId() {
		return districtId;
	}

	@JsonProperty("district_id")
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@JsonProperty("vendor_id")
	public String getVendorId() {
		return vendorId;
	}

	@JsonProperty("vendor_id")
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	@JsonProperty("realm")
	public String getRealm() {
		return realm;
	}

	@JsonProperty("realm")
	public void setRealm(String realm) {
		this.realm = realm;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty("emailVerified")
	public Boolean getEmailVerified() {
		return emailVerified;
	}

	@JsonProperty("emailVerified")
	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
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
		return "User{" + "email='" + email + '\'' + ", userType='" + userType + '\'' + ", providerId='" + providerId + '\'' + ", districtId='" + districtId + '\'' + ", vendorId='" + vendorId + '\'' + ", realm='" + realm + '\'' + ", username='" + username + '\'' + ", emailVerified=" + emailVerified + ", id=" + id + ", additionalProperties=" + additionalProperties + '}';
	}
}