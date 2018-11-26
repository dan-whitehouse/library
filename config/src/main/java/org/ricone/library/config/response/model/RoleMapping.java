package org.ricone.library.config.response.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "principalType", "principalId", "roleId"})
public class RoleMapping implements Serializable {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("principalType")
	private String principalType;
	@JsonProperty("principalId")
	private String principalId;
	@JsonProperty("roleId")
	private Integer roleId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -8989137437827759103L;

	/**
	 * No args constructor for use in serialization
	 */
	public RoleMapping() {
	}

	/**
	 * @param id
	 * @param principalId
	 * @param principalType
	 * @param roleId
	 */
	public RoleMapping(Integer id, String principalType, String principalId, Integer roleId) {
		super();
		this.id = id;
		this.principalType = principalType;
		this.principalId = principalId;
		this.roleId = roleId;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("principalType")
	public String getPrincipalType() {
		return principalType;
	}

	@JsonProperty("principalType")
	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	@JsonProperty("principalId")
	public String getPrincipalId() {
		return principalId;
	}

	@JsonProperty("principalId")
	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	@JsonProperty("roleId")
	public Integer getRoleId() {
		return roleId;
	}

	@JsonProperty("roleId")
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
		return "RoleMapping{" + "id=" + id + ", principalType='" + principalType + '\'' + ", principalId='" + principalId + '\'' + ", roleId=" + roleId + ", additionalProperties=" + additionalProperties + '}';
	}
}