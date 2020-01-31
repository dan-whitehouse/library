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
@JsonPropertyOrder({"warnings","demographics"})
@JsonRootName("demographics")
public class DemographicsResponse extends BaseMultiResponse<Demographics> implements Serializable {
	private final static long serialVersionUID = 6089973485073338986L;

	public DemographicsResponse() { }

	public DemographicsResponse(Demographics demographics) {
		super(demographics);
	}

	public DemographicsResponse(Demographics demographics, List<Error> errors) {
		super(demographics, errors);
	}

	@JsonUnwrapped @JsonProperty("demographics")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "demographic")
	@Override public Demographics getData() {
		return super.getData();
	}

	@JsonUnwrapped @JsonProperty("demographics")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "demographic")
	@Override public void setData(Demographics demographics) {
		super.setData(demographics);
	}
}