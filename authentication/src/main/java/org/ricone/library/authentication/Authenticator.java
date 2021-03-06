package org.ricone.library.authentication;

import org.ricone.library.authentication.oneroster.OneRosterDecodedToken;
import org.ricone.library.authentication.oneroster.OneRosterEndpoint;
import org.ricone.library.authentication.oneroster.OneRosterLoginResponse;
import org.ricone.library.authentication.xPress.XPressDecodedToken;
import org.ricone.library.authentication.xPress.XPressEndpoint;
import org.ricone.library.authentication.xPress.XPressLoginResponse;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Authenticator class is responsible for authenticating a requester to one of RIC One's authentication servers.
 * It logs the requester in and saves a jwt token for each approved endpoint. The jwt tokens are used for gaining access
 * to either the xPress or One Roster APIs.
 *
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-02-03
 */
public class Authenticator {
	private RestTemplate restTemplate;
	private String url;
	private String username;
	private String password;
	private String provider;
	private API api;
	private List<Endpoint> endpoints = new ArrayList<>();

	private Authenticator() {
		restTemplate = new RestTemplate();
	}

	private static class Singleton {
		private static final Authenticator INSTANCE = new Authenticator();
	}

	/**
	 * The getInstance method is used for the single instance of the Authenticator class.
	 * @return the instance of the Authenticator.
	 */
	public static Authenticator getInstance() {
		return Authenticator.Singleton.INSTANCE;
	}

	/**
	 * The builder method is used for building an instance of the Authenticator class.
	 */
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Authenticator instance = getInstance();

		public Builder url(String url) {
			instance.url = url;
			return this;
		}

		/**
		 * The credentials method is used pass the username and password into the Builder.
		 * @param username the username of the requester.
		 * @param password the password of the requester.
		 */
		public Builder credentials(String username, String password) {
			instance.username = username;
			instance.password = password;
			return this;
		}

		/**
		 * The provider method is [optional], and used to pass the providerId into the Builder.
		 * If this option is used, the number of endpoints returned will be one.
		 * @param provider the provider id of the requested endpoint.
		 */
		public Builder provider(String provider) {
			instance.provider = provider;
			return this;
		}

		/**
		 * The api method is [optional], and used help the authenticator determine the API being requested.
		 * If this option is used incorrectly, authentication will fail.
		 * Example: A XPress url and an API choice of OneRoster
		 * @param api the type of API being requested against.
		 */
		public Builder api(API api) {
			instance.api = api;
			return this;
		}

		/**
		 * The authenticate method is required to complete the builders process.
		 * It then performs the internal authentication.
		 */
		public Authenticator authenticate()  {
			instance.authenticate(instance.url, instance.username, instance.password, instance.api);
			return instance;
		}
	}

	/**
	 * The authenticate method is used to call the correct authentication method.
	 * @param url the address of the authentication server.
	 * @param username the username of the requester.
	 * @param password the password of the requester.
	 * @param api an enumerator, which helps figure out the url.
	 */
	private void authenticate(String url, String username, String password, API api) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.api = api;

		//In the event the authenticate method is called again, lets clear any endpoints that already exist.
		this.endpoints.clear();

		//How are we authenticating...
		if(this.api.equals(API.OneRoster)) {
			this.endpoints.addAll(authenticateOneRoster(this.url, this.username, this.password));
		}
		else if(this.api.equals(API.xPress)) {
			this.endpoints.addAll(authenticateXPress(this.url, this.username, this.password));
		}
		else {
			if(this.url.endsWith("oauth/login")) {
				this.api = API.OneRoster;
				this.endpoints.addAll(authenticateOneRoster(this.url, this.username, this.password));
			}
			if (this.url.endsWith("/login")) {
				this.api = API.xPress;
				this.endpoints.addAll(authenticateXPress(this.url, this.username, this.password));
			}
		}
	}

	/**
	 * The reAuthenticate method is used to reauthenticate the requesters endpoints when the token will expire in the
	 * near future or the token as already expired.
	 */
	void reAuthenticate() {
		List<Endpoint> newEndpoints = new ArrayList<>();

		if(this.api.equals(API.xPress)) {
			newEndpoints.addAll(authenticateXPress(this.url, this.username, this.password));
		}
		else if(this.api.equals(API.OneRoster)) {
			newEndpoints.addAll(authenticateOneRoster(this.url, this.username, this.password));
		}

		for(Endpoint endpoint : this.endpoints) {
			Optional<Endpoint> newEndpoint = newEndpoints.stream().filter(e -> e.getProviderId().equalsIgnoreCase(endpoint.getProviderId())).findFirst();
			newEndpoint.ifPresent(value -> endpoint.setDecodedToken(value.getDecodedToken()));
		}
	}

	/**
	 * The authenticateXPress method is used to authenticate the requester for xPress endpoints.
	 * @param url the address of the authentication server.
	 * @param username the username of the requester.
	 * @param password the password of the requester.
	 * @return a list of endpoints returned from the authentication server, which are approved for the requester.
	 */
	private List<Endpoint> authenticateXPress(String url, String username, String password) {
		List<Endpoint> endpoints = new ArrayList<>();
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
		return endpoints;
	}

	/**
	 * The authenticateOneRoster method is used to authenticate the requester for One Roster endpoints.
	 * @param url the address of the authentication server.
	 * @param username the username of the requester.
	 * @param password the password of the requester.
	 * @return a list of endpoints returned from the authentication server, which are approved for the requester.
	 */
	private List<Endpoint> authenticateOneRoster(String url, String username, String password) {
		List<Endpoint> endpoints = new ArrayList<>();

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
		return endpoints;
	}

	/**
	 * The getEndpoints method is used to return a list of all the endpoints an application is associated to.
	 * @return a list of endpoints.
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
	 * The getEndpoint method is used to return a single endpoint, based on the value passed into the builder().providerId() method.
	 * @return an optional endpoint.
	 */
	public Optional<Endpoint> getEndpoint() {
		if(endpoints != null) {
			return endpoints.stream().filter(endpoint -> endpoint.getProviderId().equalsIgnoreCase(provider)).findFirst();
		}
		return Optional.empty();
	}

	/**
	 * The getEndpoint method is used to return a single endpoint, specifically the one requested by the providerId parameter.
	 * @param providerId the provider to be returned.
	 * @return an optional Endpoint by specified providerId.
	 */
	public Optional<Endpoint> getEndpoint(String providerId) {
		if(endpoints != null) {
			return endpoints.stream().filter(endpoint -> endpoint.getProviderId().equalsIgnoreCase(providerId)).findFirst();
		}
		return Optional.empty();
	}

	/**
	 * The isTokenExpired method is used to check if the endpoints token has expired.
	 * @param endpoint the endpoint being looked at.
	 * @return a boolean indicating if the token has expired.
	 */
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

	/**
	 * The willTokenExpire method is used to check if the endpoints token will expire within the next ten minutes.
	 * @param endpoint the endpoint being looked at.
	 * @return a boolean indicating if the token will expire soon.
	 */
	private boolean willTokenExpire(Endpoint endpoint) {
		try {
			LocalDateTime expiryDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(endpoint.getDecodedToken().getExp()), ZoneId.systemDefault()).minusMinutes(10);
			return LocalDateTime.now().isAfter(expiryDate);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
