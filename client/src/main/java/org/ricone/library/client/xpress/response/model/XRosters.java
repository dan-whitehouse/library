package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.ricone.library.client.core.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xRoster"})
@JsonRootName(value = "xRosters")
public class XRosters extends Model {
	@JsonProperty("xRoster")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XRoster> xRoster;

	public XRosters() {
		xRoster = new ArrayList<>();
	}

	public XRosters(List<XRoster> xRoster) {
		super();
		this.xRoster = xRoster;
	}

	@JsonProperty("xRoster")
	public List<XRoster> getXRoster() {
		return xRoster;
	}

	@JsonProperty("xRoster")
	public void setXRoster(List<XRoster> xRoster) {
		this.xRoster = xRoster;
	}

	@Override
	public boolean isEmpty() {
		return xRoster.isEmpty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XRosters xRosters = (XRosters) o;
		return Objects.equals(xRoster, xRosters.xRoster);
	}

	@Override
	public int hashCode() {
		return Objects.hash(xRoster);
	}

	@Override
	public String toString() {
		return "XRosters{" + "xRoster=" + xRoster + '}';
	}
}