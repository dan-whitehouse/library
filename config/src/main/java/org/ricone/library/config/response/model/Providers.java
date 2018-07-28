package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "providers")
public class Providers implements ListWrapper<Provider> {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Provider> providers;

	public Providers() {
		providers = new ArrayList<>();
	}

	@JsonProperty("provider")
	public List<Provider> getProviders() {
		return providers;
	}

	@Override
	public void setList(List<Provider> list) {
		providers = list;
	}

	@Override
	public String toString() {
		return "Providers{" + "providers=" + providers + '}';
	}
}