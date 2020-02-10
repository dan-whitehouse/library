package org.ricone.library.client.core;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public abstract class BaseResponse<M> {
    private Class<? extends BaseResponse<M>> responseClass;
    private Class<M> modelClass;

    protected void setResponseClass(Class<? extends BaseResponse<M>> clazz) {
        this.responseClass = clazz;
    }
    
    protected Class<? extends BaseResponse<M>> getResponseClass() {
        return responseClass;
    }

    protected Class<M> getModelClass() {
        return modelClass;
    }

    protected void setModelClass(Class<M> clazz) {
        this.modelClass = clazz;
    }

    public abstract M getData();

    public abstract void setData(M data);

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

    public abstract String getJSON();

    public abstract String getXML();
}
