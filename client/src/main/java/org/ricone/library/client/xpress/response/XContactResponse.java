package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XContact;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xContact"})
public class XContactResponse extends XResponse<XContact> {
	@JsonProperty("xContact")
	private XContact xContact;

	public XContactResponse() { }

	public XContactResponse(XContact xContact) {
		super();
		this.xContact = xContact;
	}

	@JsonProperty("xContact")
	public XContact getXContact() {
		return xContact;
	}

	@JsonProperty("xContact")
	public void setXContact(XContact xContact) {
		this.xContact = xContact;
	}

	@Override
	public XContact getData() {
		return xContact;
	}

	@Override
	public void setData(XContact data) {
		this.xContact = data;
	}

	@Override
	public String toString() {
		return "XContactResponse{" + "xContact=" + xContact + '}';
	}
}