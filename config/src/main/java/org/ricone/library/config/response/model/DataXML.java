package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xml"})
public class DataXML implements Serializable {

	@JsonProperty("xml")
	private String xml;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -8567503549463169788L;

	/**
	 * No args constructor for use in serialization
	 */
	public DataXML() {
	}

	/**
	 * @param xml
	 */
	public DataXML(String xml) {
		super();
		this.xml = xml;
	}

	@JsonProperty("xml")
	public String getXml() {
		return xml;
	}

	@JsonProperty("xml")
	public void setXml(String xml) {
		this.xml = xml;
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