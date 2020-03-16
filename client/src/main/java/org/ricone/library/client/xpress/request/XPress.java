package org.ricone.library.client.xpress.request;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Model;
import org.ricone.library.client.xpress.response.*;
import org.ricone.library.client.xpress.response.model.*;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
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
		this.restTemplate.setErrorHandler(new XErrorHandler());
		this.restTemplate.setMessageConverters(Collections.singletonList(converter));
	}

	/* PUBLIC REQUESTS */
	public XLeaResponse getXLea(XRequest request) {
		return request(request, XLeaResponse.class, XLea.class);
	}

	public XLeasResponse getXLeas(XRequest request) {
		return request(request, XLeasResponse.class, XLeas.class);
	}

	public XSchoolResponse getXSchool(XRequest request) {
		return request(request, XSchoolResponse.class, XSchool.class);
	}

	public XSchoolsResponse getXSchools(XRequest request) { return request(request, XSchoolsResponse.class, XSchools.class); }

	public XCalendarResponse getXCalendar(XRequest request) { return request(request, XCalendarResponse.class, XCalendar.class); }

	public XCalendarsResponse getXCalendars(XRequest request) { return request(request, XCalendarsResponse.class, XCalendars.class); }

	public XCourseResponse getXCourse(XRequest request) { return request(request, XCourseResponse.class, XCourse.class); }

	public XCoursesResponse getXCourses(XRequest request) { return request(request, XCoursesResponse.class, XCourses.class); }

	public XRosterResponse getXRoster(XRequest request) { return request(request, XRosterResponse.class, XRoster.class); }

	public XRostersResponse getXRosters(XRequest request) { return request(request, XRostersResponse.class, XRosters.class); }

	public XStaffResponse getXStaff(XRequest request) { return request(request, XStaffResponse.class, XStaff.class); }

	public XStaffsResponse getXStaffs(XRequest request) { return request(request, XStaffsResponse.class, XStaffs.class); }

	public XStudentResponse getXStudent(XRequest request) { return request(request, XStudentResponse.class, XStudent.class); }

	public XStudentsResponse getXStudents(XRequest request) { return request(request, XStudentsResponse.class, XStudents.class); }

	public XContactResponse getXContact(XRequest request) { return request(request, XContactResponse.class, XContact.class); }

	public XContactsResponse getXContacts(XRequest request) { return request(request, XContactsResponse.class, XContacts.class); }

	public Integer getLastPage(XRequest request) { return requestLastPage(request); }

	public XLastPageResponse getXLastPage(XRequest request) {
		return requestLastPageResponse(request);
	}


	public <R extends IResponse<M>, M extends Model> R request(XRequest request) {
		switch(request.request().path().getResponseClass().getSimpleName()) {
			case "XLeaResponse":  return (R) getXLea(request);
			case "XLeasResponse":  return (R) getXLeas(request);
			case "XSchoolResponse":  return (R) getXSchool(request);
			case "XSchoolsResponse":  return (R) getXSchools(request);
			case "XCalendarResponse":  return (R) getXCalendar(request);
			case "XCalendarsResponse":  return (R) getXCalendars(request);
			case "XCourseResponse":  return (R) getXCourse(request);
			case "XCoursesResponse":  return (R) getXCourses(request);
			case "XRosterResponse":  return (R) getXRoster(request);
			case "XRostersResponse":  return (R) getXRosters(request);
			case "XStaffResponse":  return (R) getXStaff(request);
			case "XStaffsResponse":  return (R) getXStaffs(request);
			case "XStudentResponse":  return (R) getXStudent(request);
			case "XStudentsResponse":  return (R) getXStudents(request);
			case "XContactResponse":  return (R) getXContact(request);
			case "XContactsResponse":  return (R) getXContacts(request);
			case "XLastPageResponse":  return (R) requestLastPageResponse(request);
			default: return null;
		}
	}

	/* PRIVATE REQUEST */
	private <R extends IResponse<M>, M extends Model> R request(XRequest request, Class<R> responseClass, Class<M> modelClass) {
		//Before doing anything, make sure that the request path can return the response object.
		verifyRequestAndResponse(request, responseClass, modelClass);

		R response = null;
		String requestPath = getRequestPath(request);
		HttpEntity<?> httpEntity = getHttpEntity(request);
		try {
			ResponseEntity<R> entity = restTemplate.exchange(requestPath, HttpMethod.GET, httpEntity, responseClass);
			if(entity.hasBody()) {
				response = entity.getBody();
				assert response != null;
				response.setRequestPath(requestPath);
				response.setRequestHeaders(httpEntity.getHeaders());
				response.setResponseStatus(entity.getStatusCode());
				response.setResponseHeaders(entity.getHeaders());

				//Needed for determining the JSON/XML building
				response.setResponseClass(responseClass);
				response.setModelClass(modelClass);
			}
			else {
				response = setDataOnNoContent(responseClass, modelClass, requestPath, httpEntity, entity);
			}
		}
		catch (HttpClientErrorException e) {
			response = setDataOnError(responseClass, modelClass, requestPath, httpEntity, e);
		}
		catch(ResourceAccessException rae) {
			response = setDataOnServerDown(responseClass, modelClass, requestPath, httpEntity, rae);
		}
		catch(HttpStatusCodeException c) {
			c.printStackTrace();
		}
		return response;
	}

	private Integer requestLastPage(XRequest request) {
		Integer response = null;
		String requestPath = getRequestPath(request);
		HttpEntity<?> httpEntity = getHttpEntity(request);
		try {
			ResponseEntity<String> entity = restTemplate.exchange(requestPath, HttpMethod.GET, httpEntity, String.class);

			String value = entity.getHeaders().getFirst("navigationLastPage");
			if(StringUtils.hasText(value)) {
				response = NumberUtils.parseNumber(value, Integer.class);
			}
		}
		catch (Exception e) {
			System.out.println("request error: " + e.getMessage());
		}
		return response;
	}

	private XLastPageResponse requestLastPageResponse(XRequest request) {
		XLastPageResponse response;
		String requestPath = getRequestPath(request);
		HttpEntity<?> httpEntity = getHttpEntity(request);
		try {
			ResponseEntity<String> entity = restTemplate.exchange(requestPath, HttpMethod.HEAD, httpEntity, String.class);

			String value = entity.getHeaders().getFirst("navigationLastPage");
			if(StringUtils.hasText(value)) {
				response = new XLastPageResponse(NumberUtils.parseNumber(value, Integer.class));
				response.setRequestPath(requestPath);
				response.setRequestHeaders(httpEntity.getHeaders());
				response.setResponseStatus(entity.getStatusCode());
				response.setResponseHeaders(entity.getHeaders());
			}
			else {
				response = setDataOnNoContent(XLastPageResponse.class,LastPage.class, requestPath, httpEntity, entity);
			}
		}
		catch (HttpClientErrorException e) {
			e.printStackTrace();
			response = setDataOnError(XLastPageResponse.class, LastPage.class, requestPath, httpEntity, e);
		}
		return response;
	}

	/* GET URL */
	private String getRequestPath(XRequest request) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint.getHref());
		if(!request.request().path().getServicePathType().equals(ServicePathType.OBJECT)) {
			if(request.request().idType().equals(IdType.RefId)) {
				builder.path(StringUtils.replace(request.request().path().getValue(), "{refId}", request.request().id()));
			}
			else {
				builder.path(StringUtils.replace(request.request().path().getValue(), "{id}", request.request().id()));
			}
		}
		else {
			builder.path(request.request().path().getValue());
		}

		if(request.hasAUPP()) {
			builder.queryParam("getUsers", true);
		}
		else if(request.hasChangesSince()) {
			builder.queryParam("changesSinceMarker", request.with().getChangesSince().iso8601());
		}

		if(request.configuration().withAsQueryParameters()) {
			if(request.hasIdType() && !request.request().idType().equals(IdType.RefId)) {
				builder.queryParam("idType", request.request().idType().getValue());
			}

			if(request.hasPaging()) {
				builder.queryParam("navigationPage", String.valueOf(request.with().paging().getPage()));
				builder.queryParam("navigationPageSize", String.valueOf(request.with().paging().getSize()));
			}

			if(request.hasSchoolYear()) {
				builder.queryParam("SchoolYear", request.with().schoolYear().toString());
			}
		}

		return builder.build().toUriString();
	}

	/* GET HEADERS */
	private HttpEntity<?> getHttpEntity(XRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer " + this.endpoint.getDecodedToken().getToken());

		if(request.configuration().withAsHeaders()) {
			if(request.hasIdType() && !request.request().idType().equals(IdType.RefId)) {
				headers.set("IdType", request.request().idType().getValue());
			}

			if(request.hasPaging()) {
				headers.set("navigationPage", String.valueOf(request.with().paging().getPage()));
				headers.set("navigationPageSize", String.valueOf(request.with().paging().getSize()));
			}

			if(request.hasSchoolYear()) {
				headers.set("SchoolYear", request.with().schoolYear().toString());
			}
		}
		return new HttpEntity<>(headers);
	}

	/* ON ERROR */
	private <R extends IResponse<M>, M extends Model> R setDataOnNoContent(Class<R> responseClass, Class<M> modelClass, String requestPath, HttpEntity<?> httpEntity, ResponseEntity<?> response) {
		R xResponse = null;
		try {
			xResponse = responseClass.getDeclaredConstructor().newInstance();
			xResponse.setData(modelClass.getDeclaredConstructor().newInstance());
			xResponse.setRequestPath(requestPath);
			xResponse.setRequestHeaders(httpEntity.getHeaders());
			xResponse.setResponseHeaders(response.getHeaders());
			xResponse.setResponseStatusText(response.getStatusCode().getReasonPhrase());
			xResponse.setResponseStatus(response.getStatusCode());

			//Needed for determining the JSON/XML building
			xResponse.setResponseClass(responseClass);
			xResponse.setModelClass(modelClass);
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return xResponse;
	}

	private <R extends IResponse<M>, M extends Model> R setDataOnError(Class<R> responseClass, Class<M> modelClass, String requestPath, HttpEntity<?> httpEntity, HttpClientErrorException exception) {
		R xResponse = null;
		try {
			xResponse = responseClass.getDeclaredConstructor().newInstance();
			xResponse.setData(modelClass.getDeclaredConstructor().newInstance());
			xResponse.setRequestPath(requestPath);
			xResponse.setRequestHeaders(httpEntity.getHeaders());
			xResponse.setResponseHeaders(exception.getResponseHeaders());
			xResponse.setResponseStatusText(exception.getStatusText());
			xResponse.setResponseStatus(exception.getStatusCode());

			//Needed for determining the JSON/XML building
			xResponse.setResponseClass(responseClass);
			xResponse.setModelClass(modelClass);
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return xResponse;
	}

	private <R extends IResponse<M>, M extends Model> R setDataOnServerDown(Class<R> responseClass, Class<M> modelClass, String requestPath, HttpEntity<?> httpEntity, ResourceAccessException exception) {
		R xResponse = null;
		try {
			xResponse = responseClass.getDeclaredConstructor().newInstance();
			xResponse.setData(modelClass.getDeclaredConstructor().newInstance());
			xResponse.setRequestPath(requestPath);
			xResponse.setRequestHeaders(httpEntity.getHeaders());
			xResponse.setResponseHeaders(new HttpHeaders());
			xResponse.setResponseStatusText(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
			xResponse.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE);

			//Needed for determining the JSON/XML building
			xResponse.setResponseClass(responseClass);
			xResponse.setModelClass(modelClass);
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return xResponse;
	}


	private <R extends IResponse<M>, M extends Model> void verifyRequestAndResponse(XRequest request, Class<R> responseClass, Class<M> modelClass) {
		if(!request.request().path().getResponseClass().equals(responseClass)) {
			throw new IllegalArgumentException("ServicePath: " + request.request().path() + " requires that the response return: " + request.request().path().getResponseClass().getCanonicalName()
					+ ", however the response will return: " + responseClass.getCanonicalName());
		}
	}
}
