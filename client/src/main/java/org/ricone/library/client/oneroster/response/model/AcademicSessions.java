package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonRootName("academicSessions")
@JacksonXmlRootElement(localName = "academicSessions")
public class AcademicSessions implements Serializable {

    @JsonProperty("academicSessions")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "academicSession")
    private List<AcademicSession> academicSessions;

    public AcademicSessions() {
    }

    public AcademicSessions(List<AcademicSession> academicSessions) {
        this.academicSessions = academicSessions;
    }

    @JsonProperty("academicSessions")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "academicSession")
    public List<AcademicSession> getAcademicSessions() {
        return this.academicSessions;
    }

    @JsonProperty("academicSessions")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "academicSession")
    public void setAcademicSessions(List<AcademicSession> academicSessions) {
        this.academicSessions = academicSessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicSessions academicSessions = (AcademicSessions) o;
        return Objects.equals(this.academicSessions, academicSessions.academicSessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(academicSessions);
    }

    @Override
    public String toString() {
        return "Orgs{" +
                "orgs=" + academicSessions +
                '}';
    }
}