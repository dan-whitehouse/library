package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "provider_id", "value", "field"})
public class ProviderKV implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("provider_id")
    private String providerId;
    @JsonProperty("value")
    private String value;
    @JsonProperty("field")
    private String field;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1304712771702255224L;

    /**
     * No args constructor for use in serialization
     */
    public ProviderKV() {
    }

    /**
     * @param field
     * @param id
     * @param value
     * @param providerId
     */
    public ProviderKV(Integer id, String providerId, String value, String field) {
        super();
        this.id = id;
        this.providerId = providerId;
        this.value = value;
        this.field = field;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("provider_id")
    public String getProviderId() {
        return providerId;
    }

    @JsonProperty("provider_id")
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("field")
    public String getField() {
        return field;
    }

    @JsonProperty("field")
    public void setField(String field) {
        this.field = field;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}