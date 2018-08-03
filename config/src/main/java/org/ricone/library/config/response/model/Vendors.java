package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "vendors")
public class Vendors implements ListWrapper<Vendor> {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Vendor> vendors;

	public Vendors() {
		vendors = new ArrayList<>();
	}

	@JsonProperty("vendor")
	public List<Vendor> getVendors() {
		return vendors;
	}

	@Override
	public List<Vendor> getList() { return vendors; }

	@Override
	public void setList(List<Vendor> list) {
		vendors = list;
	}

	@Override
	public String toString() {
		return "Vendors{" + "vendors=" + vendors + '}';
	}
}