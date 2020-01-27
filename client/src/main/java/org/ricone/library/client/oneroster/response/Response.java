package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public abstract class Response<T> {
	private Class<T> clazz;

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public abstract T getData();

	public abstract void setData(T data);

	public abstract String getRequestPath();

	public abstract void setRequestPath(String requestPath);

	public abstract HttpHeaders getRequestHeaders();

	public abstract void setRequestHeaders(HttpHeaders requestHeaders);

	public abstract HttpStatus getResponseStatus();

	public abstract void setResponseStatus(HttpStatus status);

	public abstract String getResponseStatusText();

	public abstract void setResponseStatusText(String statusText);

	public abstract HttpHeaders getResponseHeaders();

	public abstract void setResponseHeaders(HttpHeaders responseHeaders);

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

			//TODO: Dates DateTimes are screwy

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

	public String getXML() {
		if(getData() != null) {
			XmlMapper mapper = new XmlMapper();
			mapper.registerModule(new Jdk8Module());
			mapper.registerModule(new JavaTimeModule());
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

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
