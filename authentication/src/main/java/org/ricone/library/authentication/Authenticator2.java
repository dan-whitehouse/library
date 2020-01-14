package org.ricone.library.authentication;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class Authenticator2 {
	private RestTemplate restTemplate;
	private ObjectMapper mapper;
	private String url;
	private String username;
	private String password;
	private String provider;
	private String token;
	private DecodedToken decodedToken;
	private List<Endpoint> endpoints;

	public static Builder builder() {
		return new Builder();
	}

	private Authenticator2() {
		restTemplate = new RestTemplate();
		mapper = new ObjectMapper();
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

		public Authenticator2 authenticate()  {
			instance.authenticate(instance.url, instance.username, instance.password);
			return instance;
		}
	}

	private void authenticate(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;

		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("username", username);
		body.add("password", password);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

		try {
			ResponseEntity<Login> login = restTemplate.exchange(url, HttpMethod.POST, entity, Login.class);
			if(login.hasBody()) {
				token = Objects.requireNonNull(login.getBody()).getToken();
				endpoints = login.getBody().getEndpoints();
				decodedToken = mapper.readValue(new String(Base64.getDecoder().decode(JWT.decode(token).getPayload())), DecodedToken.class);
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

	/**
	 * Determine if authentication was successful.
	 *
	 * @return value of type boolean.
	 */
	public boolean isAuthenticated() {
		return !isTokenExpired();
	}

	String getToken() {
		if(isTokenExpired() || willTokenExpire()) {
			authenticate(url, username, password);
		}
		return token;
	}

	private boolean isTokenExpired() {
		try {
			LocalDateTime expiryDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(decodedToken.getExp()), ZoneId.systemDefault());
			return expiryDate.isBefore(LocalDateTime.now());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean willTokenExpire() {
		try {
			LocalDateTime expiryDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(decodedToken.getExp()), ZoneId.systemDefault()).minusMinutes(10);
			return LocalDateTime.now().isAfter(expiryDate);
			//return expiryDate.isBefore(LocalDateTime.now());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
