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
@JsonPropertyOrder({"warnings","enrollments"})
public class EnrollmentsResponse extends BaseMultiResponse<Enrollment> implements Serializable {
	private final static long serialVersionUID = -3656792804387663344L;
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

	public EnrollmentsResponse() {
	}

	public EnrollmentsResponse(List<Enrollment> enrollments) {
		super(enrollments);
	}

	public EnrollmentsResponse(List<Enrollment> enrollments, List<Error> errors) {
		super(enrollments);
	}

	@JsonProperty("enrollments")
	@JacksonXmlElementWrapper(localName = "enrollments") @JacksonXmlProperty(localName = "enrollment")
	@Override public List<Enrollment> getData() {
		return super.getData();
	}

	@JsonProperty("enrollments")
	@JacksonXmlElementWrapper(localName = "enrollments") @JacksonXmlProperty(localName = "enrollment")
	@Override public void setData(List<Enrollment> enrollments) {
		super.setData(enrollments);
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