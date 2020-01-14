package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"ricone.schoolYear", "ricone.districtId"})
@ApiModel(description="This specification is silent on what implementers may consider to be appropriate extensions. Different objects may or may not include key/value pairs.\nExample:\n\"metadata\": {\n" + "\"ricone.schoolYear\": \"2019\",\n" + "\"ricone.districtId\": \"999999\",\n" + "\"address1\": \"123 Something Road\",\n" + "\"city\": \"Albany\",\n" + "\"state\": \"NY\",\n" + "\"postCode\": \"12205\",\n" + "\"country\": \"US\"\n" + "}")
public class Metadata implements Serializable {
	private final static long serialVersionUID = -210985162537038327L;

	@JsonProperty("ricone.schoolYear")
	@ApiModelProperty(position = 1, value = "", example = "2020")
	private Integer schoolYear;
	@JsonProperty("ricone.districtId")
	@ApiModelProperty(position = 2, value = "", example = "012345")
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