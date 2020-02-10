/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.library.client.xpress.response;

import com.fasterxml.jackson.annotation.*;
import org.ricone.library.client.xpress.response.model.LastPage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class XLastPageResponse extends XResponse<LastPage> {
	private LastPage lastPage;

	public XLastPageResponse() {
	}

	public XLastPageResponse(Integer lastPage) {
		super();
		this.lastPage = new LastPage(lastPage);
	}

	@Override
	public LastPage getData() {
		return lastPage;
	}

	@Override
	public void setData(LastPage data) {
		this.lastPage = data;
	}

	@Override
	public String getJSON() {
		return null;
	}

	@Override
	public String getXML() {
		return null;
	}

	@Override
	public String toString() {
		return "XLastPageResponse{" + "lastPage=" + lastPage + '}';
	}
}