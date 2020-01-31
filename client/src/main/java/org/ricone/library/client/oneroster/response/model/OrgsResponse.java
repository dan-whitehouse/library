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
@JsonPropertyOrder({"warnings","orgs"})
@JsonRootName("orgs")
public class OrgsResponse extends BaseMultiResponse<Orgs> implements Serializable {
	private final static long serialVersionUID = -5609856877368582544L;

	public OrgsResponse() { }

	public OrgsResponse(Orgs orgs) {
		super(orgs);
	}

	public OrgsResponse(Orgs list, List<Error> errors) {
		super(list, errors);
	}

	@JsonUnwrapped @JsonProperty("orgs")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "org")
	@Override public Orgs getData() {
		return super.getData();
	}

	@JsonUnwrapped @JsonProperty("orgs")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "org")
	@Override public void setData(Orgs orgs) {
		super.setData(orgs);
	}
}