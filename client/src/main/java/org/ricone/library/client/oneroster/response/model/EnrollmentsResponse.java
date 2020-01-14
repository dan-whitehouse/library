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
@JsonPropertyOrder({"warnings","enrollments"})
public class EnrollmentsResponse extends BaseMultiResponse<Enrollment> implements Serializable {
	private final static long serialVersionUID = -3656792804387663344L;

	public EnrollmentsResponse() {
	}

	public EnrollmentsResponse(List<Enrollment> enrollments) {
		super(enrollments);
	}

	public EnrollmentsResponse(List<Enrollment> enrollments, List<java.lang.Error> errors) {
		super(enrollments);
	}

	@JsonProperty("enrollments")
	@JacksonXmlElementWrapper(localName = "enrollments") @JacksonXmlProperty(localName = "enrollment")
	@Override public List<Enrollment> getData() {
		return super.getData();
	}

	@JsonProperty("enrollments")
	@JacksonXmlElementWrapper(localName = "enrollments") @JacksonXmlProperty(localName = "enrollment")
	@Override public void setData(List<Enrollment> enrollments) {
		super.setData(enrollments);
	}
}