package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XStaff;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xStaff"})
public class XStaffResponse extends XResponse<XStaff> {
	@JsonProperty("xStaff")
	private XStaff xStaff;

	public XStaffResponse() { }

	public XStaffResponse(XStaff xStaff) {
		super();
		this.xStaff = xStaff;
	}

	@JsonProperty("xStaff")
	public XStaff getXStaff() {
		return xStaff;
	}

	@JsonProperty("xStaff")
	public void setXStaff(XStaff xStaff) {
		this.xStaff = xStaff;
	}

	@Override
	public XStaff getData() {
		return xStaff;
	}

	@Override
	public void setData(XStaff data) {
		this.xStaff = data;
	}

	@Override
	public String toString() {
		return "XStaffResponse{" + "xStaff=" + xStaff + '}';
	}
}