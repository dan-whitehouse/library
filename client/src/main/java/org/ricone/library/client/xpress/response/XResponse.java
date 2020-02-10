package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Model;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public abstract class XResponse<M extends Model> implements IResponse<M> {
	private Class<? extends IResponse<M>> responseClass;
	private Class<M> modelClass;
	@JsonIgnore
	private String requestPath;
	@JsonIgnore
	private HttpHeaders requestHeaders;
	@JsonIgnore
	private HttpStatus responseStatus;
	@JsonIgnore
	private String responseStatusText;
	@JsonIgnore
	private HttpHeaders responseHeaders;

	@Override
	public void setResponseClass(Class<? extends IResponse<M>> clazz) {
		this.responseClass = clazz;
	}

	@Override
	public void setModelClass(Class<M> clazz) {
		this.modelClass = clazz;
	}

	@Override
	public abstract M getData();

	@Override
	public abstract void setData(M data);

	@Override
	public String getRequestPath() {
		return requestPath;
	}

	@Override
	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	@Override
	public HttpHeaders getRequestHeaders() {
		return requestHeaders;
	}

	@Override
	public void setRequestHeaders(HttpHeaders requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	@Override
	public HttpStatus getResponseStatus() {
		return responseStatus;
	}

	@Override
	public void setResponseStatus(HttpStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	@Override
	public String getResponseStatusText() {
		return responseStatusText;
	}

	@Override
	public void setResponseStatusText(String responseStatusText) {
		this.responseStatusText = responseStatusText;
	}

	@Override
	public HttpHeaders getResponseHeaders() {
		return responseHeaders;
	}

	@Override
	public void setResponseHeaders(HttpHeaders responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	@Override
	public String getJSON() {
		if(getData() != null) {
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
		}
		return null;
	}

	@Override
	public String getXML() {
		if(getData() != null) {
			XmlMapper mapper = new XmlMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
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
