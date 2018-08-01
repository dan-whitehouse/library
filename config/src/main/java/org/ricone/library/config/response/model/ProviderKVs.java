package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "providerKVs")
public class ProviderKVs implements ListWrapper<ProviderKV> {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<ProviderKV> providerKVs;

	public ProviderKVs() {
		providerKVs = new ArrayList<>();
	}

	@JsonProperty("providerKV")
	public List<ProviderKV> getProviderKVs() {
		return providerKVs;
	}

	@Override
	public void setList(List<ProviderKV> list) {
		providerKVs = list;
	}

	@Override
	public String toString() {
		return "ProviderKVs{" + "providerKV=" + providerKVs + '}';
	}
}