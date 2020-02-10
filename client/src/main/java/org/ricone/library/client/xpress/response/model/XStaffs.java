package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.ricone.library.client.core.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xStaff"})
@JsonRootName(value = "xStaffs")
public class XStaffs extends Model {
	@JsonProperty("xStaff")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XStaff> xStaff;

	public XStaffs() {
		xStaff = new ArrayList<>();
	}

	public XStaffs(List<XStaff> xStaff) {
		super();
		this.xStaff = xStaff;
	}

	@JsonProperty("xStaff")
	public List<XStaff> getXStaff() {
		return xStaff;
	}

	@JsonProperty("xStaff")
	public void setXStaff(List<XStaff> xStaff) {
		this.xStaff = xStaff;
	}

	@Override
	public boolean isEmpty() {
		return xStaff.isEmpty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XStaffs xStaffs = (XStaffs) o;
		return Objects.equals(xStaff, xStaffs.xStaff);
	}

	@Override
	public int hashCode() {
		return Objects.hash(xStaff);
	}

	@Override
	public String toString() {
		return "XStaffs{" + "xStaff=" + xStaff + '}';
	}
}