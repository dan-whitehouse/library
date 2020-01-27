package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"errors"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "data")
public class ErrorResponse implements Serializable {
	private final static long serialVersionUID = -2668305213966385627L;

	@JsonProperty("errors")
	@JacksonXmlElementWrapper(localName = "errors") @JacksonXmlProperty(localName = "error")
	private List<java.lang.Error> errors = new ArrayList<>();

	public ErrorResponse() {
	}

	public ErrorResponse(List<java.lang.Error> errors) {
		super();
		this.errors = errors;
	}

	@JsonProperty("errors")
	@JacksonXmlElementWrapper(localName = "errors") @JacksonXmlProperty(localName = "error")
	public List<java.lang.Error> getErrors() {
		return errors;
	}

	@JsonProperty("errors")
	@JacksonXmlElementWrapper(localName = "errors") @JacksonXmlProperty(localName = "error")
	public void setErrors(List<java.lang.Error> errors) {
		this.errors = errors;
	}
}