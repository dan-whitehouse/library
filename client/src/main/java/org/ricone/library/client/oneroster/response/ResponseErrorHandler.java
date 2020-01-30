package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.ricone.library.client.oneroster.response.model.Error;
import org.ricone.library.client.oneroster.response.model.ErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-29
 */

public class ResponseErrorHandler implements org.springframework.web.client.ResponseErrorHandler {
	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == CLIENT_ERROR || httpResponse.getStatusCode().series() == SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		String responseBody = IOUtils.toString(httpResponse.getBody(), UTF_8);
		ErrorsResponse response = getErrorResponse(responseBody);

		if(response != null && response.getErrors() != null) {
			throw new HttpClientErrorException(
				HttpStatus.valueOf(getStatusCode(response)),
				getStatusText(response),
				httpResponse.getHeaders(),
				httpResponse.getBody().readAllBytes(),
				UTF_8
			);
		}
		else {
			throw new HttpClientErrorException(
				httpResponse.getStatusCode(),
				httpResponse.getStatusText(),
				httpResponse.getHeaders(),
				httpResponse.getBody().readAllBytes(),
				UTF_8
			);
		}
	}

	private int getStatusCode(ErrorsResponse response) {
		for(Error error : response.getErrors().getErrors()) {
			switch(error.getCodeMinor()) {
				case invalid_data:
				case invalid_filter_field:
				case invalid_blank_selection_field:
					return 400;
				case unauthorized:
					return 401;
				case forbidden:
					return 403;
				case unknown_object:
					return 404;
				default:
					return 500;
			}
		}
		return 0;
	}

	private String getStatusText(ErrorsResponse response) {
		for(Error error : response.getErrors().getErrors()) {
			return error.getDescription(); // + " [" + error + "]"
		}
		return "";
	}

	private ErrorsResponse getErrorResponse(String body) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(mapper);

		try {
			return mapper.readValue(body, ErrorsResponse.class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}