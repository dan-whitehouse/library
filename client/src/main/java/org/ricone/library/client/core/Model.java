package org.ricone.library.client.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Model {

    @JsonIgnore
    public boolean isEmpty() {
        return true;
    }

    @JsonIgnore
    public boolean hasData() {
        return !isEmpty();
    }
}
