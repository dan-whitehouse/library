package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class ResponseModel {

    @JsonIgnore
    public boolean isEmpty() {
        return true;
    }

    @JsonIgnore
    public boolean hasData() {
        return !isEmpty();
    }
}
