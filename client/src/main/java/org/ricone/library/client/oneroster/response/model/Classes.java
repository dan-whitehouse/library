package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@JsonRootName("classes")
@JacksonXmlRootElement(localName = "classes")
public class Classes extends ResponseModel implements Serializable {

    @JsonProperty("classes")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "class")
    private List<Class> classes;

    public Classes() {
        classes = new ArrayList<>();
    }

    public Classes(List<Class> classes) {
        this.classes = classes;
    }

    @JsonProperty("classes")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "class")
    public List<Class> getClasses() {
        return this.classes;
    }

    @JsonProperty("classes")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "class")
    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public boolean isEmpty() {
        return classes.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes classes = (Classes) o;
        return Objects.equals(this.classes, classes.classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classes);
    }

    @Override
    public String toString() {
        return "Orgs{" +
                "orgs=" + classes +
                '}';
    }
}