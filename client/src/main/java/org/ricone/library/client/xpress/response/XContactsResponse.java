package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XContacts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xContacts"})
public class XContactsResponse extends XResponse<XContacts> {
	@JsonProperty("xContacts")
	private XContacts xContacts;

	public XContactsResponse() {
		xContacts = new XContacts();
	}

	public XContactsResponse(XContacts xContacts) {
		super();
		this.xContacts = xContacts;
	}

	@JsonProperty("xContacts")
	public XContacts getXContacts() {
		return xContacts;
	}

	@JsonProperty("xContacts")
	public void setXContacts(XContacts xContacts) {
		this.xContacts = xContacts;
	}

	@Override
	public XContacts getData() {
		return xContacts;
	}

	@Override
	public void setData(XContacts data) {
		this.xContacts = data;
	}

	@Override
	public String toString() {
		return "XContactsResponse{" + "xContacts=" + xContacts + '}';
	}
}