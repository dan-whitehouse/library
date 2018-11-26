package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "district_id", "value", "field", "app_id"})
public class DAA implements Serializable {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("district_id")
	private String districtId;
	@JsonProperty("value")
	private String value;
	@JsonProperty("field")
	private String field;
	@JsonProperty("app_id")
	private String appId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -5830994502814228060L;

	/**
	 * No args constructor for use in serialization
	 */
	public DAA() {
	}

	/**
	 * @param field
	 * @param id
	 * @param appId
	 * @param districtId
	 * @param value
	 */
	public DAA(Integer id, String districtId, String value, String field, String appId) {
		super();
		this.id = id;
		this.districtId = districtId;
		this.value = value;
		this.field = field;
		this.appId = appId;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("district_id")
	public String getDistrictId() {
		return districtId;
	}

	@JsonProperty("district_id")
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	@JsonProperty("field")
	public String getField() {
		return field;
	}

	@JsonProperty("field")
	public void setField(String field) {
		this.field = field;
	}

	@JsonProperty("app_id")
	public String getAppId() {
		return appId;
	}

	@JsonProperty("app_id")
	public void setAppId(String appId) {
		this.appId = appId;
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
		return "DAA{" + "id=" + id + ", districtId='" + districtId + '\'' + ", value='" + value + '\'' + ", field='" + field + '\'' + ", appId='" + appId + '\'' + ", additionalProperties=" + additionalProperties + '}';
	}
}