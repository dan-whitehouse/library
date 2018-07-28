package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "apps")
public class Apps implements ListWrapper<App> {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<App> apps;

	public Apps() {
		apps = new ArrayList<>();
	}

	@JsonProperty("app")
	public List<App> getApps() {
		return apps;
	}

	@Override
	public void setList(List<App> list) {
		apps = list;
	}

	@Override
	public String toString() {
		return "Apps{" + "apps=" + apps + '}';
	}
}