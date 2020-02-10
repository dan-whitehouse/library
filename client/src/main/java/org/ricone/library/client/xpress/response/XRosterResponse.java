package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XRoster;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xRoster"})
public class XRosterResponse extends XResponse<XRoster> {
	@JsonProperty("xRoster")
	private XRoster xRoster;

	public XRosterResponse() { }

	public XRosterResponse(XRoster xRoster) {
		super();
		this.xRoster = xRoster;
	}

	@JsonProperty("xRoster")
	public XRoster getXRoster() {
		return xRoster;
	}

	@JsonProperty("xRoster")
	public void setXRoster(XRoster xRoster) {
		this.xRoster = xRoster;
	}

	@Override
	public XRoster getData() {
		return xRoster;
	}

	@Override
	public void setData(XRoster data) {
		this.xRoster = data;
	}

	@Override
	public String toString() {
		return "XRosterResponse{" + "xRoster=" + xRoster + '}';
	}
}