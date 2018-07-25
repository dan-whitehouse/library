package org.ricone.library.config.request;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ricone.library.config.response.AppResponse;
import org.ricone.library.config.response.ConfigResponse;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

/**
 * @project: client
 * @author: Dan on 6/30/2018.
 */
public class ConfigRequest {
	private RestTemplate restTemplate;
	private Endpoint endpoint;
	private final String AUTHORIZATION = "Authorization";

	public ConfigRequest(Endpoint endpoint) {
		this.endpoint = endpoint;

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(mapper);

		restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Collections.singletonList(converter));
	}

	/* REQUESTS */
	public AppResponse getApp(ConfigPath request) {
		return request(request, AppResponse.class);
	}


	/* ACTUAL REQUEST */
	private <T extends ConfigResponse> T request(ConfigPath request, Class<T> clazz) {
		T data = null;
		String requestPath = getRequestPath(request);
		HttpEntity httpEntity = getHttpEntity(request);
		try {
			ResponseEntity<T> response = restTemplate.exchange(requestPath, HttpMethod.GET, httpEntity, clazz);
			if(response.hasBody()) {
				data = response.getBody();
				assert data != null;
				data.setRequestPath(requestPath);
				data.setRequestHeaders(httpEntity.getHeaders());
				data.setResponseStatus(response.getStatusCode());
				data.setResponseHeaders(response.getHeaders());
			}
			else {
				//data = setDataOnNoContent(clazz, requestPath, httpEntity, response);
			}
		}
		catch (HttpClientErrorException e) {
			e.printStackTrace();
			//data = setDataOnError(clazz, requestPath, httpEntity, e);
		}
		return data;
	}

	/* GET URL */
	private String getRequestPath(ConfigPath request) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint.getHref());
		if(!request.getServicePath().getServicePathType().equals(ServicePathType.OBJECT)) {
			builder.path(StringUtils.replace(request.getServicePath().getValue(), "{id}", request.getId()));
		}
		else {
			builder.path(request.getServicePath().getValue());
		}

		return builder.build().toUriString();
	}

	/* GET HEADERS */
	private HttpEntity<?> getHttpEntity(ConfigPath request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(AUTHORIZATION, endpoint.getToken());
		return new HttpEntity<>(headers);
	}

}
