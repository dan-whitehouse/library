package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.ricone.library.client.core.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xContact"})
@JsonRootName(value = "xContacts")
public class XContacts extends Model {
	@JsonProperty("xContact")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XContact> xContact;

	public XContacts() {
		xContact = new ArrayList<>();
	}

	public XContacts(List<XContact> xContact) {
		super();
		this.xContact = xContact;
	}

	@JsonProperty("xContact")
	public List<XContact> getXContact() {
		return xContact;
	}

	@JsonProperty("xContact")
	public void setXContact(List<XContact> xContact) {
		this.xContact = xContact;
	}

	@Override
	public boolean isEmpty() {
		return xContact.isEmpty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XContacts xContacts = (XContacts) o;
		return Objects.equals(xContact, xContacts.xContact);
	}

	@Override
	public int hashCode() {
		return Objects.hash(xContact);
	}

	@Override
	public String toString() {
		return "XContacts{" + "xContact=" + xContact + '}';
	}
}