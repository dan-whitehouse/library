package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.ricone.library.client.core.IResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public abstract class XResponse<M> implements IResponse<M> {
	private Class<? extends IResponse<M>> responseClass;
	private Class<M> modelClass;

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
