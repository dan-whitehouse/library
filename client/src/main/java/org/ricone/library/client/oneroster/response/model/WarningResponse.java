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
@JsonPropertyOrder({"warnings"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "data")
public class WarningResponse implements Serializable {
	private final static long serialVersionUID = -5741810242117515723L;

	@JsonProperty("warnings")
	@JacksonXmlElementWrapper(localName = "warnings") @JacksonXmlProperty(localName = "warning")
	private List<java.lang.Error> warnings = new ArrayList<>();

	public WarningResponse() {
	}

	public WarningResponse(List<java.lang.Error> warnings) {
		super();
		this.warnings = warnings;
	}

	@JsonProperty("warnings")
	@JacksonXmlElementWrapper(localName = "warnings") @JacksonXmlProperty(localName = "warning")
	public List<java.lang.Error> getWarnings() {
		return warnings;
	}

	@JsonProperty("warnings")
	@JacksonXmlElementWrapper(localName = "warnings") @JacksonXmlProperty(localName = "warning")
	public void setWarnings(List<java.lang.Error> warnings) {
		this.warnings = warnings;
	}
}