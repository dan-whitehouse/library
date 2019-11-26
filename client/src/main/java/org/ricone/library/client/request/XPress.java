package org.ricone.library.client.request;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.response.*;
import org.ricone.library.exception.InternalException;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

/**
 * @project: client
 * @author: Dan on 6/30/2018.
 */
public class XPress {
	private Endpoint endpoint;
	private RestTemplate restTemplate;

	public XPress(Endpoint endpoint) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(mapper);

		this.endpoint = endpoint;
		this.restTemplate = new RestTemplate();
		this.restTemplate.setErrorHandler(new XResponseErrorHandler());
		this.restTemplate.setMessageConverters(Collections.singletonList(converter));
	}

	/* PUBLIC REQUESTS */
	public XLeaResponse getXLea(XRequest request) {
		return request(request, XLeaResponse.class);
	}

	public XLeasResponse getXLeas(XRequest request) {
		return request(request, XLeasResponse.class);
	}

	public XSchoolResponse getXSchool(XRequest request) {
		return request(request, XSchoolResponse.class);
	}

	public XSchoolsResponse getXSchools(XRequest request) {
		return request(request, XSchoolsResponse.class);
	}

	public XCalendarResponse getXCalendar(XRequest request) {
		return request(request, XCalendarResponse.class);
	}

	public XCalendarsResponse getXCalendars(XRequest request) {
		return request(request, XCalendarsResponse.class);
	}

	public XCourseResponse getXCourse(XRequest request) {
		return request(request, XCourseResponse.class);
	}

	public XCoursesResponse getXCourses(XRequest request) {
		return request(request, XCoursesResponse.class);
	}

	public XRosterResponse getXRoster(XRequest request) {
		return request(request, XRosterResponse.class);
	}

	public XRostersResponse getXRosters(XRequest request) {
		return request(request, XRostersResponse.class);
	}

	public XStaffResponse getXStaff(XRequest request) {
		return request(request, XStaffResponse.class);
	}

	public XStaffsResponse getXStaffs(XRequest request) {
		return request(request, XStaffsResponse.class);
	}

	public XStudentResponse getXStudent(XRequest request) {
		return request(request, XStudentResponse.class);
	}

	public XStudentsResponse getXStudents(XRequest request) {
		return request(request, XStudentsResponse.class);
	}

	public XContactResponse getXContact(XRequest request) {
		return request(request, XContactResponse.class);
	}

	public XContactsResponse getXContacts(XRequest request) {
		return request(request, XContactsResponse.class);
	}

	public Integer getLastPage(XRequest request) {
		return requestLastPage(request);
	}

	public XLastPageResponse getXLastPage(XRequest request) {
		return requestLastPageResponse(request);
	}

	/* PRIVATE REQUEST */
	private <T extends XResponse> T request(XRequest request, Class<T> clazz) {
		//Before doing anything, make sure that the request path can return the response object.
		verifyRequestAndResponse(request, clazz);

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
				data = setDataOnNoContent(clazz, requestPath, httpEntity, response);
			}
		}
		catch (HttpClientErrorException e) {
			data = setDataOnError(clazz, requestPath, httpEntity, e);
		}
		catch(HttpStatusCodeException c) {
			System.out.println("TEST TEST TEST");
		}
		return data;
	}

	private Integer requestLastPage(XRequest request) {
		Integer data = null;
		String requestPath = getRequestPath(request);
		HttpEntity httpEntity = getHttpEntity(request);
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(requestPath, HttpMethod.GET, httpEntity, String.class);

			String value = response.getHeaders().getFirst("navigationLastPage");
			if(StringUtils.hasText(value)) {
				data = NumberUtils.parseNumber(value, Integer.class);
			}
		}
		catch (Exception e) {
			System.out.println("request error: " + e.getMessage());
		}
		return data;
	}

	private XLastPageResponse requestLastPageResponse(XRequest request) {
		XLastPageResponse data = null;
		String requestPath = getRequestPath(request);
		HttpEntity httpEntity = getHttpEntity(request);
		try {
			ResponseEntity<String> response = restTemplate.exchange(requestPath, HttpMethod.HEAD, httpEntity, String.class);

			String value = response.getHeaders().getFirst("navigationLastPage");
			if(StringUtils.hasText(value)) {
				data = new XLastPageResponse(NumberUtils.parseNumber(value, Integer.class));
				data.setRequestPath(requestPath);
				data.setRequestHeaders(httpEntity.getHeaders());
				data.setResponseStatus(response.getStatusCode());
				data.setResponseHeaders(response.getHeaders());
			}
			else {
				data = setDataOnNoContent(XLastPageResponse.class, requestPath, httpEntity, response);
			}
		}
		catch (HttpClientErrorException e) {
			e.printStackTrace();
			data = setDataOnError(XLastPageResponse.class, requestPath, httpEntity, e);
		}
		return data;
	}

	/* GET URL */
	private String getRequestPath(XRequest request) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint.getHref());
		if(!request.getServicePath().getServicePathType().equals(ServicePathType.OBJECT)) {
			if(request.hasIdType()) {
				builder.path(StringUtils.replace(request.getServicePath().getValue(), "{id}", request.getId()));
			}
			else {
				builder.path(StringUtils.replace(request.getServicePath().getValue(), "{refId}", request.getId()));
			}
		}
		else {
			builder.path(request.getServicePath().getValue());
		}

		if(request.hasAUPP()) {
			builder.queryParam(request.getAuppType().getValue(), true);
		}

		if(request.hasChangesSince()) {
			builder.queryParam("changesSinceMarker", request.getChangesSince().iso8601());
		}

		return builder.build().toUriString();
	}

	/* GET HEADERS */
	private HttpEntity<?> getHttpEntity(XRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer " + endpoint.getToken());

		if(request.hasIdType()) {
			headers.set("IdType", request.getIdType().getValue());
		}

		if(request.hasPaging()) {
			headers.set("navigationPage", request.getPagingInfo().getPageNumber().toString());
			headers.set("navigationPageSize", request.getPagingInfo().getPageSize().toString());
		}

		if(request.hasSchoolYear()) {
			headers.set("SchoolYear", request.getSchoolYear().toString());
		}
		return new HttpEntity<>(headers);
	}

	/* ON ERROR */
	private <T extends XResponse> T setDataOnError(Class<T> clazz, String requestPath, HttpEntity httpEntity, HttpClientErrorException exception) {
		T data = null;
		try {
			data = clazz.getDeclaredConstructor().newInstance();
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

	private <T extends XResponse> T setDataOnNoContent(Class<T> clazz, String requestPath, HttpEntity httpEntity, ResponseEntity response) {
		T data = null;
		try {
			data = clazz.getDeclaredConstructor().newInstance();
			data.setRequestPath(requestPath);
			data.setRequestHeaders(httpEntity.getHeaders());
			data.setResponseHeaders(response.getHeaders());
			data.setResponseStatusText(response.getStatusCode().getReasonPhrase());
			data.setResponseStatus(response.getStatusCode());
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return data;
	}

	private <T extends XResponse> void verifyRequestAndResponse(XRequest request, Class<T> clazz) {
		if(!request.getServicePath().getResponseClass().equals(clazz)) {
			throw new IllegalArgumentException("ServicePath: " + request.getServicePath() + " requires that the response return: " + request.getServicePath().getResponseClass().getCanonicalName()
					+ ", however the response will return: " + clazz.getCanonicalName());
		}
	}
}
