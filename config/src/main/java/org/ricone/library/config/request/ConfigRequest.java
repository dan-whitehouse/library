package org.ricone.library.config.request;

import org.ricone.library.config.response.*;
import org.ricone.library.config.response.model.App;
import org.ricone.library.config.response.model.District;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.InvocationTargetException;

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
		this.restTemplate = new RestTemplate();
	}

	/* REQUESTS */
	public AppResponse getApp(ConfigPath request) { return getRequest(request, AppResponse.class, App.class); }
	public AppsResponse getApps(ConfigPath request) {
		return getRequest(request, AppsResponse.class, App[].class);
	}
	public DistrictResponse getDistrict(ConfigPath request) { return getRequest(request, DistrictResponse.class, District.class); }
	public DistrictsResponse getDistricts(ConfigPath request) { return getRequest(request, DistrictsResponse.class, District[].class); }

	/* ACTUAL REQUEST */
	private <RESPONSE extends ConfigResponse<REQUEST>, REQUEST> RESPONSE getRequest(ConfigPath request, Class<RESPONSE> responseClass, Class<REQUEST> requestClass) {
		RESPONSE data = null;
		String requestPath = getRequestPath(request);
		HttpEntity httpEntity = getHttpEntity(request);
		try {
			ResponseEntity<REQUEST> response = restTemplate.exchange(requestPath, HttpMethod.GET, httpEntity, requestClass);
			if(response.hasBody()) {
				data = createResponseObject(responseClass, requestClass);
				data.setData(response.getBody());
				data.setRequestPath(requestPath);
				data.setRequestHeaders(httpEntity.getHeaders());
				data.setResponseStatus(response.getStatusCode());
				data.setResponseHeaders(response.getHeaders());
			}
			else {
				data = setDataOnNoContent(responseClass, requestClass, requestPath, httpEntity, response);
			}
		}
		catch (HttpClientErrorException e) {
			data = setDataOnError(responseClass, requestClass, requestPath, httpEntity, e);
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

	/* CREATE GENERIC RESPONSE OBJECT */
	private <RESPONSE extends ConfigResponse<REQUEST>, REQUEST> RESPONSE createResponseObject(Class<RESPONSE> responseClass, Class<REQUEST> requestClass) {
		RESPONSE data = null;
		try {
			data = responseClass.getDeclaredConstructor().newInstance();
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		return data;
	}

	/* CREATE GENERIC RESPONSE OBJECT ON ERROR*/
	private <RESPONSE extends ConfigResponse<REQUEST>, REQUEST> RESPONSE setDataOnError(Class<RESPONSE> responseClass, Class<REQUEST> requestClass, String requestPath, HttpEntity httpEntity, HttpClientErrorException exception) {
		RESPONSE data = null;
		try {
			data = responseClass.getDeclaredConstructor().newInstance();
			data.setRequestPath(requestPath);
			data.setRequestHeaders(httpEntity.getHeaders());
			data.setResponseHeaders(exception.getResponseHeaders());
			data.setResponseStatusText(exception.getStatusText());
			data.setResponseStatus(exception.getStatusCode());
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return data;
	}

	/* CREATE GENERIC RESPONSE OBJECT ON EMPTY*/
	private <RESPONSE extends ConfigResponse<REQUEST>, REQUEST> RESPONSE setDataOnNoContent(Class<RESPONSE> responseClass, Class<REQUEST> requestClass, String requestPath, HttpEntity httpEntity, ResponseEntity<REQUEST> response) {
		RESPONSE data = null;
		try {
			data = responseClass.getDeclaredConstructor().newInstance();
			data.setRequestPath(requestPath);
			data.setRequestHeaders(httpEntity.getHeaders());
			data.setResponseHeaders(response.getHeaders());
			data.setResponseStatusText(response.getStatusCode().getReasonPhrase());
			data.setResponseStatus(response.getStatusCode());
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		return data;
	}
}
