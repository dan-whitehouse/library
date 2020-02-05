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

@JsonRootName("demographics")
@JacksonXmlRootElement(localName = "demographics")
public class Demographics extends ResponseModel implements Serializable {

    @JsonProperty("demographics")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "demographic")
    private List<Demographic> demographics;

    public Demographics() {
        demographics = new ArrayList<>();
    }

    public Demographics(List<Demographic> demographics) {
        this.demographics = demographics;
    }

    @JsonProperty("demographics")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "demographic")
    public List<Demographic> getDemographics() {
        return this.demographics;
    }

    @JsonProperty("demographics")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "demographic")
    public void setDemographics(List<Demographic> demographics) {
        this.demographics = demographics;
    }

    @Override
    public boolean isEmpty() {
        return demographics.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demographics demographics = (Demographics) o;
        return Objects.equals(this.demographics, demographics.demographics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(demographics);
    }

    @Override
    public String toString() {
        return "Demographics{" +
                "demographics=" + demographics +
                '}';
    }
}