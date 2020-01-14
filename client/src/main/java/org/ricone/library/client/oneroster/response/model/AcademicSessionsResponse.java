package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"warnings","academicSessions"})
public class AcademicSessionsResponse extends BaseMultiResponse<AcademicSession> implements Serializable {
	private final static long serialVersionUID = 9018025310977717656L;

	public AcademicSessionsResponse() {
	}

	public AcademicSessionsResponse(List<AcademicSession> academicSessions) {
		super(academicSessions);
	}

	public AcademicSessionsResponse(List<AcademicSession> academicSessions, List<java.lang.Error> errors) {
		super(academicSessions, errors);
	}

	@JsonProperty("academicSessions")
	@JacksonXmlElementWrapper(localName = "academicSessions") @JacksonXmlProperty(localName = "academicSession")
	@Override public List<AcademicSession> getData() { return super.getData(); }

	@JsonProperty("academicSessions")
	@JacksonXmlElementWrapper(localName = "academicSessions") @JacksonXmlProperty(localName = "academicSession")
	@Override public void setData(List<AcademicSession> academicSessions) { super.setData(academicSessions);}
}