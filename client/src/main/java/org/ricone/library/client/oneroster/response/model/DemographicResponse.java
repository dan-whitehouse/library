package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.library.client.oneroster.response.BaseSingleResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-31
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","demographics"})
public class DemographicResponse extends BaseSingleResponse<Demographic> implements Serializable {
	private final static long serialVersionUID = 6174150439900047310L;

	public DemographicResponse() { }

	public DemographicResponse(Demographic demographic) {
		super(demographic);
	}

	public DemographicResponse(Demographic demographic, List<Error> errors) {
		super(demographic, errors);
	}

	@JsonProperty("demographics")
	@Override public Demographic getData() {
		return super.getData();
	}

	@JsonProperty("demographics")
	@Override public void setData(Demographic demographic) {
		super.setData(demographic);
	}
}