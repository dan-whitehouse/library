package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XCalendars;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xCalendars"})
public class XCalendarsResponse extends XResponse<XCalendars> {
	@JsonProperty("xCalendars")
	private XCalendars xCalendars;

	public XCalendarsResponse() {
		xCalendars = new XCalendars();
	}

	public XCalendarsResponse(XCalendars xCalendars) {
		super();
		this.xCalendars = xCalendars;
	}

	@JsonProperty("xCalendars")
	public XCalendars getXCalendars() {
		return xCalendars;
	}

	@JsonProperty("xCalendars")
	public void setXCalendars(XCalendars xCalendars) {
		this.xCalendars = xCalendars;
	}

	@Override
	public XCalendars getData() {
		return xCalendars;
	}

	@Override
	public void setData(XCalendars data) {
		this.xCalendars = data;
	}

	@Override
	public String toString() {
		return "XCalendarsResponse{" + "xCalendars=" + xCalendars + '}';
	}
}