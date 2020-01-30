package org.ricone.library.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "user_name", "token", "endpoint"})
class Login implements Serializable {
	@JsonProperty("id")
	private String id;
	@JsonProperty("user_name")
	private String userName;
	@JsonProperty("token")
	private String token;
	@JsonProperty("endpoint")
	private List<Endpoint> endpoints = new ArrayList<>();
	private final static long serialVersionUID = 1804651811881810864L;

	public Login() { }

	public Login(String id, String userName, String token, List<Endpoint> endpoints) {
		super();
		this.id = id;
		this.userName = userName;
		this.token = token;
		this.endpoints = endpoints;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("user_name")
	public String getUserName() {
		return userName;
	}

	@JsonProperty("user_name")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonProperty("token")
	public String getToken() {
		return token;
	}

	@JsonProperty("token")
	public void setToken(String token) {
		this.token = token;
	}

	@JsonProperty("endpoint")
	public List<Endpoint> getEndpoints() {
		return endpoints;
	}

	@JsonProperty("endpoint")
	public void setEndpoints(List<Endpoint> endpoints) {
		this.endpoints = endpoints;
	}

	@Override
	public String toString() {
		return "UserInfo{" + "id='" + id + '\'' + ", userName='" + userName + '\'' + ", token='" + token + '\'' + ", endpoints=" + endpoints + '}';
	}
}