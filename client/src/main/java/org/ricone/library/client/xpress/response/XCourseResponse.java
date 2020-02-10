package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XCourse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xCourse"})
public class XCourseResponse extends XResponse<XCourse> {
	@JsonProperty("xCourse")
	private XCourse xCourse;

	public XCourseResponse() {
		xCourse = new XCourse();
	}

	public XCourseResponse(XCourse xCourse) {
		super();
		this.xCourse = xCourse;
	}

	@JsonProperty("xCourse")
	public XCourse getXCourse() {
		return xCourse;
	}

	@JsonProperty("xCourse")
	public void setXCourse(XCourse xCourse) {
		this.xCourse = xCourse;
	}

	@Override
	public XCourse getData() {
		return xCourse;
	}

	@Override
	public void setData(XCourse data) {
		this.xCourse = data;
	}

	@Override
	public String toString() {
		return "XCourseResponse{" + "xCourse=" + xCourse + '}';
	}
}