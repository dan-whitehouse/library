package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"profile_id", "data", "id"})
public class SIFData implements Serializable {

	@JsonProperty("profile_id")
	private String profileId;
	@JsonProperty("data")
	private String data;
	@JsonProperty("id")
	private String id;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -2863026839701702183L;

	/**
	 * No args constructor for use in serialization
	 */
	public SIFData() {
	}

	/**
	 * @param id
	 * @param profileId
	 * @param data
	 */
	public SIFData(String profileId, String data, String id) {
		super();
		this.profileId = profileId;
		this.data = data;
		this.id = id;
	}

	@JsonProperty("profile_id")
	public String getProfileId() {
		return profileId;
	}

	@JsonProperty("profile_id")
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	@JsonProperty("data")
	public String getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(String data) {
		this.data = data;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
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
		return "SIFData{" + "profileId='" + profileId + '\'' + ", data='" + data + '\'' + ", id='" + id + '\'' + ", additionalProperties=" + additionalProperties + '}';
	}
}