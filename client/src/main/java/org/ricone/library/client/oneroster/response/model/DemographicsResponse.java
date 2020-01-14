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
@JsonPropertyOrder({"warnings","demographics"})
public class DemographicsResponse extends BaseMultiResponse<Demographic> implements Serializable {
	private final static long serialVersionUID = 6089973485073338986L;

	public DemographicsResponse() {
	}

	public DemographicsResponse(List<Demographic> demographics) {
		super(demographics);
	}

	public DemographicsResponse(List<Demographic> demographics, List<java.lang.Error> errors) {
		super(demographics, errors);
	}

	@JsonProperty("demographics")
	@JacksonXmlElementWrapper(localName = "demographics") @JacksonXmlProperty(localName = "demographic")
	@Override public List<Demographic> getData() {
		return super.getData();
	}

	@JsonProperty("demographics")
	@JacksonXmlElementWrapper(localName = "demographics") @JacksonXmlProperty(localName = "demographic")
	@Override public void setData(List<Demographic> demographics) {
		super.setData(demographics);
	}
}