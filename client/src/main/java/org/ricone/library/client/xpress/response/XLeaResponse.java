package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XLea;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLea"})
public class XLeaResponse extends XResponse<XLea> {
	@JsonProperty("xLea")
	private XLea xLea;

	public XLeaResponse() { }

	public XLeaResponse(XLea xLea) {
		super();
		this.xLea = xLea;
	}

	@JsonProperty("xLea")
	public XLea getXLea() {
		return xLea;
	}

	@JsonProperty("xLea")
	public void setXLea(XLea xLea) {
		this.xLea = xLea;
	}

	@Override
	public XLea getData() {
		return xLea;
	}

	@Override
	public void setData(XLea data) {
		this.xLea = data;
	}

	@Override
	public String toString() {
		return "XLeaResponse{" + "xLea=" + xLea + '}';
	}
}