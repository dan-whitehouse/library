package org.ricone.library.config.request2;

import org.springframework.http.HttpMethod;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
public interface ConfigPath {
    ServicePath getServicePath();

    HttpMethod getHttpMethod();

    Object getBody();

    String getId();

    String getPredicateId();

    boolean hasId();

    boolean hasHttpMethod();

    boolean hasBody();

    boolean hasPredicateId();

    boolean isServicePathType(ServicePathType servicePathType);

    boolean isHttpMethodType(HttpMethod httpMethod);

    boolean containsHttpMethodType(HttpMethod httpMethod);
}
