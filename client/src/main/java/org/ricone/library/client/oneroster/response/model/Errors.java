package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.List;

@JsonRootName("errors")
@JacksonXmlRootElement(localName = "errors")
public class Errors implements Serializable {

    @JsonProperty("errors")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "error")
    private List<Error> errors;

    public Errors() {
    }

    public Errors(List<Error> errors) {
        this.errors = errors;
    }

    @JsonProperty("errors")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "error")
    public List<Error> getErrors() {
        return this.errors;
    }

    @JsonProperty("errors")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "error")
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "Errors{" +
                "errors=" + errors +
                '}';
    }
}