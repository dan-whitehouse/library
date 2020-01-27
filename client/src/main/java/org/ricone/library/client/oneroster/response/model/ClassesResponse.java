package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.oneroster.response.BaseMultiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","classes"})
@JsonRootName("classes")
public class ClassesResponse extends BaseMultiResponse<Classes> implements Serializable {
	private final static long serialVersionUID = 5934808405475046263L;
	@JsonIgnore
	private String requestPath;
	@JsonIgnore
	private HttpHeaders requestHeaders;
	@JsonIgnore
	private HttpStatus responseStatus;
	@JsonIgnore
	private String responseStatusText;
	@JsonIgnore
	private HttpHeaders responseHeaders;

	public ClassesResponse() {
	}

	public ClassesResponse(Classes classes) {
		super(classes);
	}

	public ClassesResponse(Classes classes, List<Error> errors) {
		super(classes, errors);
	}

	@JsonUnwrapped @JsonProperty("classes")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "class")
	@Override public Classes getData() {
		return super.getData();
	}

	@JsonUnwrapped @JsonProperty("classes")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "class")
	@Override public void setData(Classes classes) {
		super.setData(classes);
	}

	@Override
	public String getRequestPath() {
		return requestPath;
	}

	@Override
	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	@Override
	public HttpHeaders getRequestHeaders() {
		return requestHeaders;
	}

	@Override
	public void setRequestHeaders(HttpHeaders requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	@Override
	public HttpStatus getResponseStatus() {
		return responseStatus;
	}

	@Override
	public void setResponseStatus(HttpStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	@Override
	public String getResponseStatusText() {
		return responseStatusText;
	}

	@Override
	public void setResponseStatusText(String responseStatusText) {
		this.responseStatusText = responseStatusText;
	}

	@Override
	public HttpHeaders getResponseHeaders() {
		return responseHeaders;
	}

	@Override
	public void setResponseHeaders(HttpHeaders responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
}