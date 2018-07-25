package org.ricone.library.config.request;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
public interface ConfigPath {
    ServicePath getServicePath();

    String getId();

    boolean hasId();

    boolean containsRequestType(RequestType requestType);

    boolean isServicePathType(ServicePathType servicePathType);
}
