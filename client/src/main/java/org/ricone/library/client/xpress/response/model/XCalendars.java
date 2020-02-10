package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.ricone.library.client.core.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xCalendar"})
@JsonRootName(value = "xCalendars")
public class XCalendars extends Model {
	@JsonProperty("xCalendar")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XCalendar> xCalendar;

	public XCalendars() {
		xCalendar = new ArrayList<>();
	}

	public XCalendars(List<XCalendar> xCalendar) {
		super();
		this.xCalendar = xCalendar;
	}

	@JsonProperty("xCalendar")
	public List<XCalendar> getXCalendar() {
		return xCalendar;
	}

	@JsonProperty("xCalendar")
	public void setXCalendar(List<XCalendar> xCalendar) {
		this.xCalendar = xCalendar;
	}

	@Override
	public boolean isEmpty() {
		return xCalendar.isEmpty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XCalendars that = (XCalendars) o;
		return Objects.equals(xCalendar, that.xCalendar);
	}

	@Override
	public int hashCode() {
		return Objects.hash(xCalendar);
	}

	@Override
	public String toString() {
		return "XCalendars{" + "xCalendar=" + xCalendar + '}';
	}
}