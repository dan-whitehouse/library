package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XCalendar;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xCalendar"})
public class XCalendarResponse extends XResponse<XCalendar> {
	@JsonProperty("xCalendar")
	private XCalendar xCalendar;

	public XCalendarResponse() { }

	public XCalendarResponse(XCalendar xCalendar) {
		super();
		this.xCalendar = xCalendar;
	}

	@JsonProperty("xCalendar")
	public XCalendar getXCalendar() {
		return xCalendar;
	}

	@JsonProperty("xCalendar")
	public void setXCalendar(XCalendar xCalendar) {
		this.xCalendar = xCalendar;
	}

	@Override
	public XCalendar getData() {
		return xCalendar;
	}

	@Override
	public void setData(XCalendar data) {
		this.xCalendar = data;
	}

	@Override
	public String toString() {
		return "XCalendarResponse{" + "xCalendar=" + xCalendar + '}';
	}
}