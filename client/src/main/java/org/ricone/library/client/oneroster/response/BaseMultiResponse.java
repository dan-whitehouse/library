package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.ricone.library.client.oneroster.response.model.Error;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"warnings"})
@JacksonXmlRootElement(localName = "data")
public abstract class BaseMultiResponse<T> extends Response<T> implements Serializable {
	private T data;
	private List<Error> warnings;

	protected BaseMultiResponse() {
	}

	public BaseMultiResponse(T data) {
		this.data = data;
	}

	public BaseMultiResponse(T data, List<Error> warnings) {
		this.data = data;
		this.warnings = warnings;
	}

	@Override public T getData() {
		return data;
	}

	@Override public void setData(T data) {
		this.data = data;
	}

	@JsonProperty("warnings")
	@JacksonXmlElementWrapper(localName = "warnings") @JacksonXmlProperty(localName = "warning")
	public List<Error> getWarnings() {
		return warnings;
	}

	@JsonProperty("warnings")
	@JacksonXmlElementWrapper(localName = "warnings") @JacksonXmlProperty(localName = "warning")
	public void setWarnings(List<Error> errors) {
		this.warnings = errors;
	}
}
