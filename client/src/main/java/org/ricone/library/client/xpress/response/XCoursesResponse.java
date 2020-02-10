package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XCourses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xCourses"})
public class XCoursesResponse extends XResponse<XCourses> {
	@JsonProperty("xCourses")
	private XCourses xCourses;

	public XCoursesResponse() { }

	public XCoursesResponse(XCourses xCourses) {
		super();
		this.xCourses = xCourses;
	}

	@JsonProperty("xCourses")
	public XCourses getXCourses() {
		return xCourses;
	}

	@JsonProperty("xCourses")
	public void setXCourses(XCourses xCourses) {
		this.xCourses = xCourses;
	}

	@Override
	public XCourses getData() {
		return xCourses;
	}

	@Override
	public void setData(XCourses data) {
		this.xCourses = data;
	}

	@Override
	public String toString() {
		return "XCoursesResponse{" + "xCourses=" + xCourses + '}';
	}
}