package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"county", "department", "district_id", "street", "name", "mobilePhone", "officePhone", "state", "email", "city", "zip", "fax", "title", "id"})
public class Contact implements Serializable {

	@JsonProperty("county")
	private String county;
	@JsonProperty("department")
	private String department;
	@JsonProperty("district_id")
	private String districtId;
	@JsonProperty("street")
	private String street;
	@JsonProperty("name")
	private String name;
	@JsonProperty("mobilePhone")
	private String mobilePhone;
	@JsonProperty("officePhone")
	private String officePhone;
	@JsonProperty("state")
	private String state;
	@JsonProperty("email")
	private String email;
	@JsonProperty("city")
	private String city;
	@JsonProperty("zip")
	private String zip;
	@JsonProperty("fax")
	private String fax;
	@JsonProperty("title")
	private String title;
	@JsonProperty("id")
	private Integer id;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 6066472642782412191L;

	/**
	 * No args constructor for use in serialization
	 */
	public Contact() {
	}

	/**
	 * @param id
	 * @param zip
	 * @param title
	 * @param fax
	 * @param districtId
	 * @param mobilePhone
	 * @param email
	 * @param county
	 * @param department
	 * @param name
	 * @param street
	 * @param state
	 * @param officePhone
	 * @param city
	 */
	public Contact(String county, String department, String districtId, String street, String name, String mobilePhone, String officePhone, String state, String email, String city, String zip, String fax, String title, Integer id) {
		super();
		this.county = county;
		this.department = department;
		this.districtId = districtId;
		this.street = street;
		this.name = name;
		this.mobilePhone = mobilePhone;
		this.officePhone = officePhone;
		this.state = state;
		this.email = email;
		this.city = city;
		this.zip = zip;
		this.fax = fax;
		this.title = title;
		this.id = id;
	}

	@JsonProperty("county")
	public String getCounty() {
		return county;
	}

	@JsonProperty("county")
	public void setCounty(String county) {
		this.county = county;
	}

	@JsonProperty("department")
	public String getDepartment() {
		return department;
	}

	@JsonProperty("department")
	public void setDepartment(String department) {
		this.department = department;
	}

	@JsonProperty("district_id")
	public String getDistrictId() {
		return districtId;
	}

	@JsonProperty("district_id")
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@JsonProperty("street")
	public String getStreet() {
		return street;
	}

	@JsonProperty("street")
	public void setStreet(String street) {
		this.street = street;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("mobilePhone")
	public String getMobilePhone() {
		return mobilePhone;
	}

	@JsonProperty("mobilePhone")
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@JsonProperty("officePhone")
	public String getOfficePhone() {
		return officePhone;
	}

	@JsonProperty("officePhone")
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("zip")
	public String getZip() {
		return zip;
	}

	@JsonProperty("zip")
	public void setZip(String zip) {
		this.zip = zip;
	}

	@JsonProperty("fax")
	public String getFax() {
		return fax;
	}

	@JsonProperty("fax")
	public void setFax(String fax) {
		this.fax = fax;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
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

}