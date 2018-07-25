package org.ricone.library.config.request;

import org.ricone.library.config.Credential;
import org.ricone.library.config.Login;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    private long ttl;
    private LocalDateTime created;
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
		this.url = url;
		this.username = username;
		this.password = password;

        HttpEntity<Credential> entity = new HttpEntity<>(new Credential(username, password));
		try {
			ResponseEntity<Login> login = restTemplate.exchange(url + ServicePath.GET_LOGIN.getValue(), HttpMethod.POST, entity, Login.class);
			if(login.hasBody()) {
				token = login.getBody().getId();
				ttl = login.getBody().getTtl();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS[xxx][xx][X]");
                created = LocalDateTime.parse(login.getBody().getCreated(), formatter);

                endpoint = new Endpoint(url, token);
			}
		}
		catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
	}

    public boolean isAuthenticated() {
        return !isTokenExpired();
    }

    String getToken() {
		if(isTokenExpired()) {
			authenticate(url, username, password);
		}
        return token;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    private boolean isTokenExpired() {
        try {
            LocalDateTime expiryDate = created.plusSeconds(ttl);
            return expiryDate.isBefore(LocalDateTime.now());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}