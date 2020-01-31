package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.ricone.library.client.core.IResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public abstract class Response<T> implements IResponse<T> {
	private Class<T> clazz;

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public abstract T getData();

	@Override
	public abstract void setData(T data);

	@Override
	public abstract String getRequestPath();

	@Override
	public abstract void setRequestPath(String requestPath);

	@Override
	public abstract HttpHeaders getRequestHeaders();

	@Override
	public abstract void setRequestHeaders(HttpHeaders requestHeaders);

	@Override
	public abstract HttpStatus getResponseStatus();

	@Override
	public abstract void setResponseStatus(HttpStatus status);

	@Override
	public abstract String getResponseStatusText();

	@Override
	public abstract void setResponseStatusText(String statusText);

	@Override
	public abstract HttpHeaders getResponseHeaders();

	@Override
	public abstract void setResponseHeaders(HttpHeaders responseHeaders);

	@Override
	public String getJSON() {
		if(getData() != null) {
			final String dateFormat = "yyyy-MM-dd";
			final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new Jdk8Module());
			mapper.registerModule(new JavaTimeModule());

			if(this.clazz.getSuperclass().isAssignableFrom(BaseSingleResponse.class)) {
				//If the request is a single object, wrap it.
				mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
			}
			mapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			try {
				return writer.writeValueAsString(getData());
				//return writer.withRootName(getData().toString()).writeValueAsString(getData());
			}
			catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getXML() {
		if(getData() != null) {
			XmlMapper mapper = new XmlMapper();
			mapper.registerModule(new Jdk8Module());
			mapper.registerModule(new JavaTimeModule());
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

			//TODO: Dates DateTimes are screwy

			try {
				return mapper.writeValueAsString(getData());
			}
			catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
