/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XStudent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xStudent"})
public class XStudentResponse extends XResponse<XStudent> {
	@JsonProperty("xStudent")
	private XStudent xStudent;

	public XStudentResponse() { }

	public XStudentResponse(XStudent xStudent) {
		super();
		this.xStudent = xStudent;
	}

	@JsonProperty("xStudent")
	public XStudent getXStudent() {
		return xStudent;
	}

	@JsonProperty("xStudent")
	public void setXStudent(XStudent xStudent) {
		this.xStudent = xStudent;
	}

	@Override
	public XStudent getData() {
		return xStudent;
	}

	@Override
	public void setData(XStudent data) {
		this.xStudent = data;
	}

	@Override
	public String toString() {
		return "XStudentResponse{" + "xStudent=" + xStudent + '}';
	}
}