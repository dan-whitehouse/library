package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","courses"})
public class CoursesResponse extends BaseMultiResponse<Course> implements Serializable {
	private final static long serialVersionUID = 8240007651262912068L;

	public CoursesResponse() {
	}

	public CoursesResponse(List<Course> course) {
		super(course);
	}

	public CoursesResponse(List<Course> course, List<java.lang.Error> errors) {
		super(course);
	}

	@JsonProperty("courses")
	@JacksonXmlElementWrapper(localName = "courses") @JacksonXmlProperty(localName = "course")
	@Override public List<Course> getData() {
		return super.getData();
	}

	@JsonProperty("courses")
	@JacksonXmlElementWrapper(localName = "courses") @JacksonXmlProperty(localName = "course")
	@Override public void setData(List<Course> course) {
		super.setData(course);
	}

}