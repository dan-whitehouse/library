package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.ricone.library.client.core.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xStudent"})
@JsonRootName(value = "xStudents")
public class XStudents extends Model {
	@JsonProperty("xStudent")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XStudent> xStudent;

	public XStudents() {
		xStudent = new ArrayList<>();
	}

	public XStudents(List<XStudent> xStudent) {
		super();
		this.xStudent = xStudent;
	}

	@JsonProperty("xStudent")
	public List<XStudent> getXStudent() {
		return xStudent;
	}

	@JsonProperty("xStudent")
	public void setXStudent(List<XStudent> xStudent) {
		this.xStudent = xStudent;
	}

	@Override
	public boolean isEmpty() {
		return xStudent.isEmpty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XStudents xStudents = (XStudents) o;
		return Objects.equals(xStudent, xStudents.xStudent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(xStudent);
	}

	@Override
	public String toString() {
		return "XStudents{" + "xStudent=" + xStudent + '}';
	}
}