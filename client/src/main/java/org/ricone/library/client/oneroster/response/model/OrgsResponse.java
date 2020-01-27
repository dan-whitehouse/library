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
@JsonPropertyOrder({"warnings","orgs"})
@JsonRootName("orgs")
public class OrgsResponse extends BaseMultiResponse<Orgs> implements Serializable {
	private final static long serialVersionUID = -5609856877368582544L;
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

	public OrgsResponse() {
	}

	public OrgsResponse(Orgs orgs) {
		super(orgs);
	}

	public OrgsResponse(Orgs list, List<Error> errors) {
		super(list, errors);
	}

	@JsonUnwrapped @JsonProperty("orgs")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "org")
	@Override public Orgs getData() {
		return super.getData();
	}

	@JsonUnwrapped @JsonProperty("orgs")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "org")
	@Override public void setData(Orgs orgs) {
		super.setData(orgs);
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