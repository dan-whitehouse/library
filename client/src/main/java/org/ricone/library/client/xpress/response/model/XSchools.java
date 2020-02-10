package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.ricone.library.client.core.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xSchool"})
@JsonRootName(value = "xSchools")
public class XSchools extends Model {
	@JsonProperty("xSchool")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XSchool> xSchool;

	public XSchools() {
		xSchool = new ArrayList<>();
	}

	public XSchools(List<XSchool> xSchool) {
		super();
		this.xSchool = xSchool;
	}

	@JsonProperty("xSchool")
	public List<XSchool> getXSchool() {
		return xSchool;
	}

	@JsonProperty("xSchool")
	public void setXSchool(List<XSchool> xSchool) {
		this.xSchool = xSchool;
	}

	@Override
	public boolean isEmpty() {
		return xSchool.isEmpty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XSchools xSchools = (XSchools) o;
		return Objects.equals(xSchool, xSchools.xSchool);
	}

	@Override
	public int hashCode() {
		return Objects.hash(xSchool);
	}

	@Override
	public String toString() {
		return "XSchools{" + "xSchool=" + xSchool + '}';
	}
}