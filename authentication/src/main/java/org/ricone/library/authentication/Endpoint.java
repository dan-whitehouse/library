package org.ricone.library.authentication;

import org.ricone.library.authentication.oneroster.OneRosterEndpoint;
import org.ricone.library.authentication.xPress.XPressEndpoint;

import java.io.Serializable;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public class Endpoint implements Serializable {
	private final static long serialVersionUID = -3410519710831695918L;
	private String name;
	private String href;
	private String providerId;
	private DecodedToken decodedToken;

	Endpoint(XPressEndpoint endpoint, DecodedToken decodedToken) {
		super();
		this.name = endpoint.getName();
		this.href = endpoint.getHref();
		this.providerId = endpoint.getProviderId();
		this.decodedToken = decodedToken;
	}

	Endpoint(OneRosterEndpoint endpoint, DecodedToken decodedToken) {
		super();
		this.name = endpoint.getName();
		this.href = endpoint.getHref();
		this.providerId = endpoint.getProviderId();
		this.decodedToken = decodedToken;
	}

	/**
	 * The getName method is used to return the full name of the endpoint.
	 * @return the name of the endpoint.
	 */
	public String getName() {
		return name;
	}

	/**
	 * The getHref method is used to return the url of the endpoint.
	 * @return the href url of the endpoint.
	 */
	public String getHref() {
		return href;
	}

	/**
	 * The getProviderId method is used to return the id of the endpoint.
	 * @return the provider id of the endpoint.
	 */
	public String getProviderId() {
		return providerId;
	}

	/**
	 * The getDecodedToken method is used to return the id of the endpoint.
	 * @return the decoded token object, which contains information about the auth token.
	 */
	public DecodedToken getDecodedToken() {
		return decodedToken;
	}

	void setDecodedToken(DecodedToken decodedToken) {
		this.decodedToken = decodedToken;
	}
}