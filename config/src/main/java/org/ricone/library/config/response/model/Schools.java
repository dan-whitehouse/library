package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "schools")
public class Schools implements ListWrapper<School> {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<School> schools;

	public Schools() {
		schools = new ArrayList<>();
	}

	@JsonProperty("school")
	public List<School> getSchools() {
		return schools;
	}

	@Override
	public List<School> getList() { return schools; }

	@Override
	public void setList(List<School> list) {
		schools = list;
	}

	@Override
	public String toString() {
		return "Schools{" + "schools=" + schools + '}';
	}
}