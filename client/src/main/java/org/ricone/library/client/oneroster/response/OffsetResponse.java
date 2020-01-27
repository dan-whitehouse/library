package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Iterator;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OffsetResponse extends Response<int[]> implements Iterator<Integer>  {
    private int current = 0;
    private int[] offsets;
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

    public OffsetResponse(int[] offsets) {
        this.offsets = offsets;
    }

    @Override
    public boolean hasNext() {
        return (current < offsets.length);
    }

    @Override
    public Integer next() {
        return offsets[current++];
    }

    @Override
    public int[] getData() {
        return offsets;
    }

    @Override
    public void setData(int[] data) {
        this.offsets = data;
    }

    @Override
    public String getRequestPath() {
        return this.requestPath;
    }

    @Override
    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    @Override
    public HttpHeaders getRequestHeaders() {
        return this.requestHeaders;
    }

    @Override
    public void setRequestHeaders(HttpHeaders requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    @Override
    public HttpStatus getResponseStatus() {
        return this.responseStatus;
    }

    @Override
    public void setResponseStatus(HttpStatus status) {
        this.responseStatus = status;
    }

    @Override
    public String getResponseStatusText() {
        return this.responseStatusText;
    }

    @Override
    public void setResponseStatusText(String statusText) {
        this.responseStatusText = statusText;
    }

    @Override
    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    @Override
    public void setResponseHeaders(HttpHeaders responseHeaders) {
        this.responseHeaders = responseHeaders;
    }
}
