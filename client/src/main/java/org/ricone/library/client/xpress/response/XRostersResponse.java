package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XRosters;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xRosters"})
public class XRostersResponse extends XResponse<XRosters> {
	@JsonProperty("xRosters")
	private XRosters xRosters;

	public XRostersResponse() {
		xRosters = new XRosters();
	}

	public XRostersResponse(XRosters xRosters) {
		super();
		this.xRosters = xRosters;
	}

	@JsonProperty("xRosters")
	public XRosters getXRosters() {
		return xRosters;
	}

	@JsonProperty("xRosters")
	public void setXRosters(XRosters xRosters) {
		this.xRosters = xRosters;
	}

	@Override
	public XRosters getData() {
		return xRosters;
	}

	@Override
	public void setData(XRosters data) {
		this.xRosters = data;
	}

	@Override
	public String toString() {
		return "XRostersResponse{" + "xRosters=" + xRosters + '}';
	}
}