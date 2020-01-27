package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonRootName("courses")
@JacksonXmlRootElement(localName = "courses")
public class Courses implements Serializable {

    @JsonProperty("courses")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "course")
    private List<Course> courses;

    public Courses() {
    }

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    @JsonProperty("courses")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "course")
    public List<Course> getCourses() {
        return this.courses;
    }

    @JsonProperty("courses")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "course")
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courses courses = (Courses) o;
        return Objects.equals(this.courses, courses.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courses);
    }

    @Override
    public String toString() {
        return "Courses{" +
                "courses=" + courses +
                '}';
    }
}