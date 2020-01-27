package org.ricone.library.authentication;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DecodedToken implements Serializable {
	private final static long serialVersionUID = 9033040468688496163L;
	private String appId;
	private String providerId;
	private String href;
	private Integer iat;
	private Integer exp;
	private String iss;
	private String token;

	public DecodedToken(String appId, String providerId, String href, Integer iat, Integer exp, String iss, String token) {
		this.appId = appId;
		this.providerId = providerId;
		this.href = href;
		this.iat = iat;
		this.exp = exp;
		this.iss = iss;
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getIat() {
		return iat;
	}

	public void setIat(Integer iat) {
		this.iat = iat;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
