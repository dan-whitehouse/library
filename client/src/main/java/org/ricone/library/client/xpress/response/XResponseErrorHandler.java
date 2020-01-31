package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.ricone.library.client.xpress.response.model.XErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class XResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == CLIENT_ERROR || httpResponse.getStatusCode().series() == SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		String responseBody = IOUtils.toString(httpResponse.getBody(), UTF_8);
		XErrorResponse response = getErrorResponse(responseBody);

		if(response != null && response.getError() != null) {
			throw new HttpClientErrorException(
				HttpStatus.valueOf(response.getError().getCode()),
				response.getError().getMessage() + " - " + response.getError().getDescription(),
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

	private XErrorResponse getErrorResponse(String body) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(mapper);

		try {
			return mapper.readValue(body, XErrorResponse.class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}