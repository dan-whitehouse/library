package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.ricone.library.client.core.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xLea"})
@JsonRootName(value = "xLeas")
public class XLeas extends Model {
	@JsonProperty("xLea")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XLea> xLeas;

	public XLeas() {
		xLeas = new ArrayList<>();
	}

	public XLeas(List<XLea> xLeas) {
		super();
		this.xLeas = xLeas;
	}

	@JsonProperty("xLea")
	public List<XLea> getXLeas() {
		return xLeas;
	}

	@JsonProperty("xLea")
	public void setXLeas(List<XLea> xLea) {
		this.xLeas = xLea;
	}

	@Override
	public boolean isEmpty() {
		return xLeas.isEmpty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XLeas xLeas1 = (XLeas) o;
		return Objects.equals(xLeas, xLeas1.xLeas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(xLeas);
	}

	@Override
	public String toString() {
		return "XLeas{" + "xLeas=" + xLeas + '}';
	}
}