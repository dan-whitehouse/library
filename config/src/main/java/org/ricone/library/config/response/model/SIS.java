package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"district_id", "value", "id", "field"})
public class SIS implements Serializable {

	@JsonProperty("district_id")
	private String districtId;
	@JsonProperty("value")
	private String value;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("field")
	private String field;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -5525150656178698148L;

	/**
	 * No args constructor for use in serialization
	 */
	public SIS() {
	}

	/**
	 * @param field
	 * @param id
	 * @param districtId
	 * @param value
	 */
	public SIS(String districtId, String value, Integer id, String field) {
		super();
		this.districtId = districtId;
		this.value = value;
		this.id = id;
		this.field = field;
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

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("field")
	public String getField() {
		return field;
	}

	@JsonProperty("field")
	public void setField(String field) {
		this.field = field;
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