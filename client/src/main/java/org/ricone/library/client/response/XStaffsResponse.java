/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.library.client.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.response.model.XStaffs;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xStaffs"})
public class XStaffsResponse extends XResponse<XStaffs> {

	@JsonProperty("xStaffs")
	private XStaffs xStaffs;
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

	public XStaffsResponse() {
		xStaffs = new XStaffs();
	}

	public XStaffsResponse(XStaffs xStaffs) {
		super();
		this.xStaffs = xStaffs;
	}

	@JsonProperty("xStaffs")
	public XStaffs getXStaffs() {
		return xStaffs;
	}

	@JsonProperty("xStaffs")
	public void setXStaffs(XStaffs xStaffs) {
		this.xStaffs = xStaffs;
	}

	@Override
	public XStaffs getData() {
		return xStaffs;
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
		return "XStaffsResponse{" + "xStaffs=" + xStaffs + '}';
	}
}