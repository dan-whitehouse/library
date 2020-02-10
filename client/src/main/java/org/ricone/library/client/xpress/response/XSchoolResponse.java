package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XSchool;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xSchool"})
public class XSchoolResponse extends XResponse<XSchool> {
	@JsonProperty("xSchool")
	private XSchool xSchool;

	public XSchoolResponse() { }

	public XSchoolResponse(XSchool xSchool) {
		super();
		this.xSchool = xSchool;
	}

	@JsonProperty("xSchool")
	public XSchool getXSchool() {
		return xSchool;
	}

	@JsonProperty("xSchool")
	public void setXSchool(XSchool xSchool) {
		this.xSchool = xSchool;
	}

	@Override
	public XSchool getData() {
		return xSchool;
	}

	@Override
	public void setData(XSchool data) {
		this.xSchool = data;
	}

	@Override
	public String toString() {
		return "XSchoolResponse{" + "xSchool=" + xSchool + '}';
	}
}