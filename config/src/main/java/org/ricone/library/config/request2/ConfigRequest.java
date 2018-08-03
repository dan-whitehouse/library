package org.ricone.library.config.request2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ricone.library.config.response.ConfigResponse;
import org.ricone.library.config.response.VendorResponse;
import org.ricone.library.config.response.VendorsResponse;
import org.ricone.library.config.response.model.ListWrapper;
import org.ricone.library.config.response.model.Vendor;
import org.ricone.library.config.response.model.Vendors;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @project: client
 * @author: Dan on 6/30/2018.
 */
public class ConfigRequest {
	private RestTemplate restTemplate;
	private Endpoint endpoint;
	private ObjectMapper mapper;
	private final String AUTHORIZATION = "Authorization";

	public ConfigRequest(Endpoint endpoint) {
		this.endpoint = endpoint;
		this.mapper = new ObjectMapper();
		this.restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ErrorHandler());
		//this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}

	/* GET REQUESTS */
	public VendorResponse vendor(ConfigPath request) {
		if(request.isHttpMethodType(HttpMethod.HEAD) || request.isHttpMethodType(HttpMethod.DELETE)) {
			return requestVoid(request, request.getHttpMethod(), VendorResponse.class, Vendor.class);
		}
		else {
			return request(request, VendorResponse.class, Vendor.class);
		}
	}

	public VendorsResponse vendors(ConfigPath request) {
		if(request.isHttpMethodType(HttpMethod.HEAD) || request.isHttpMethodType(HttpMethod.DELETE)) {
			return requestVoid(request, request.getHttpMethod(), VendorsResponse.class, Vendors.class);
		}
		else {
			return requestList(request, VendorsResponse.class, Vendors.class, Vendor.class);
		}
	}

	/* ACTUAL REQUEST */
	private <RESPONSE extends ConfigResponse<REQUEST>, REQUEST> RESPONSE request(ConfigPath request, Class<RESPONSE> responseClass, Class<REQUEST> requestClass) {
		RESPONSE data;
		String requestPath = getRequestPath(request);
		HttpEntity httpEntity = getHttpEntity(request);
		try {
			ResponseEntity<REQUEST> response = restTemplate.exchange(requestPath, request.getHttpMethod(), httpEntity, requestClass);
			if(response.hasBody()) {
				data = createData(responseClass);
				data.setData(response.getBody());
				data.setRequestPath(requestPath);
				data.setRequestHeaders(httpEntity.getHeaders());
				data.setResponseStatus(response.getStatusCode());
				data.setResponseHeaders(response.getHeaders());
			}
			else {
				data = setDataOnNoContent(responseClass, requestPath, httpEntity, response);
			}
		}
		catch (HttpClientErrorException e) {
			data = setDataOnError(responseClass, requestPath, httpEntity, e);
		}
		return data;
	}

	private <RESPONSE extends ConfigResponse<RESPONSE_OBJECT>, RESPONSE_OBJECT extends ListWrapper<REQUEST>, REQUEST> RESPONSE requestList(ConfigPath request, Class<RESPONSE> responseClass, Class<RESPONSE_OBJECT> responseObjectClass, Class<REQUEST> requestClass) {
		RESPONSE data;
		String requestPath = getRequestPath(request);
		HttpEntity httpEntity = getHttpEntity(request);
		try {
			ResponseEntity<String> response = restTemplate.exchange(requestPath, request.getHttpMethod(), httpEntity, String.class);
			if(response.hasBody()) {
				data = createData(responseClass);
				if(data != null) {
					RESPONSE_OBJECT responseObject = createResponseObject(responseObjectClass);
					try {
						List<REQUEST> list = mapper.readValue(response.getBody(), mapper.getTypeFactory().constructCollectionType(List.class, requestClass));
						responseObject.setList(list);
					}
					catch (IOException ignored) {
					}

					data.setData(responseObject);
					data.setRequestPath(requestPath);
					data.setRequestHeaders(httpEntity.getHeaders());
					data.setResponseStatus(response.getStatusCode());
					data.setResponseHeaders(response.getHeaders());
				}
			}

			else {
				data = setDataOnNoContent(responseClass, requestPath, httpEntity, response);
			}
		}
		catch (HttpClientErrorException e) {
			data = setDataOnError(responseClass, requestPath, httpEntity, e);
		}
		return data;
	}

	private <RESPONSE extends ConfigResponse<REQUEST>, REQUEST> RESPONSE requestVoid(ConfigPath request, HttpMethod httpMethod, Class<RESPONSE> responseClass, Class<REQUEST> requestClass) {
		RESPONSE data;
		String requestPath = getRequestPath(request);
		HttpEntity httpEntity = getHttpEntity(request);
		try {
			ResponseEntity response = restTemplate.exchange(requestPath, httpMethod, httpEntity, Void.class);
			data = createData(responseClass);
			data.setRequestPath(requestPath);
			data.setRequestHeaders(httpEntity.getHeaders());
			data.setResponseStatus(response.getStatusCode());
			data.setResponseHeaders(response.getHeaders());
		}
		catch (HttpClientErrorException e) {
			data = setDataOnError(responseClass, requestPath, httpEntity, e);
		}
		return data;
	}

	/* GET URL */
	private String getRequestPath(ConfigPath request) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint.getHref());
		if(request.getServicePath().getServicePathType().equals(ServicePathType.SINGLE) || request.getServicePath().getServicePathType().equals(ServicePathType.MANY_WITH_PREDICATE)) {
			builder.path(StringUtils.replace(request.getServicePath().getValue(), "{id}", request.getId()));
		}
		else if(request.getServicePath().getServicePathType().equals(ServicePathType.SINGLE_WITH_PREDICATE)) {
			String path = StringUtils.replace(request.getServicePath().getValue(), "{predicate_id}", request.getPredicateId());
			path = StringUtils.replace(path, "{id}", request.getId());
			builder.path(path);
		}
		else {
			builder.path(request.getServicePath().getValue());
		}
		return builder.build().toUriString();
	}

	/* HEADERS */
	private HttpEntity<?> getHttpEntity(ConfigPath request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		headers.set(AUTHORIZATION, endpoint.getToken());

		if(request.hasBody()) {
			if(request.getBody() instanceof ListWrapper) {
				try {
					String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(((ListWrapper) request.getBody()).getList());
					return new HttpEntity<>(json, headers);
				}
				catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
			return new HttpEntity<>(request.getBody(), headers);
		}
		return new HttpEntity<>(headers);
	}

	/* CREATE GENERIC RESPONSE MANY */
	private <RESPONSE extends ConfigResponse<T>, T> RESPONSE createData(Class<RESPONSE> responseClass) {
		RESPONSE data = null;
		try {
			data = responseClass.getDeclaredConstructor().newInstance();
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		return data;
	}

	/* CREATE GENERIC RESPONSE_OBJECT MANY */
	private <RESPONSE_OBJECT> RESPONSE_OBJECT createResponseObject(Class<RESPONSE_OBJECT> responseObjectClass) {
		RESPONSE_OBJECT data = null;
		try {
			data = responseObjectClass.getDeclaredConstructor().newInstance();
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		return data;
	}

	/* CREATE GENERIC RESPONSE MANY ON ERROR*/
	private <RESPONSE extends ConfigResponse<REQUEST>, REQUEST> RESPONSE setDataOnError(Class<RESPONSE> responseClass, String requestPath, HttpEntity httpEntity, HttpClientErrorException exception) {
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

	/* CREATE GENERIC RESPONSE MANY ON EMPTY*/
	private <RESPONSE extends ConfigResponse<REQUEST>, REQUEST> RESPONSE setDataOnNoContent(Class<RESPONSE> responseClass, String requestPath, HttpEntity httpEntity, ResponseEntity response) {
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
