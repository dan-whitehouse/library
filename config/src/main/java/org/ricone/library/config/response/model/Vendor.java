package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"website", "status", "name", "id", "enabled"})
public class Vendor implements Serializable {

	@JsonProperty("website")
	private String website;
	@JsonProperty("status")
	private String status;
	@JsonProperty("name")
	private String name;
	@JsonProperty("id")
	private String id;
	@JsonProperty("enabled")
	private Boolean enabled;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 7646289797093186634L;

	/**
	 * No args constructor for use in serialization
	 */
	public Vendor() {
	}

	/**
	 * @param id
	 * @param enabled
	 * @param status
	 * @param website
	 * @param name
	 */
	public Vendor(String website, String status, String name, String id, Boolean enabled) {
		super();
		this.website = website;
		this.status = status;
		this.name = name;
		this.id = id;
		this.enabled = enabled;
	}

	@JsonProperty("website")
	public String getWebsite() {
		return website;
	}

	@JsonProperty("website")
	public void setWebsite(String website) {
		this.website = website;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	@JsonProperty("enabled")
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
		return "Vendor{" + "website='" + website + '\'' + ", status='" + status + '\'' + ", name='" + name + '\'' + ", id='" + id + '\'' + ", enabled=" + enabled + ", additionalProperties=" + additionalProperties + '}';
	}
}