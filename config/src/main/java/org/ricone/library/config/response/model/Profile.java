package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "title", "id", "description", "filterType"})
public class Profile implements Serializable {

	@JsonProperty("name")
	private String name;
	@JsonProperty("title")
	private String title;
	@JsonProperty("id")
	private String id;
	@JsonProperty("description")
	private String description;
	@JsonProperty("filterType")
	private String filterType;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 4284589446511278977L;

	/**
	 * No args constructor for use in serialization
	 */
	public Profile() {
	}

	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param name
	 * @param filterType
	 */
	public Profile(String name, String title, String id, String description, String filterType) {
		super();
		this.name = name;
		this.title = title;
		this.id = id;
		this.description = description;
		this.filterType = filterType;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
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
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("filterType")
	public String getFilterType() {
		return filterType;
	}

	@JsonProperty("filterType")
	public void setFilterType(String filterType) {
		this.filterType = filterType;
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
		return "Profile{" + "name='" + name + '\'' + ", title='" + title + '\'' + ", id='" + id + '\'' + ", description='" + description + '\'' + ", filterType='" + filterType + '\'' + ", additionalProperties=" + additionalProperties + '}';
	}
}