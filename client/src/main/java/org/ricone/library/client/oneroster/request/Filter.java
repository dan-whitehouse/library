package org.ricone.library.client.oneroster.request;

import org.ricone.library.client.oneroster.response.model.Type;

import java.util.Objects;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public class Filter {
    private IField field;
    private Predicate predicate;
    private String value;

    public Filter(IField field, Predicate predicate, String value) {
        this.field = field;
        this.predicate = predicate;
        this.value = value;
    }

    public Filter(IField field, Predicate predicate, Type type) {
        this.field = field;
        this.predicate = predicate;
        this.value = type.getValue();
    }

    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return Objects.equals(field, filter.field) &&
                predicate == filter.predicate &&
                Objects.equals(value, filter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, predicate, value);
    }

    @Override
    public String toString() {
        return "Filter{" +
                "field=" + field +
                ", predicate=" + predicate +
                ", value='" + value + '\'' +
                '}';
    }
}
