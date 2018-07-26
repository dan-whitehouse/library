package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"longDescription", "type", "name", "permTemplate", "status", "profile_id", "tags", "licenseName", "includeExitedStudents", "providerSecret", "siteUrl", "vendor_id", "iconUrl", "sis_id", "public", "severityLevelDataAPI", "shortDescription", "title", "id", "password"})
public class App implements Serializable {

    @JsonProperty("longDescription")
    private Object longDescription;
    @JsonProperty("type")
    private Object type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("permTemplate")
    private String permTemplate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("profile_id")
    private String profileId;
    @JsonProperty("tags")
    private String tags;
    @JsonProperty("licenseName")
    private String licenseName;
    @JsonProperty("includeExitedStudents")
    private Boolean includeExitedStudents;
    @JsonProperty("providerSecret")
    private String providerSecret;
    @JsonProperty("siteUrl")
    private String siteUrl;
    @JsonProperty("vendor_id")
    private String vendorId;
    @JsonProperty("iconUrl")
    private String iconUrl;
    @JsonProperty("sis_id")
    private String sisId;
    @JsonProperty("public")
    private Object _public;
    @JsonProperty("severityLevelDataAPI")
    private String severityLevelDataAPI;
    @JsonProperty("shortDescription")
    private Object shortDescription;
    @JsonProperty("title")
    private String title;
    @JsonProperty("id")
    private String id;
    @JsonProperty("password")
    private String password;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3224224729416228935L;

    /**
     * No args constructor for use in serialization
     */
    public App() {
    }

    /**
     * @param tags
     * @param _public
     * @param status
     * @param severityLevelDataAPI
     * @param iconUrl
     * @param sisId
     * @param includeExitedStudents
     * @param licenseName
     * @param permTemplate
     * @param type
     * @param vendorId
     * @param password
     * @param id
     * @param siteUrl
     * @param title
     * @param shortDescription
     * @param profileId
     * @param name
     * @param longDescription
     * @param providerSecret
     */
    public App(Object longDescription, Object type, String name, String permTemplate, String status, String profileId, String tags, String licenseName, Boolean includeExitedStudents, String providerSecret, String siteUrl, String vendorId, String iconUrl, String sisId, Object _public, String severityLevelDataAPI, Object shortDescription, String title, String id, String password) {
        super();
        this.longDescription = longDescription;
        this.type = type;
        this.name = name;
        this.permTemplate = permTemplate;
        this.status = status;
        this.profileId = profileId;
        this.tags = tags;
        this.licenseName = licenseName;
        this.includeExitedStudents = includeExitedStudents;
        this.providerSecret = providerSecret;
        this.siteUrl = siteUrl;
        this.vendorId = vendorId;
        this.iconUrl = iconUrl;
        this.sisId = sisId;
        this._public = _public;
        this.severityLevelDataAPI = severityLevelDataAPI;
        this.shortDescription = shortDescription;
        this.title = title;
        this.id = id;
        this.password = password;
    }

    @JsonProperty("longDescription")
    public Object getLongDescription() {
        return longDescription;
    }

    @JsonProperty("longDescription")
    public void setLongDescription(Object longDescription) {
        this.longDescription = longDescription;
    }

    public App withLongDescription(Object longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    @JsonProperty("type")
    public Object getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Object type) {
        this.type = type;
    }

    public App withType(Object type) {
        this.type = type;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public App withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("permTemplate")
    public String getPermTemplate() {
        return permTemplate;
    }

    @JsonProperty("permTemplate")
    public void setPermTemplate(String permTemplate) {
        this.permTemplate = permTemplate;
    }

    public App withPermTemplate(String permTemplate) {
        this.permTemplate = permTemplate;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public App withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("profile_id")
    public String getProfileId() {
        return profileId;
    }

    @JsonProperty("profile_id")
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public App withProfileId(String profileId) {
        this.profileId = profileId;
        return this;
    }

    @JsonProperty("tags")
    public String getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(String tags) {
        this.tags = tags;
    }

    public App withTags(String tags) {
        this.tags = tags;
        return this;
    }

    @JsonProperty("licenseName")
    public String getLicenseName() {
        return licenseName;
    }

    @JsonProperty("licenseName")
    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public App withLicenseName(String licenseName) {
        this.licenseName = licenseName;
        return this;
    }

    @JsonProperty("includeExitedStudents")
    public Boolean getIncludeExitedStudents() {
        return includeExitedStudents;
    }

    @JsonProperty("includeExitedStudents")
    public void setIncludeExitedStudents(Boolean includeExitedStudents) {
        this.includeExitedStudents = includeExitedStudents;
    }

    public App withIncludeExitedStudents(Boolean includeExitedStudents) {
        this.includeExitedStudents = includeExitedStudents;
        return this;
    }

    @JsonProperty("providerSecret")
    public String getProviderSecret() {
        return providerSecret;
    }

    @JsonProperty("providerSecret")
    public void setProviderSecret(String providerSecret) {
        this.providerSecret = providerSecret;
    }

    public App withProviderSecret(String providerSecret) {
        this.providerSecret = providerSecret;
        return this;
    }

    @JsonProperty("siteUrl")
    public String getSiteUrl() {
        return siteUrl;
    }

    @JsonProperty("siteUrl")
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public App withSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
        return this;
    }

    @JsonProperty("vendor_id")
    public String getVendorId() {
        return vendorId;
    }

    @JsonProperty("vendor_id")
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public App withVendorId(String vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    @JsonProperty("iconUrl")
    public String getIconUrl() {
        return iconUrl;
    }

    @JsonProperty("iconUrl")
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public App withIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    @JsonProperty("sis_id")
    public String getSisId() {
        return sisId;
    }

    @JsonProperty("sis_id")
    public void setSisId(String sisId) {
        this.sisId = sisId;
    }

    public App withSisId(String sisId) {
        this.sisId = sisId;
        return this;
    }

    @JsonProperty("public")
    public Object getPublic() {
        return _public;
    }

    @JsonProperty("public")
    public void setPublic(Object _public) {
        this._public = _public;
    }

    public App withPublic(Object _public) {
        this._public = _public;
        return this;
    }

    @JsonProperty("severityLevelDataAPI")
    public String getSeverityLevelDataAPI() {
        return severityLevelDataAPI;
    }

    @JsonProperty("severityLevelDataAPI")
    public void setSeverityLevelDataAPI(String severityLevelDataAPI) {
        this.severityLevelDataAPI = severityLevelDataAPI;
    }

    public App withSeverityLevelDataAPI(String severityLevelDataAPI) {
        this.severityLevelDataAPI = severityLevelDataAPI;
        return this;
    }

    @JsonProperty("shortDescription")
    public Object getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("shortDescription")
    public void setShortDescription(Object shortDescription) {
        this.shortDescription = shortDescription;
    }

    public App withShortDescription(Object shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public App withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public App withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public App withPassword(String password) {
        this.password = password;
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

    public App withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}