package org.ricone.library.client.oneroster.request;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.Experimental;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Model;
import org.ricone.library.client.oneroster.response.*;
import org.ricone.library.client.oneroster.response.model.*;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020.1
 */

public class OneRoster {
	private Endpoint endpoint;
	private RestTemplate restTemplate;

	public OneRoster(Endpoint endpoint) {
		ObjectMapper mapper = new ObjectMapper();

		//Modules
		mapper.registerModule(new ParameterNamesModule());
		mapper.registerModule(new Jdk8Module());
		mapper.registerModule(new JavaTimeModule()); // new module, NOT JSR310Module

		//Features
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(mapper);

		this.endpoint = endpoint;
		this.restTemplate = new RestTemplate();
		this.restTemplate.setErrorHandler(new ErrorHandler());
		this.restTemplate.setMessageConverters(Collections.singletonList(converter));
	}

	/* PUBLIC REQUESTS */
	public OrgResponse getOrg(Request request) { return request(request, OrgResponse.class, Org.class); }

	public OrgsResponse getOrgs(Request request) { return request(request, OrgsResponse.class, Orgs.class); }

	public AcademicSessionResponse getAcademicSession(Request request) { return request(request, AcademicSessionResponse.class, AcademicSession.class); }

	public AcademicSessionsResponse getAcademicSessions(Request request) { return request(request, AcademicSessionsResponse.class, AcademicSessions.class); }

	public CourseResponse getCourse(Request request) { return request(request, CourseResponse.class, Course.class); }

	public CoursesResponse getCourses(Request request) { return request(request, CoursesResponse.class, Courses.class); }

	public ClassResponse getClass(Request request) { return request(request, ClassResponse.class, org.ricone.library.client.oneroster.response.model.Class.class); }

	public ClassesResponse getClasses(Request request) { return request(request, ClassesResponse.class, Classes.class); }

	public EnrollmentResponse getEnrollment(Request request) { return request(request, EnrollmentResponse.class, Enrollment.class); }

	public EnrollmentsResponse getEnrollments(Request request) { return request(request, EnrollmentsResponse.class, Enrollments.class); }

	public UserResponse getUser(Request request) { return request(request, UserResponse.class, User.class); }

	public UsersResponse getUsers(Request request) { return request(request, UsersResponse.class, Users.class); }

	public DemographicResponse getDemographic(Request request) { return request(request, DemographicResponse.class, Demographic.class); }

	public DemographicsResponse getDemographics(Request request) { return request(request, DemographicsResponse.class, Demographics.class); }

	public OffsetResponse getOffset(Request request) { return requestOffsetResponse(request); }


	@SuppressWarnings("unchecked")
	@Experimental @Deprecated(since = "2020.1")
	/**
	 * The request() method is an experimental method used to simplify the need for picking the right method when developing with this library.
	 * The reason for the experimental annotation, is that the method is prone to throwing an exception if the user does not know what they are doing.
	 * Additionally, the compiler won't detect an issue until runtime.
	 *
	 * Correct Usage: - Orgs is used correctly in both the IResponse and the ServicePath.
	 * IResponse<Orgs> response = oneRoster.request(Request.builder().request().path(ServicePath.GET_Orgs).end().build());
	 *
	 * Incorrect Usage: - Expects Courses but will return Orgs resulting in a ClassCastException.
	 * IResponse<Courses> response = oneRoster.request(Request.builder().request().path(ServicePath.GET_Orgs).end().build());
	 *
	 * @param request is an instance of request builder, which contains all of the necessary information to make the request.
	 * @return an IResponse object containing an instance of the object being request.
	 */
	public <R extends IResponse<M>, M extends Model> R request(Request request) {
		switch(request.request().path().getResponseClass().getSimpleName()) {
			case "OrgResponse":  return (R) getOrg(request);
			case "OrgsResponse":  return (R) getOrgs(request);
			case "AcademicSessionResponse":  return (R) getAcademicSession(request);
			case "AcademicSessionsResponse":  return (R) getAcademicSessions(request);
			case "CourseResponse":  return (R) getCourse(request);
			case "CoursesResponse":  return (R) getCourses(request);
			case "ClassResponse":  return (R) getClass(request);
			case "ClassesResponse":  return (R) getClasses(request);
			case "EnrollmentResponse":  return (R) getEnrollment(request);
			case "EnrollmentsResponse":  return (R) getEnrollments(request);
			case "UserResponse":  return (R) getUser(request);
			case "UsersResponse":  return (R) getUsers(request);
			case "DemographicResponse":  return (R) getDemographic(request);
			case "DemographicsResponse":  return (R) getDemographics(request);
			case "OffsetResponse":  return (R) getOffset(request);
			default: return null;
		}
	}

	/* PRIVATE REQUEST */
	private <R extends IResponse<M>, M extends Model> R request(Request request, Class<R> responseClass, Class<M> modelClass) {
		//Before doing anything, make sure that the request path can return the response object.
		verifyRequestAndResponse(request, responseClass, modelClass);

		R response = null;
		String requestPath = getRequestPath(request);
		HttpEntity<?> httpEntity = getHttpEntity();
		try {
			ResponseEntity<R> entity = restTemplate.exchange(requestPath, HttpMethod.GET, httpEntity, responseClass);
			if(entity.hasBody()) {
				response = entity.getBody();
				assert response != null;
				response.setRequestPath(requestPath);
				response.setRequestHeaders(httpEntity.getHeaders());
				response.setResponseStatus(entity.getStatusCode());
				response.setResponseStatusText(entity.getStatusCode().getReasonPhrase());
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

	/* OFFSET REQUEST */
	private OffsetResponse requestOffsetResponse(Request request) {
		OffsetResponse response;
		String requestPath = getRequestPath(request);
		HttpEntity<?> httpEntity = getHttpEntity();
		try {
			ResponseEntity<String> entity = restTemplate.exchange(requestPath, HttpMethod.HEAD, httpEntity, String.class);

			String totalRecords = entity.getHeaders().getFirst("X-Total-Count");
			int limit = request.with().paging().getLimit();
			int offset = request.with().paging().getOffset();

			if(StringUtils.hasText(totalRecords)) {
				response = new OffsetResponse(getOffsetArray(limit, offset, totalRecords));
				response.setRequestPath(requestPath);
				response.setRequestHeaders(httpEntity.getHeaders());
				response.setResponseStatus(entity.getStatusCode());
				response.setResponseHeaders(entity.getHeaders());

				//Needed for determining the JSON/XML building
				response.setResponseClass(OffsetResponse.class);
				response.setModelClass(Offset.class);
			}
			else {
				response = setDataOnNoContent(OffsetResponse.class, Offset.class, requestPath, httpEntity, entity);
			}
		}
		catch (HttpClientErrorException e) {
			e.printStackTrace();
			response = setDataOnError(OffsetResponse.class, Offset.class, requestPath, httpEntity, e);
		}
		return response;
	}

	/* ON REQUEST ERROR */
	private <R extends IResponse<M>, M extends Model> R setDataOnNoContent(Class<R> responseClass, Class<M> modelClass, String requestPath, HttpEntity<?> httpEntity, ResponseEntity<?> entity) {
		R response = null;
		try {
			response = responseClass.getDeclaredConstructor().newInstance();
			response.setData(modelClass.getDeclaredConstructor().newInstance());
			response.setRequestPath(requestPath);
			response.setRequestHeaders(httpEntity.getHeaders());
			response.setResponseHeaders(entity.getHeaders());
			response.setResponseStatusText(entity.getStatusCode().getReasonPhrase());
			response.setResponseStatus(entity.getStatusCode());

			//Needed for determining the JSON/XML building
			response.setResponseClass(responseClass);
			response.setModelClass(modelClass);
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return response;
	}

	private <R extends IResponse<M>, M extends Model> R setDataOnError(Class<R> responseClass, Class<M> modelClass, String requestPath, HttpEntity<?> httpEntity, HttpClientErrorException exception) {
		R response = null;
		try {
			response = responseClass.getDeclaredConstructor().newInstance();
			response.setData(modelClass.getDeclaredConstructor().newInstance());
			response.setRequestPath(requestPath);
			response.setRequestHeaders(httpEntity.getHeaders());
			response.setResponseHeaders(exception.getResponseHeaders());
			response.setResponseStatusText(exception.getStatusText());
			response.setResponseStatus(exception.getStatusCode());

			//Needed for determining the JSON/XML building
			response.setResponseClass(responseClass);
			response.setModelClass(modelClass);

		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return response;
	}

	private <R extends IResponse<M>, M extends Model> R setDataOnServerDown(Class<R> responseClass, Class<M> modelClass, String requestPath, HttpEntity<?> httpEntity, ResourceAccessException exception) {
		R response = null;
		try {
			response = responseClass.getDeclaredConstructor().newInstance();
			response.setData(modelClass.getDeclaredConstructor().newInstance());
			response.setRequestPath(requestPath);
			response.setRequestHeaders(httpEntity.getHeaders());
			response.setResponseHeaders(new HttpHeaders());
			response.setResponseStatusText(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
			response.setResponseStatus(HttpStatus.SERVICE_UNAVAILABLE);

			//Needed for determining the JSON/XML building
			response.setResponseClass(responseClass);
			response.setModelClass(modelClass);

		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return response;
	}


	// GET URL
	private String getRequestPath(Request request) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint.getHref());
		if(!request.request().path().getServicePathType().equals(ServicePathType.OBJECT)) {
			if(!request.request().path().getServicePathType().equals(ServicePathType.PREDICATES)) {
				//If the request path is not of type Predicates, replace the single {id} parameter from the service path.
				builder.path(StringUtils.replace(request.request().path().getValue(), "{id}", request.request().ids().getIds().get(0)));
			}
			else {
				//If the request path is of type Predicates, replace the two {id} parameters, with values from the list.
				builder.path(Stream.of(
						request.request().ids().getIds().get(0),
						request.request().ids().getIds().get(1)
						).reduce(request.request().path().getValue(), (id1, id2) -> id1.replaceFirst("\\{([id}]+)}", id2))
				);
			}
		}
		else {
			//If the request path has no {id} parameters, don't do anything special.
			builder.path(request.request().path().getValue());
		}

		if(request.hasPaging()) {
			builder.queryParam("limit", request.with().paging().getLimit());
			builder.queryParam("offset", request.with().paging().getOffset());
		}

		if(request.hasSorting()) {
			builder.queryParam("sort", request.with().sorting().getField().getValue());
			builder.queryParam("orderBy", request.with().sorting().getOrderBy().getValue());
		}

		if(request.hasFieldSelection()) {
			List<String> fields = request.with().fieldSelection().getFields().stream().map(IField::getValue).collect(Collectors.toList());
			builder.queryParam("fields", String.join(",", fields));
		}

		if(request.hasFiltering()) {
			Filter filter1 = request.with().filtering().getFilters().get(0);
			if(request.with().filtering().getLogicalOperation() != LogicalOperation.NONE) {
				Filter filter2 = request.with().filtering().getFilters().get(1);
				builder.queryParam("filter",
								filter1.getField().getValue() + filter1.getPredicate().getValue() + "'" + filter1.getValue() + "'" +
										request.with().filtering().getLogicalOperation().getValue() +
										filter2.getField().getValue() + filter2.getPredicate().getValue() + "'" + filter2.getValue() + "'"
				);
			}
			else {
				builder.queryParam("filter", filter1.getField().getValue() + filter1.getPredicate().getValue() + "'" + filter1.getValue() + "'");
			}
		}
		return builder.build().toUriString();
	}

	// GET HEADERS
	private HttpEntity<?> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer " + this.endpoint.getDecodedToken().getToken());
		return new HttpEntity<>(headers);
	}

	// GET OFFSET
	private int[] getOffsetArray(int limit, int offset, String totalRecordCount) {
		final LinkedList<Integer> list = new LinkedList<>();
		list.add(offset); //Add the first instance of what is being offset

		int totalRecords = NumberUtils.parseNumber(totalRecordCount, Integer.class);
		int currentPage = (int) Math.floor((double)offset / limit);
		int totalPages = (int) Math.ceil((double)totalRecords / limit);
		int last_offset = (totalPages - 1) * limit;

		int next_offset = 0;

		if(totalRecords != 0) {
			while(last_offset != next_offset) {
				next_offset = (currentPage + 1) * limit;
				list.add(next_offset);
				currentPage++;
			}
		}
		return list.stream().mapToInt(i->i).toArray();
	}

	private <R extends IResponse<M>, M extends Model> void verifyRequestAndResponse(Request request, Class<R> responseClass, Class<M> modelClass) {
		if(!request.request().path().getResponseClass().equals(responseClass)) {
			throw new IllegalArgumentException("ServicePath: " + request.request().path() + " requires that the response return: " + request.request().path().getResponseClass().getCanonicalName()
					+ ", however the response will return: " + responseClass.getCanonicalName());
		}
	}
}