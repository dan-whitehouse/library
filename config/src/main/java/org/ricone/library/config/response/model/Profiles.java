package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "profiles")
public class Profiles implements ListWrapper<Profile> {
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Profile> profiles;

	public Profiles() {
		profiles = new ArrayList<>();
	}

	@JsonProperty("profile")
	public List<Profile> getProfiles() {
		return profiles;
	}

	@Override
	public List<Profile> getList() {
		return profiles;
	}

	@Override
	public void setList(List<Profile> list) {
		profiles = list;
	}

	@Override
	public String toString() {
		return "Profiles{" + "profiles=" + profiles + '}';
	}
}