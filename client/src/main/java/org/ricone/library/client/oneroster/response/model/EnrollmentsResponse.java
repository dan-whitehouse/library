package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.oneroster.response.BaseMultiResponse;
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
@JsonPropertyOrder({"warnings","enrollments"})
@JsonRootName("enrollments")
public class EnrollmentsResponse extends BaseMultiResponse<Enrollments> implements Serializable {
	private final static long serialVersionUID = -3656792804387663344L;

	public EnrollmentsResponse() { }

	public EnrollmentsResponse(Enrollments enrollments) {
		super(enrollments);
	}

	public EnrollmentsResponse(Enrollments enrollments, List<Error> errors) {
		super(enrollments);
	}

	@JsonUnwrapped @JsonProperty("enrollments")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "enrollment")
	@Override public Enrollments getData() {
		return super.getData();
	}

	@JsonUnwrapped @JsonProperty("enrollments")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "enrollment")
	@Override public void setData(Enrollments enrollments) {
		super.setData(enrollments);
	}
}