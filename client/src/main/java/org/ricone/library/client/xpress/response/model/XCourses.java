package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.ricone.library.client.core.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xCourse"})
@JsonRootName(value = "xCourses")
public class XCourses extends Model {
	@JsonProperty("xCourse")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XCourse> xCourse;

	public XCourses() {
		xCourse = new ArrayList<>();
	}

	public XCourses(List<XCourse> xCourse) {
		super();
		this.xCourse = xCourse;
	}

	@JsonProperty("xCourse")
	public List<XCourse> getXCourse() {
		return xCourse;
	}

	@JsonProperty("xCourse")
	public void setXCourse(List<XCourse> xCourse) {
		this.xCourse = xCourse;
	}

	@Override
	public boolean isEmpty() {
		return xCourse.isEmpty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XCourses xCourses = (XCourses) o;
		return Objects.equals(xCourse, xCourses.xCourse);
	}

	@Override
	public int hashCode() {
		return Objects.hash(xCourse);
	}

	@Override
	public String toString() {
		return "XCourses{" + "xCourse=" + xCourse + '}';
	}
}