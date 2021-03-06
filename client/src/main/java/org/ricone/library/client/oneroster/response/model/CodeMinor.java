package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CodeMinor {
	forbidden("forbidden"),
	full_success("full success"),
	unknown_object("unknown object"),
	invalid_data("invalid data"),
	unauthorized("unauthorized"),
	invalid_sort_field("invalid_sort_field"),
	invalid_blank_selection_field("invalid_ blank_selection _field"),
	invalid_filter_field("invalid_filter_field"),
	invalid_selection_field("invalid_selection_field"),
	internal_server_error("internal_server_error"), //Custom
	unsupported_feature("unsupported_feature"); //Custom

	private final String label;

	CodeMinor(String label) {
		this.label = label;
	}

	@JsonValue
	public String getLabel() {
		return label;
	}
}
