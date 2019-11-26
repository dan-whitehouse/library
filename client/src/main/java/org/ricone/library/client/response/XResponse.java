package org.ricone.library.client.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public abstract class XResponse<T> {
	public abstract T getData();

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
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);

		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		try {
			return writer.writeValueAsString(getData());
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getXML() {
		XmlMapper mapper = new XmlMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			return mapper.writeValueAsString(getData());
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
