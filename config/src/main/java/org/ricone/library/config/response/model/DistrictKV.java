package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "district_id", "value", "field"})
public class DistrictKV implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("district_id")
    private String districtId;
    @JsonProperty("value")
    private String value;
    @JsonProperty("field")
    private String field;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7563589737211801882L;

    /**
     * No args constructor for use in serialization
     */
    public DistrictKV() {
    }

    /**
     * @param field
     * @param id
     * @param districtId
     * @param value
     */
    public DistrictKV(Integer id, String districtId, String value, String field) {
        super();
        this.id = id;
        this.districtId = districtId;
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

    @JsonProperty("district_id")
    public String getDistrictId() {
        return districtId;
    }

    @JsonProperty("district_id")
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
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

    @Override
    public String toString() {
        return "DistrictKV{" + "id=" + id + ", districtId='" + districtId + '\'' + ", value='" + value + '\'' + ", field='" + field + '\'' + ", additionalProperties=" + additionalProperties + '}';
    }
}