/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.library.client.xpress.response.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"race"})
public class Races {

	@JsonProperty("race")
	private List<Race> race;

	public Races() {
		race = new ArrayList<>();
	}

	public Races(List<Race> race) {
		super();
		this.race = race;
	}

	@JsonProperty("race")
	public List<Race> getRace() {
		return race;
	}

	@JsonProperty("race")
	public void setRace(List<Race> race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return "Races{" + "race=" + race + '}';
	}
}