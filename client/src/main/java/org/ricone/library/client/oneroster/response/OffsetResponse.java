package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.ricone.library.client.oneroster.response.model.Offset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Iterator;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-02-05
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OffsetResponse extends Response<Offset> implements Iterator<Integer>  {
    private Offset offset;
    private int current = 0;

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

    public OffsetResponse(int[] data) {
        this.offset = new Offset(data);
    }

    @Override
    public Offset getData() {
        return offset;
    }

    @Override
    public void setData(Offset offset) {
        this.offset = offset;
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

    @Override
    public boolean hasNext() {
        return (current < offset.getOffsets().length);
    }

    @Override
    public Integer next() {
        return offset.getOffsets()[current++];
    }
}
