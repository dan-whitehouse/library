package org.ricone.library.client.oneroster.request;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public enum LogicalOperation {
    NONE(""),
    AND(" AND "),
    OR(" OR ");

    private final String value;
    LogicalOperation(String value) {this.value = value;}

    public String getValue() {
        return value;
    }
}
