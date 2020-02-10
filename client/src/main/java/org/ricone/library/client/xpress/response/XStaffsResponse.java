/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XStaffs;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xStaffs"})
public class XStaffsResponse extends XResponse<XStaffs> {
	@JsonProperty("xStaffs")
	private XStaffs xStaffs;

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
	public void setData(XStaffs data) {
		this.xStaffs = data;
	}

	@Override
	public String toString() {
		return "XStaffsResponse{" + "xStaffs=" + xStaffs + '}';
	}
}