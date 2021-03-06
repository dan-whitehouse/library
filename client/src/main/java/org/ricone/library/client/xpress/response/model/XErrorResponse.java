package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.library.client.xpress.response.model.XError;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"error"})
public class XErrorResponse implements Serializable {

	@JsonProperty("error")
	private XError error;

	/**
	 * No args constructor for use in serialization
	 */
	public XErrorResponse() {
	}

	/**
	 * @param error
	 */
	public XErrorResponse(XError error) {
		super();
		this.error = error;
	}

	@JsonProperty("error")
	public XError getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(XError error) {
		this.error = error;
	}
}