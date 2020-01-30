package org.ricone.library.client.oneroster.request;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

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
