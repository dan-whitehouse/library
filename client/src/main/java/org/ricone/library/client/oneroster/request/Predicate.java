package org.ricone.library.client.oneroster.request;

public enum Predicate {
    Equals("="),
    NotEquals("!="),
    GreaterThan(">"),
    GreaterThanOrEqual(">="),
    LessThan("<"),
    LessThanOrEqual("<="),
    Contains("~");

    private final String value;
    Predicate(String value) {this.value = value;}

    public String getValue() {
        return value;
    }
}
