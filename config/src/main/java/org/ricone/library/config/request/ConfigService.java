package org.ricone.library.config.request;

import org.ricone.library.config.response.model.Credential;
import org.ricone.library.config.response.model.Login;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfigService {
    private RestTemplate restTemplate;
    private String url;
    private String username;
    private String password;
    private String token;
    private Endpoint endpoint;

    private ConfigService() {
        restTemplate = new RestTemplate();
    }

    private static class SingletonHelper {
        private static final ConfigService INSTANCE = new ConfigService();
    }

    public static ConfigService getInstance() {
        return SingletonHelper.INSTANCE;
    }

	public void authenticate(String url, String username, String password) {
		this.url = appendSlashIfMissing(url);
		this.username = username;
		this.password = password;

		try {
			ResponseEntity<Login> login = restTemplate.exchange(this.url + "users/login", HttpMethod.POST, getHttpEntity(), Login.class);
			if(login.hasBody()) {
				token = login.getBody().getId();
				var created = LocalDateTime.parse(login.getBody().getCreated(), DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS[xxx][xx][X]"));
				var expires = created.plusSeconds(login.getBody().getTtl());
				endpoint = new Endpoint(url, token, created, expires);
			}
		}
		catch (HttpClientErrorException e) {
			e.printStackTrace();
			System.out.print(e.getResponseHeaders());
		}
	}

	private HttpEntity<Credential> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(new Credential(username, password), headers);
	}

	private boolean isTokenExpired() {
		if(endpoint != null) {
			try {
				return endpoint.getExpires().isBefore(LocalDateTime.now());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private String appendSlashIfMissing(String url) {
		if(url != null && url.endsWith("/")) {
			return url;
		}
		return url = url + "/";
	}

    String getToken() {
		if(isTokenExpired()) {
			authenticate(url, username, password);
		}
        return token;
    }

	public boolean isAuthenticated() {
		return !isTokenExpired();
	}

    public Endpoint getEndpoint() {
        return endpoint;
    }


}