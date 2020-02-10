package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.ricone.library.client.core.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonRootName("enrollments")
@JacksonXmlRootElement(localName = "enrollments")
public class Enrollments extends Model implements Serializable {

    @JsonProperty("enrollments")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "enrollment")
    private List<Enrollment> enrollments;

    public Enrollments() {
        enrollments = new ArrayList<>();
    }

    public Enrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @JsonProperty("enrollments")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "enrollment")
    public List<Enrollment> getEnrollments() {
        return this.enrollments;
    }

    @JsonProperty("enrollments")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "enrollment")
    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public boolean isEmpty() {
        return enrollments.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollments enrollments = (Enrollments) o;
        return Objects.equals(this.enrollments, enrollments.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrollments);
    }
}