/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.model.XContacts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xContacts"})
public class XContactsResponse extends XResponse<XContacts> {
	@JsonProperty("xContacts")
	private XContacts xContacts;
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

	public XContactsResponse() {
		xContacts = new XContacts();
	}

	public XContactsResponse(XContacts xContacts) {
		super();
		this.xContacts = xContacts;
	}

	@JsonProperty("xContacts")
	public XContacts getXContacts() {
		return xContacts;
	}

	@JsonProperty("xContacts")
	public void setXContacts(XContacts xContacts) {
		this.xContacts = xContacts;
	}

	@Override
	public XContacts getData() {
		return xContacts;
	}

	@Override
	public void setData(XContacts data) {
		this.xContacts = data;
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
		return "XContactsResponse{" + "xContacts=" + xContacts + '}';
	}
}