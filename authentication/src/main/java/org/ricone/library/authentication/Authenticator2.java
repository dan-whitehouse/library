package org.ricone.library.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ricone.library.authentication.oneroster.OneRosterDecodedToken;
import org.ricone.library.authentication.oneroster.OneRosterEndpoint;
import org.ricone.library.authentication.oneroster.OneRosterLoginResponse;
import org.ricone.library.authentication.xPress.XPressDecodedToken;
import org.ricone.library.authentication.xPress.XPressEndpoint;
import org.ricone.library.authentication.xPress.XPressLoginResponse;
import org.springframework.http.*;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class Authenticator2 {
	private RestTemplate restTemplate;
	private String url;
	private String username;
	private String password;
	private String provider;
	private API api;
	private List<Endpoint> endpoints = new ArrayList<>();

	public static Builder builder() {
		return new Builder();
	}

	private Authenticator2() {
		restTemplate = new RestTemplate();
	}

	private static class Singleton {
		private static final Authenticator2 INSTANCE = new Authenticator2();
	}

	public static Authenticator2 getInstance() {
		return Authenticator2.Singleton.INSTANCE;
	}


	public static class Builder {
		private Authenticator2 instance = getInstance();

		public Builder url(String url) {
			instance.url = url;
			return this;
		}

		public Builder credentials(String username, String password) {
			instance.username = username;
			instance.password = password;
			return this;
		}

		public Builder provider(String provider) {
			instance.provider = provider;
			return this;
		}

		public Builder api(API api) {
			instance.api = api;
			return this;
		}

		public Authenticator2 authenticate()  {
			instance.authenticate(instance.url, instance.username, instance.password, instance.api);
			return instance;
		}
	}

	private void authenticate(String url, String username, String password, API api) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.api = api;

		if(this.api.equals(API.xPress)) {
			authenticateXPress(this.url, this.username, this.password);
		}
		else if(this.api.equals(API.OneRoster)) {
			authenticateOneRoster(this.url, this.username, this.password);
		}
		else {
			if(this.url.endsWith("oauth/login")) {
				authenticateOneRoster(this.url, this.username, this.password);
			}
			else if (this.url.endsWith("/login")) {
				authenticateXPress(this.url, this.username, this.password);
			}
		}
	}

	private void authenticateXPress(String url, String username, String password) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("username", username);
		body.add("password", password);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

		try {
			ResponseEntity<XPressLoginResponse> login = restTemplate.exchange(url, HttpMethod.POST, entity, XPressLoginResponse.class);
			if(login.hasBody()) {
				for(XPressEndpoint endpoint : login.getBody().getEndpoints()) {
					DecodedToken decodedToken = TokenDecoder.getDecodedToken(TokenDecoder.decodeToken(endpoint.getToken(), XPressDecodedToken.class), endpoint.getToken());
					endpoints.add(new Endpoint(endpoint, decodedToken));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void authenticateOneRoster(String url, String username, String password) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "client_credentials");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));

		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
		try {
			ResponseEntity<OneRosterLoginResponse> login = restTemplate.exchange(url, HttpMethod.POST, entity, OneRosterLoginResponse.class);
			if(login.hasBody()) {
				for(OneRosterEndpoint endpoint : login.getBody().getEndpoint()) {
					DecodedToken decodedToken = TokenDecoder.getDecodedToken(TokenDecoder.decodeToken(endpoint.getAccessToken(), OneRosterDecodedToken.class), endpoint.getAccessToken());
					endpoints.add(new Endpoint(endpoint, decodedToken));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * A list of all endpoints an application is associated with and it's details.
	 *
	 * @return a list of type Endpoint.
	 */
	public List<Endpoint> getEndpoints() {
		if(endpoints != null) {
			if(provider != null) {
				return endpoints.stream().filter(endpoint -> endpoint.getProviderId().equalsIgnoreCase(provider)).collect(Collectors.toList());
			}
			return endpoints;
		}
		return new ArrayList<>();
	}

	/**
	 * Details of a specific endpoint by providerId.
	 *
	 * @return an Endpoint by specified providerId if the providerId was provided during the building process.
	 */
	public Optional<Endpoint> getEndpoint() {
		if(endpoints != null) {
			return endpoints.stream().filter(endpoint -> endpoint.getProviderId().equalsIgnoreCase(provider)).findFirst();
		}
		return Optional.empty();
	}

	/**
	 * Details of a specific endpoint by providerId.
	 *
	 * @param providerId the providerId to be returned.
	 * @return an Endpoint by specified providerId.
	 */
	public Optional<Endpoint> getEndpoint(String providerId) {
		if(endpoints != null) {
			return endpoints.stream().filter(endpoint -> endpoint.getProviderId().equalsIgnoreCase(providerId)).findFirst();
		}
		return Optional.empty();
	}


	private boolean isTokenExpired(Endpoint endpoint) {
		try {
			LocalDateTime expiryDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(endpoint.getDecodedToken().getExp()), ZoneId.systemDefault());
			return expiryDate.isBefore(LocalDateTime.now());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean willTokenExpire(Endpoint endpoint) {
		try {
			LocalDateTime expiryDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(endpoint.getDecodedToken().getExp()), ZoneId.systemDefault()).minusMinutes(10);
			return LocalDateTime.now().isAfter(expiryDate);
			//return expiryDate.isBefore(LocalDateTime.now());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
