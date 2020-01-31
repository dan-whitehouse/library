package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XLeas;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLeas"})
public class XLeasResponse extends XResponse<XLeas> {
	@JsonProperty("xLeas")
	private XLeas xLeas;
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

	public XLeasResponse() {
		xLeas = new XLeas();
	}

	public XLeasResponse(XLeas xLeas) {
		super();
		this.xLeas = xLeas;
	}

	@JsonProperty("xLeas")
	public XLeas getXLeas() {
		return xLeas;
	}

	@JsonProperty("xLeas")
	public void setXLeas(XLeas xLeas) {
		this.xLeas = xLeas;
	}

	@Override
	public XLeas getData() {
		return xLeas;
	}

	@Override
	public void setData(XLeas data) {
		this.xLeas = data;
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
	public String getResponseStatusText() {
		return responseStatusText;
	}

	@Override
	public void setResponseStatusText(String responseStatusText) {
		this.responseStatusText = responseStatusText;
	}

	@Override
	public void setResponseStatus(HttpStatus responseStatus) {
		this.responseStatus = responseStatus;
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
		return "XLeasResponse{" + "xLeas=" + xLeas + '}';
	}
}