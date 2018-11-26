package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"adminLastName", "locStreet", "stateLocId", "orgTypeCode", "district_id", "adminFirstName", "enabled", "locState", "orgTypeLong", "adminPhone", "locName", "schoolYear", "tier2", "lastStatusDate", "locID", "id", "tier1", "locStatus", "locOpen", "locAddress2", "institutionCode", "locCity", "adminName", "stateCo", "parentDistName", "countyName", "locAddress", "orgSubType", "locZip4", "locZip", "locClose"})
public class School implements Serializable {

	@JsonProperty("adminLastName")
	private String adminLastName;
	@JsonProperty("locStreet")
	private String locStreet;
	@JsonProperty("stateLocId")
	private String stateLocId;
	@JsonProperty("orgTypeCode")
	private String orgTypeCode;
	@JsonProperty("district_id")
	private String districtId;
	@JsonProperty("adminFirstName")
	private String adminFirstName;
	@JsonProperty("enabled")
	private Boolean enabled;
	@JsonProperty("locState")
	private String locState;
	@JsonProperty("orgTypeLong")
	private String orgTypeLong;
	@JsonProperty("adminPhone")
	private String adminPhone;
	@JsonProperty("locName")
	private String locName;
	@JsonProperty("schoolYear")
	private String schoolYear;
	@JsonProperty("tier2")
	private String tier2;
	@JsonProperty("lastStatusDate")
	private String lastStatusDate;
	@JsonProperty("locID")
	private String locID;
	@JsonProperty("id")
	private String id;
	@JsonProperty("tier1")
	private String tier1;
	@JsonProperty("locStatus")
	private String locStatus;
	@JsonProperty("locOpen")
	private String locOpen;
	@JsonProperty("locAddress2")
	private String locAddress2;
	@JsonProperty("institutionCode")
	private String institutionCode;
	@JsonProperty("locCity")
	private String locCity;
	@JsonProperty("adminName")
	private String adminName;
	@JsonProperty("stateCo")
	private String stateCo;
	@JsonProperty("parentDistName")
	private String parentDistName;
	@JsonProperty("countyName")
	private String countyName;
	@JsonProperty("locAddress")
	private String locAddress;
	@JsonProperty("orgSubType")
	private String orgSubType;
	@JsonProperty("locZip4")
	private String locZip4;
	@JsonProperty("locZip")
	private String locZip;
	@JsonProperty("locClose")
	private String locClose;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -8215290283872587885L;

	/**
	 * No args constructor for use in serialization
	 */
	public School() {
	}

	/**
	 * @param lastStatusDate
	 * @param locOpen
	 * @param locZip
	 * @param parentDistName
	 * @param locClose
	 * @param locName
	 * @param locCity
	 * @param orgSubType
	 * @param id
	 * @param countyName
	 * @param districtId
	 * @param orgTypeCode
	 * @param locAddress2
	 * @param adminLastName
	 * @param stateLocId
	 * @param adminPhone
	 * @param locState
	 * @param enabled
	 * @param locZip4
	 * @param tier1
	 * @param locStatus
	 * @param adminName
	 * @param locStreet
	 * @param schoolYear
	 * @param stateCo
	 * @param institutionCode
	 * @param orgTypeLong
	 * @param locAddress
	 * @param adminFirstName
	 * @param tier2
	 * @param locID
	 */
	public School(String adminLastName, String locStreet, String stateLocId, String orgTypeCode, String districtId, String adminFirstName, Boolean enabled, String locState, String orgTypeLong, String adminPhone, String locName, String schoolYear, String tier2, String lastStatusDate, String locID, String id, String tier1, String locStatus, String locOpen, String locAddress2, String institutionCode, String locCity, String adminName, String stateCo, String parentDistName, String countyName, String locAddress, String orgSubType, String locZip4, String locZip, String locClose) {
		super();
		this.adminLastName = adminLastName;
		this.locStreet = locStreet;
		this.stateLocId = stateLocId;
		this.orgTypeCode = orgTypeCode;
		this.districtId = districtId;
		this.adminFirstName = adminFirstName;
		this.enabled = enabled;
		this.locState = locState;
		this.orgTypeLong = orgTypeLong;
		this.adminPhone = adminPhone;
		this.locName = locName;
		this.schoolYear = schoolYear;
		this.tier2 = tier2;
		this.lastStatusDate = lastStatusDate;
		this.locID = locID;
		this.id = id;
		this.tier1 = tier1;
		this.locStatus = locStatus;
		this.locOpen = locOpen;
		this.locAddress2 = locAddress2;
		this.institutionCode = institutionCode;
		this.locCity = locCity;
		this.adminName = adminName;
		this.stateCo = stateCo;
		this.parentDistName = parentDistName;
		this.countyName = countyName;
		this.locAddress = locAddress;
		this.orgSubType = orgSubType;
		this.locZip4 = locZip4;
		this.locZip = locZip;
		this.locClose = locClose;
	}

	@JsonProperty("adminLastName")
	public String getAdminLastName() {
		return adminLastName;
	}

	@JsonProperty("adminLastName")
	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}

	@JsonProperty("locStreet")
	public String getLocStreet() {
		return locStreet;
	}

	@JsonProperty("locStreet")
	public void setLocStreet(String locStreet) {
		this.locStreet = locStreet;
	}

	@JsonProperty("stateLocId")
	public String getStateLocId() {
		return stateLocId;
	}

	@JsonProperty("stateLocId")
	public void setStateLocId(String stateLocId) {
		this.stateLocId = stateLocId;
	}

	@JsonProperty("orgTypeCode")
	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	@JsonProperty("orgTypeCode")
	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	@JsonProperty("district_id")
	public String getDistrictId() {
		return districtId;
	}

	@JsonProperty("district_id")
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@JsonProperty("adminFirstName")
	public String getAdminFirstName() {
		return adminFirstName;
	}

	@JsonProperty("adminFirstName")
	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}

	@JsonProperty("enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	@JsonProperty("enabled")
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@JsonProperty("locState")
	public String getLocState() {
		return locState;
	}

	@JsonProperty("locState")
	public void setLocState(String locState) {
		this.locState = locState;
	}

	@JsonProperty("orgTypeLong")
	public String getOrgTypeLong() {
		return orgTypeLong;
	}

	@JsonProperty("orgTypeLong")
	public void setOrgTypeLong(String orgTypeLong) {
		this.orgTypeLong = orgTypeLong;
	}

	@JsonProperty("adminPhone")
	public String getAdminPhone() {
		return adminPhone;
	}

	@JsonProperty("adminPhone")
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	@JsonProperty("locName")
	public String getLocName() {
		return locName;
	}

	@JsonProperty("locName")
	public void setLocName(String locName) {
		this.locName = locName;
	}

	@JsonProperty("schoolYear")
	public String getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonProperty("tier2")
	public String getTier2() {
		return tier2;
	}

	@JsonProperty("tier2")
	public void setTier2(String tier2) {
		this.tier2 = tier2;
	}

	@JsonProperty("lastStatusDate")
	public String getLastStatusDate() {
		return lastStatusDate;
	}

	@JsonProperty("lastStatusDate")
	public void setLastStatusDate(String lastStatusDate) {
		this.lastStatusDate = lastStatusDate;
	}

	@JsonProperty("locID")
	public String getLocID() {
		return locID;
	}

	@JsonProperty("locID")
	public void setLocID(String locID) {
		this.locID = locID;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("tier1")
	public String getTier1() {
		return tier1;
	}

	@JsonProperty("tier1")
	public void setTier1(String tier1) {
		this.tier1 = tier1;
	}

	@JsonProperty("locStatus")
	public String getLocStatus() {
		return locStatus;
	}

	@JsonProperty("locStatus")
	public void setLocStatus(String locStatus) {
		this.locStatus = locStatus;
	}

	@JsonProperty("locOpen")
	public String getLocOpen() {
		return locOpen;
	}

	@JsonProperty("locOpen")
	public void setLocOpen(String locOpen) {
		this.locOpen = locOpen;
	}

	@JsonProperty("locAddress2")
	public String getLocAddress2() {
		return locAddress2;
	}

	@JsonProperty("locAddress2")
	public void setLocAddress2(String locAddress2) {
		this.locAddress2 = locAddress2;
	}

	@JsonProperty("institutionCode")
	public String getInstitutionCode() {
		return institutionCode;
	}

	@JsonProperty("institutionCode")
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	@JsonProperty("locCity")
	public String getLocCity() {
		return locCity;
	}

	@JsonProperty("locCity")
	public void setLocCity(String locCity) {
		this.locCity = locCity;
	}

	@JsonProperty("adminName")
	public String getAdminName() {
		return adminName;
	}

	@JsonProperty("adminName")
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@JsonProperty("stateCo")
	public String getStateCo() {
		return stateCo;
	}

	@JsonProperty("stateCo")
	public void setStateCo(String stateCo) {
		this.stateCo = stateCo;
	}

	@JsonProperty("parentDistName")
	public String getParentDistName() {
		return parentDistName;
	}

	@JsonProperty("parentDistName")
	public void setParentDistName(String parentDistName) {
		this.parentDistName = parentDistName;
	}

	@JsonProperty("countyName")
	public String getCountyName() {
		return countyName;
	}

	@JsonProperty("countyName")
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	@JsonProperty("locAddress")
	public String getLocAddress() {
		return locAddress;
	}

	@JsonProperty("locAddress")
	public void setLocAddress(String locAddress) {
		this.locAddress = locAddress;
	}

	@JsonProperty("orgSubType")
	public String getOrgSubType() {
		return orgSubType;
	}

	@JsonProperty("orgSubType")
	public void setOrgSubType(String orgSubType) {
		this.orgSubType = orgSubType;
	}

	@JsonProperty("locZip4")
	public String getLocZip4() {
		return locZip4;
	}

	@JsonProperty("locZip4")
	public void setLocZip4(String locZip4) {
		this.locZip4 = locZip4;
	}

	@JsonProperty("locZip")
	public String getLocZip() {
		return locZip;
	}

	@JsonProperty("locZip")
	public void setLocZip(String locZip) {
		this.locZip = locZip;
	}

	@JsonProperty("locClose")
	public String getLocClose() {
		return locClose;
	}

	@JsonProperty("locClose")
	public void setLocClose(String locClose) {
		this.locClose = locClose;
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
		return "School{" + "adminLastName='" + adminLastName + '\'' + ", locStreet='" + locStreet + '\'' + ", stateLocId='" + stateLocId + '\'' + ", orgTypeCode='" + orgTypeCode + '\'' + ", districtId='" + districtId + '\'' + ", adminFirstName='" + adminFirstName + '\'' + ", enabled=" + enabled + ", locState='" + locState + '\'' + ", orgTypeLong='" + orgTypeLong + '\'' + ", adminPhone='" + adminPhone + '\'' + ", locName='" + locName + '\'' + ", schoolYear='" + schoolYear + '\'' + ", tier2='" + tier2 + '\'' + ", lastStatusDate='" + lastStatusDate + '\'' + ", locID='" + locID + '\'' + ", id='" + id + '\'' + ", tier1='" + tier1 + '\'' + ", locStatus='" + locStatus + '\'' + ", locOpen='" + locOpen + '\'' + ", locAddress2='" + locAddress2 + '\'' + ", institutionCode='" + institutionCode + '\'' + ", locCity='" + locCity + '\'' + ", adminName='" + adminName + '\'' + ", stateCo='" + stateCo + '\'' + ", parentDistName='" + parentDistName + '\'' + ", countyName='" + countyName + '\'' + ", locAddress='" + locAddress + '\'' + ", orgSubType='" + orgSubType + '\'' + ", locZip4='" + locZip4 + '\'' + ", locZip='" + locZip + '\'' + ", locClose='" + locClose + '\'' + ", additionalProperties=" + additionalProperties + '}';
	}
}