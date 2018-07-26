package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "ttl", "created", "userId", "roles"})
public class Login implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("ttl")
    private Integer ttl;
    @JsonProperty("created")
    private String created;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("roles")
    private List<String> roles = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7651784989029361498L;

    /**
     * No args constructor for use in serialization
     */
    public Login() {
    }

    /**
     * @param id
     * @param created
     * @param roles
     * @param userId
     * @param ttl
     */
    public Login(String id, Integer ttl, String created, Integer userId, List<String> roles) {
        super();
        this.id = id;
        this.ttl = ttl;
        this.created = created;
        this.userId = userId;
        this.roles = roles;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Login withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("ttl")
    public Integer getTtl() {
        return ttl;
    }

    @JsonProperty("ttl")
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Login withTtl(Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    public Login withCreated(String created) {
        this.created = created;
        return this;
    }

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Login withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Login withRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Login withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}