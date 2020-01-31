package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.library.client.oneroster.response.BaseSingleResponse;
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
@JsonPropertyOrder({"warnings","enrollment"})
public class EnrollmentResponse extends BaseSingleResponse<Enrollment> implements Serializable {
	private final static long serialVersionUID = 6097789264454176014L;

	public EnrollmentResponse() { }

	public EnrollmentResponse(Enrollment enrollment) {
		super(enrollment);
	}

	public EnrollmentResponse(Enrollment enrollment, List<java.lang.Error> errors) {
		super(enrollment);
	}

	@JsonProperty("enrollment")
	@Override public Enrollment getData() {
		return super.getData();
	}

	@JsonProperty("enrollment")
	@Override public void setData(Enrollment enrollment) {
		super.setData(enrollment);
	}
}