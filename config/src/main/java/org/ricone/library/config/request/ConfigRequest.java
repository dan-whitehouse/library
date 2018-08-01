package org.ricone.library.config.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ricone.library.config.response.*;
import org.ricone.library.config.response.model.*;
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
	}

	/* REQUESTS */
	public AppResponse getApp(ConfigPath request) {
		return request(request, AppResponse.class, App.class);
	}

	public AppsResponse getApps(ConfigPath request) {
		return requestList(request, AppsResponse.class, Apps.class, App.class);
	}

	public DistrictResponse getDistrict(ConfigPath request) {
		return request(request, DistrictResponse.class, District.class);
	}

	public DistrictsResponse getDistricts(ConfigPath request) {
		return requestList(request, DistrictsResponse.class, Districts.class, District.class);
	}

	public ProfileResponse getProfile(ConfigPath request) {
		return request(request, ProfileResponse.class, Profile.class);
	}

	public ProfilesResponse getProfiles(ConfigPath request) {
		return requestList(request, ProfilesResponse.class, Profiles.class, Profile.class);
	}

	public ProviderResponse getProvider(ConfigPath request) {
		return request(request, ProviderResponse.class, Provider.class);
	}

	public ProvidersResponse getProviders(ConfigPath request) {
		return requestList(request, ProvidersResponse.class, Providers.class, Provider.class);
	}

	public SchoolResponse getSchool(ConfigPath request) {
		return request(request, SchoolResponse.class, School.class);
	}

	public SchoolsResponse getSchools(ConfigPath request) {
		return requestList(request, SchoolsResponse.class, Schools.class, School.class);
	}

	public VendorResponse getVendor(ConfigPath request) {
		return request(request, VendorResponse.class, Vendor.class);
	}

	public VendorsResponse getVendors(ConfigPath request) {
		return requestList(request, VendorsResponse.class, Vendors.class, Vendor.class);
	}

	public ProviderKVResponse getProviderKV(ConfigPath request) {
		return request(request, ProviderKVResponse.class, ProviderKV.class);
	}

	public ProviderKVsResponse getProviderKVs(ConfigPath request) {
		return requestList(request, ProviderKVsResponse.class, ProviderKVs.class, ProviderKV.class);
	}

	/* ACTUAL RESPONSE_OBJECT */
	private <RESPONSE extends ConfigResponse<REQUEST>, REQUEST> RESPONSE request(ConfigPath request, Class<RESPONSE> responseClass, Class<REQUEST> requestClass) {
		RESPONSE data;
		String requestPath = getRequestPath(request);
		HttpEntity httpEntity = getHttpEntity(request);
		try {
			ResponseEntity<REQUEST> response = restTemplate.exchange(requestPath, HttpMethod.GET, httpEntity, requestClass);
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
			ResponseEntity<String> response = restTemplate.exchange(requestPath, HttpMethod.GET, httpEntity, String.class);
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

	/* GET URL */
	private String getRequestPath(ConfigPath request) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint.getHref());
		if(request.getServicePath().getServicePathType().equals(ServicePathType.SINGLE) || request.getServicePath().getServicePathType().equals(ServicePathType.PREDICATE)) {
			builder.path(StringUtils.replace(request.getServicePath().getValue(), "{id}", request.getId()));
		}
		else if(request.getServicePath().getServicePathType().equals(ServicePathType.PREDICATE_WITH_ID)) {
			String path = StringUtils.replace(request.getServicePath().getValue(), "{predicate_id}", request.getPredicateId());
			path = StringUtils.replace(path, "{id}", request.getId());
			builder.path(path);
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

	/* CREATE GENERIC RESPONSE_OBJECT OBJECT */
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


	/* CREATE GENERIC RESPONSE OBJECT ON ERROR*/
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

	/* CREATE GENERIC RESPONSE OBJECT ON EMPTY*/
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
