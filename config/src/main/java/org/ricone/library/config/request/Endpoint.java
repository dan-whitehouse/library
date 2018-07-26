package org.ricone.library.config.request;

import java.time.LocalDateTime;
import java.util.Objects;

public class Endpoint {
	private String href;
	private String token;
	private LocalDateTime created;
	private LocalDateTime expires;

	public Endpoint() {
	}

	public Endpoint(String href, String token, LocalDateTime created, LocalDateTime expires) {
		this.href = href;
		this.token = token;
		this.created = created;
		this.expires = expires;
	}

	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}

	public String getToken() {
		return ConfigService.getInstance().getToken();
	}
	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getExpires() {
		return expires;
	}

	public void setExpires(LocalDateTime expires) {
		this.expires = expires;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Endpoint endpoint = (Endpoint) o;
		return Objects.equals(href, endpoint.href) && Objects.equals(token, endpoint.token) && Objects.equals(created, endpoint.created) && Objects.equals(expires, endpoint.expires);
	}

	@Override
	public int hashCode() {
		return Objects.hash(href, token, created, expires);
	}

	@Override
	public String toString() {
		return "Endpoint{" + "href='" + href + '\'' + ", token='" + token + '\'' + ", created=" + created + ", expires=" + expires + '}';
	}
}