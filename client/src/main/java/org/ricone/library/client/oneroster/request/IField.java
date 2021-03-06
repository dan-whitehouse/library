package org.ricone.library.client.oneroster.request;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public interface IField {
	String getValue();
	FieldType getType();
	FieldClassType getFieldClassType();
}
