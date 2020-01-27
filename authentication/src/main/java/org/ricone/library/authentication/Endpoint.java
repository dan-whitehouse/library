package org.ricone.library.authentication;

import org.ricone.library.authentication.oneroster.OneRosterEndpoint;
import org.ricone.library.authentication.xPress.XPressEndpoint;

import java.io.Serializable;

public class Endpoint implements Serializable {
	private final static long serialVersionUID = -3410519710831695918L;
	private String name;
	private String href;
	private String providerId;
	private DecodedToken decodedToken;

	public Endpoint(XPressEndpoint endpoint, DecodedToken decodedToken) {
		super();
		this.name = endpoint.getName();
		this.href = endpoint.getHref();
		this.providerId = endpoint.getProviderId();
		this.decodedToken = decodedToken;
	}

	public Endpoint(OneRosterEndpoint endpoint, DecodedToken decodedToken) {
		super();
		this.name = endpoint.getName();
		this.href = endpoint.getHref();
		this.providerId = endpoint.getProviderId();
		this.decodedToken = decodedToken;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public DecodedToken getDecodedToken() {
		return decodedToken;
	}

	public void setDecodedToken(DecodedToken decodedToken) {
		this.decodedToken = decodedToken;
	}
}