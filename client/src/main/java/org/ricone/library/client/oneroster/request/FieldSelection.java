package org.ricone.library.client.oneroster.request;

import java.util.List;

public class FieldSelection {
	private List<IField> fields;

	public FieldSelection(List<IField> fields) {
		this.fields = fields;
	}

	public List<IField> getFields() {
		return fields;
	}

	public void setFields(List<IField> fields) {
		this.fields = fields;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof FieldSelection)) return false;

		FieldSelection that = (FieldSelection) o;

		return fields != null ? fields.equals(that.fields) : that.fields == null;
	}

	@Override
	public int hashCode() {
		return fields != null ? fields.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "FieldSelection{" + "fields=" + fields + '}';
	}
}
