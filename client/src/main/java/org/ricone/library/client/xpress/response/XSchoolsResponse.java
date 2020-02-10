package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XSchools;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xSchools"})
public class XSchoolsResponse extends XResponse<XSchools> {
	@JsonProperty("xSchools")
	private XSchools xSchools;

	public XSchoolsResponse() {
		xSchools = new XSchools();
	}

	public XSchoolsResponse(XSchools xSchools) {
		super();
		this.xSchools = xSchools;
	}

	@JsonProperty("xSchools")
	public XSchools getXSchools() {
		return xSchools;
	}

	@JsonProperty("xSchools")
	public void setXSchools(XSchools xSchools) {
		this.xSchools = xSchools;
	}

	@Override
	public XSchools getData() {
		return xSchools;
	}

	@Override
	public void setData(XSchools data) {
		this.xSchools = data;
	}


	@Override
	public String toString() {
		return "XSchoolsResponse{" + "xSchools=" + xSchools + '}';
	}
}