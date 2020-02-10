package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.oneroster.response.BaseMultiResponse;
import org.ricone.library.client.oneroster.response.model.AcademicSessions;
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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"warnings","academicSessions"})
@JsonRootName("academicSessions")
public class AcademicSessionsResponse extends BaseMultiResponse<AcademicSessions> implements Serializable {
	private final static long serialVersionUID = 9018025310977717656L;

	public AcademicSessionsResponse() { }

	public AcademicSessionsResponse(AcademicSessions academicSessions) {
		super(academicSessions);
	}

	public AcademicSessionsResponse(AcademicSessions academicSessions, List<Error> errors) {
		super(academicSessions, errors);
	}

	@JsonUnwrapped @JsonProperty("academicSessions")
	@JacksonXmlElementWrapper(useWrapping = false)  @JacksonXmlProperty(localName = "academicSession")
	@Override public AcademicSessions getData() { return super.getData(); }

	@JsonUnwrapped @JsonProperty("academicSessions")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "academicSession")
	@Override public void setData(AcademicSessions academicSessions) { super.setData(academicSessions);}
}