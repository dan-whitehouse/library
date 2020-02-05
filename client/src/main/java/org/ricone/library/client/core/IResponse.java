package org.ricone.library.client.core;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public interface IResponse<M> {
	void setResponseClass(Class<? extends IResponse<M>> clazz);
	void setModelClass(Class<M> clazz);

	M getData();

	void setData(M data);

	String getRequestPath();

	void setRequestPath(String requestPath);

	HttpHeaders getRequestHeaders();

	void setRequestHeaders(HttpHeaders requestHeaders);

	HttpStatus getResponseStatus();

	void setResponseStatus(HttpStatus status);

	String getResponseStatusText();

	void setResponseStatusText(String statusText);

	HttpHeaders getResponseHeaders();

	void setResponseHeaders(HttpHeaders responseHeaders);

	String getJSON();

	String getXML();
}
