package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.ricone.library.client.oneroster.response.model.Errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"errors"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "errors")
@JsonRootName("errors")
public class ErrorsResponse implements Serializable {
	private final static long serialVersionUID = -2668305213966385627L;

	@JsonUnwrapped @JsonProperty("errors")
	@JacksonXmlElementWrapper(localName = "errors") @JacksonXmlProperty(localName = "error")
	private Errors errors;

	public ErrorsResponse() {
	}

	public ErrorsResponse(Errors errors) {
		super();
		this.errors = errors;
	}

	@JsonUnwrapped @JsonProperty("errors")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "error")
	public Errors getErrors() {
		return errors;
	}

	@JsonUnwrapped @JsonProperty("errors")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "error")
	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ErrorResponse{" +
				"errors=" + errors +
				'}';
	}
}