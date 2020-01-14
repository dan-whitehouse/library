package org.ricone.library.client.oneroster.request;

import org.ricone.library.client.oneroster.request.order.Field;

import java.util.List;

public class FieldSelection {
	private List<Field> fields;

	public FieldSelection(List<Field> fields) {
		this.fields = fields;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
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
