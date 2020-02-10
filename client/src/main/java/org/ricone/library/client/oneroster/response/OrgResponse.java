package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.oneroster.response.BaseSingleResponse;
import org.ricone.library.client.oneroster.response.model.Error;
import org.ricone.library.client.oneroster.response.model.Org;
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
@JsonPropertyOrder({"warnings","org"})
@JsonRootName("org")
public class OrgResponse extends BaseSingleResponse<Org> implements Serializable {
	private final static long serialVersionUID = 4198000642945588466L;

	public OrgResponse() { }

	public OrgResponse(Org org) {
		super(org);
	}

	public OrgResponse(Org org, List<Error> errors) {
		super(org, errors);
	}

	@JsonProperty("org")
	@Override public Org getData() {
		return super.getData();
	}

	@JsonProperty("org")
	@Override public void setData(Org org) {
		super.setData(org);
	}
}