package org.ricone.library.authentication;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public class DecodedToken implements Serializable {
	private final static long serialVersionUID = 9033040468688496163L;
	private String appId;
	private String providerId;
	private String href;
	private Integer iat;
	private Integer exp;
	private String iss;
	private String token;

	DecodedToken(String appId, String providerId, String href, Integer iat, Integer exp, String iss, String token) {
		this.appId = appId;
		this.providerId = providerId;
		this.href = href;
		this.iat = iat;
		this.exp = exp;
		this.iss = iss;
		this.token = token;
	}

	/**
	 * The getAppId method is used to return the name of the application authenticated.
	 * @return the applications id.
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * The getProviderId method is used to return the id of the endpoint authenticated.
	 * @return the providers id.
	 */
	public String getProviderId() {
		return providerId;
	}

	/**
	 * The getHref method is used to return the url of the endpoint authenticated.
	 * @return the providers url.
	 */
	public String getHref() {
		return href;
	}

	/**
	 * The getIat method is used to return the issued at time of the token.
	 * @return the tokens issued at time.
	 */
	public Integer getIat() {
		return iat;
	}

	/**
	 * The getExp method is used to return the expiry time of the token.
	 * @return the tokens expiry.
	 */
	public Integer getExp() {
		return exp;
	}

	/**
	 * The getIss method is used to identify the url that issued the token.
	 * @return the issuer of the token.
	 */
	public String getIss() {
		return iss;
	}

	/**
	 * The getToken method is used to return the JWT token provided by the authorization service.
	 * @return the token.
	 */
	public String getToken() {
		//Checks if the token is expired, or will within 10 minutes. If so, we reauthenticate, and update the endpoints.
		if(isTokenExpired() || willTokenExpire()) {
			Authenticator.getInstance().reAuthenticate();
		}
		return token;
	}

	/* Token Expiry Checks */
	//todo: set these to private, as they don't need to be used by anyone other then the person testing their function.
	public boolean isTokenExpired() {
		try {
			LocalDateTime expiryDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(exp), ZoneId.systemDefault());
			return expiryDate.isBefore(LocalDateTime.now());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean willTokenExpire() {
		try {
			LocalDateTime expiryDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(exp), ZoneId.systemDefault()).minusMinutes(10);
			return LocalDateTime.now().isAfter(expiryDate);
			//return expiryDate.isBefore(LocalDateTime.now());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
