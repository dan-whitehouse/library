package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"sftpPassword", "sftpUsername", "href", "webminUsername", "name", "webminPassword", "id"})
public class Provider implements Serializable {

	@JsonProperty("sftpPassword")
	private String sftpPassword;
	@JsonProperty("sftpUsername")
	private String sftpUsername;
	@JsonProperty("href")
	private String href;
	@JsonProperty("webminUsername")
	private String webminUsername;
	@JsonProperty("name")
	private String name;
	@JsonProperty("webminPassword")
	private String webminPassword;
	@JsonProperty("id")
	private String id;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 1215003996596903223L;

	/**
	 * No args constructor for use in serialization
	 */
	public Provider() {
	}

	/**
	 * @param id
	 * @param webminPassword
	 * @param sftpPassword
	 * @param webminUsername
	 * @param name
	 * @param sftpUsername
	 * @param href
	 */
	public Provider(String sftpPassword, String sftpUsername, String href, String webminUsername, String name, String webminPassword, String id) {
		super();
		this.sftpPassword = sftpPassword;
		this.sftpUsername = sftpUsername;
		this.href = href;
		this.webminUsername = webminUsername;
		this.name = name;
		this.webminPassword = webminPassword;
		this.id = id;
	}

	@JsonProperty("sftpPassword")
	public String getSftpPassword() {
		return sftpPassword;
	}

	@JsonProperty("sftpPassword")
	public void setSftpPassword(String sftpPassword) {
		this.sftpPassword = sftpPassword;
	}

	@JsonProperty("sftpUsername")
	public String getSftpUsername() {
		return sftpUsername;
	}

	@JsonProperty("sftpUsername")
	public void setSftpUsername(String sftpUsername) {
		this.sftpUsername = sftpUsername;
	}

	@JsonProperty("href")
	public String getHref() {
		return href;
	}

	@JsonProperty("href")
	public void setHref(String href) {
		this.href = href;
	}

	@JsonProperty("webminUsername")
	public String getWebminUsername() {
		return webminUsername;
	}

	@JsonProperty("webminUsername")
	public void setWebminUsername(String webminUsername) {
		this.webminUsername = webminUsername;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("webminPassword")
	public String getWebminPassword() {
		return webminPassword;
	}

	@JsonProperty("webminPassword")
	public void setWebminPassword(String webminPassword) {
		this.webminPassword = webminPassword;
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

}