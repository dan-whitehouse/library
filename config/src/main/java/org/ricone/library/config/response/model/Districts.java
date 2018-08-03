package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "districts")
public class Districts implements ListWrapper<District> {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<District> districts;

	public Districts() {
		districts = new ArrayList<>();
	}

	@JsonProperty("district")
	public List<District> getDistricts() {
		return districts;
	}

	@Override
	public List<District> getList() {
		return districts;
	}

	@Override
	public void setList(List<District> list) {
		districts = list;
	}

	@Override
	public String toString() {
		return "Districts{" + "districts=" + districts + '}';
	}
}