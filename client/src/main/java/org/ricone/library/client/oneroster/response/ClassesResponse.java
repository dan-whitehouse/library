package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.oneroster.response.BaseMultiResponse;
import org.ricone.library.client.oneroster.response.model.Classes;
import org.ricone.library.client.oneroster.response.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-31
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","classes"})
@JsonRootName("classes")
public class ClassesResponse extends BaseMultiResponse<Classes> implements Serializable {
	private final static long serialVersionUID = 5934808405475046263L;

	public ClassesResponse() { }

	public ClassesResponse(Classes classes) {
		super(classes);
	}

	public ClassesResponse(Classes classes, List<Error> errors) {
		super(classes, errors);
	}

	@JsonUnwrapped @JsonProperty("classes")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "class")
	@Override public Classes getData() {
		return super.getData();
	}

	@JsonUnwrapped @JsonProperty("classes")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "class")
	@Override public void setData(Classes classes) {
		super.setData(classes);
	}
}