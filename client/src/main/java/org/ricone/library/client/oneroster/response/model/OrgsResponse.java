package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.oneroster.response.BaseMultiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","orgs"})
public class OrgsResponse extends BaseMultiResponse<Org> implements Serializable {
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

	public OrgsResponse(List<Org> orgs) {
		super(orgs);
	}

	public OrgsResponse(List<Org> list, List<Error> errors) {
		super(list, errors);
	}

	@JsonProperty("orgs")
	@JacksonXmlElementWrapper(localName = "orgs") @JacksonXmlProperty(localName = "org")
	@Override public List<Org> getData() {
		return super.getData();
	}

	@JsonProperty("orgs")
	@JacksonXmlElementWrapper(localName = "orgs") @JacksonXmlProperty(localName = "org")
	@Override public void setData(List<Org> orgs) {
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