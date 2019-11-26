package org.ricone.library.client.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.response.model.XLea;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLea"})
public class XLeaResponse extends XResponse<XLea> {
	@JsonProperty("xLea")
	private XLea xLea;
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

	public XLeaResponse() {
	}

	public XLeaResponse(XLea xLea) {
		super();
		this.xLea = xLea;
	}

	@JsonProperty("xLea")
	public XLea getXLea() {
		return xLea;
	}

	@JsonProperty("xLea")
	public void setXLea(XLea xLea) {
		this.xLea = xLea;
	}

	@Override
	public XLea getData() {
		return xLea;
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

	@Override
	public String toString() {
		return "XLeaResponse{" + "xLea=" + xLea + '}';
	}
}