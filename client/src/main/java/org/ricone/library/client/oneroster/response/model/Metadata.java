package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"ricone.schoolYear", "ricone.districtId"})
public class Metadata implements Serializable {
	private final static long serialVersionUID = -210985162537038327L;
	@JsonProperty("ricone.schoolYear")
	private Integer schoolYear;
	@JsonProperty("ricone.districtId")
	private String districtId;

	@JsonIgnore
	private LinkedHashMap<String, Object> additionalProperties = new LinkedHashMap<>();

	public Metadata() {
	}

	@JsonProperty("ricone.schoolYear")
	public Integer getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("ricone.schoolYear")
	public void setSchoolYear(Integer schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonProperty("ricone.districtId")
	public String getDistrictId() {
		return districtId;
	}

	@JsonProperty("ricone.districtId")
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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