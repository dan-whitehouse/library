/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XStudents;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xStudents"})
public class XStudentsResponse extends XResponse<XStudents> {
	@JsonProperty("xStudents")
	private XStudents xStudents;

	public XStudentsResponse() {
		xStudents = new XStudents();
	}

	public XStudentsResponse(XStudents xStudents) {
		super();
		this.xStudents = xStudents;
	}

	@JsonProperty("xStudents")
	public XStudents getXStudents() {
		return xStudents;
	}

	@JsonProperty("xStudents")
	public void setXStudents(XStudents xStudents) {
		this.xStudents = xStudents;
	}

	@Override
	public XStudents getData() {
		return xStudents;
	}

	@Override
	public void setData(XStudents data) {
		this.xStudents = data;
	}

	@Override
	public String toString() {
		return "XStudentsResponse{" + "xStudents=" + xStudents + '}';
	}
}