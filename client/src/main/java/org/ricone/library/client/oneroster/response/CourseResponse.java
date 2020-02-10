package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.library.client.oneroster.response.BaseSingleResponse;
import org.ricone.library.client.oneroster.response.model.Course;
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
@JsonPropertyOrder({"warnings","course"})
public class CourseResponse extends BaseSingleResponse<Course> implements Serializable {
	private final static long serialVersionUID = -6447433608404298239L;

	public CourseResponse() { }

	public CourseResponse(Course course) {
		super(course);
	}

	public CourseResponse(Course course, List<Error> errors) {
		super(course, errors);
	}

	@JsonProperty("course")
	@Override public Course getData() {
		return super.getData();
	}

	@JsonProperty("course")
	@Override public void setData(Course course) {
		super.setData(course);
	}
}