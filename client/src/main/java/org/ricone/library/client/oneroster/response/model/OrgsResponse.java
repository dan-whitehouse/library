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
@JsonPropertyOrder({"warnings","orgs"})
public class OrgsResponse extends BaseMultiResponse<Org> implements Serializable {
	private final static long serialVersionUID = -5609856877368582544L;

	public OrgsResponse() {
	}

	public OrgsResponse(List<Org> orgs) {
		super(orgs);
	}

	public OrgsResponse(List<Org> list, List<java.lang.Error> errors) {
		super(list, errors);
	}

	@JsonProperty("orgs")
	@JacksonXmlElementWrapper(localName = "orgs") @JacksonXmlProperty(localName = "org")
	@Override public List<Org> getData() {
		return super.getData();
	}

	@JsonProperty("orgs")
	@JacksonXmlElementWrapper(localName = "orgs") @JacksonXmlProperty(localName = "org")
	@Override public void setData(List<Org> orgs) {
		super.setData(orgs);
	}
}