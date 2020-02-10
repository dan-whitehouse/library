package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.oneroster.response.BaseMultiResponse;
import org.ricone.library.client.oneroster.response.model.Courses;
import org.ricone.library.client.oneroster.response.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-31
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","courses"})
@JsonRootName("courses")
public class CoursesResponse extends BaseMultiResponse<Courses> implements Serializable {
	private final static long serialVersionUID = 8240007651262912068L;

	public CoursesResponse() { }

	public CoursesResponse(Courses courses) {
		super(courses);
	}

	public CoursesResponse(Courses courses, List<Error> errors) {
		super(courses);
	}

	@JsonUnwrapped @JsonProperty("courses")
	@JacksonXmlElementWrapper(useWrapping = false)  @JacksonXmlProperty(localName = "course")
	@Override public Courses getData() {
		return super.getData();
	}

	@JsonUnwrapped @JsonProperty("courses")
	@JacksonXmlElementWrapper(useWrapping = false)  @JacksonXmlProperty(localName = "course")
	@Override public void setData(Courses course) {
		super.setData(course);
	}
}