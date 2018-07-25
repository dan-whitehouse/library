package org.ricone.library.config.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"href", "token"})
public class Endpoint implements Serializable {
	@JsonProperty("href")
	private String href;
	@JsonProperty("token")
	private String token;
	private final static long serialVersionUID = -3410519710831695918L;

	public Endpoint() {
	}

	public Endpoint(String href, String token) {
		super();
		this.href = href;
		this.token = token;
	}

	@JsonProperty("href")
	public String getHref() {
		return href;
	}

	@JsonProperty("href")
	public void setHref(String href) {
		this.href = href;
	}

	@JsonProperty("token")
	public String getToken() {
		return ConfigService.getInstance().getToken();
	}

	@JsonProperty("token")
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Endpoint endpoint = (Endpoint) o;

		if(href != null ? !href.equals(endpoint.href) : endpoint.href != null) return false;
		return token != null ? token.equals(endpoint.token) : endpoint.token == null;
	}

	@Override
	public int hashCode() {
		int result = href != null ? href.hashCode() : 0;
		result = 31 * result + (token != null ? token.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Endpoint{" + "href='" + href + '\'' + ", token='" + token + '\'' + '}';
	}
}