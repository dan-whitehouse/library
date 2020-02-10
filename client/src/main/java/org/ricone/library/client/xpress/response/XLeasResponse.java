package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XLeas;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLeas"})
public class XLeasResponse extends XResponse<XLeas> {
	@JsonProperty("xLeas")
	private XLeas xLeas;

	public XLeasResponse() {
		xLeas = new XLeas();
	}

	public XLeasResponse(XLeas xLeas) {
		super();
		this.xLeas = xLeas;
	}

	@JsonProperty("xLeas")
	public XLeas getXLeas() {
		return xLeas;
	}

	@JsonProperty("xLeas")
	public void setXLeas(XLeas xLeas) {
		this.xLeas = xLeas;
	}

	@Override
	public XLeas getData() {
		return xLeas;
	}

	@Override
	public void setData(XLeas data) {
		this.xLeas = data;
	}

	@Override
	public String toString() {
		return "XLeasResponse{" + "xLeas=" + xLeas + '}';
	}
}