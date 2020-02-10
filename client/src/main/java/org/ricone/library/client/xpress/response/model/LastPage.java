package org.ricone.library.client.xpress.response.model;

import org.ricone.library.client.core.Model;

public class LastPage extends Model {
    Integer lastPage;

    public LastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }
}
