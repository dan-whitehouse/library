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

@JsonRootName("orgs")
@JacksonXmlRootElement(localName = "orgs")
public class Orgs extends ResponseModel implements Serializable {
    @JsonProperty("orgs")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "org")
    private List<Org> orgs;

    public Orgs() {
        orgs = new ArrayList<>();
    }

    public Orgs(List<Org> orgs) {
        this.orgs = orgs;
    }

    @JsonProperty("orgs")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "org")
    public List<Org> getOrgs() {
        return this.orgs;
    }

    @JsonProperty("orgs")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "org")
    public void setOrgs(List<Org> orgs) {
        this.orgs = orgs;
    }

    @Override
    public boolean isEmpty() {
        return orgs.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orgs orgs = (Orgs) o;
        return Objects.equals(this.orgs, orgs.orgs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgs);
    }

    @Override
    public String toString() {
        return "Orgs{" +
                "orgs=" + orgs +
                '}';
    }
}