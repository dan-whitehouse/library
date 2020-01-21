package org.ricone.library.client.oneroster.request;

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
