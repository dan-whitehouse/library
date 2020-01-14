package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","classes"})
public class ClassesResponse extends BaseMultiResponse<java.lang.Class> implements Serializable {
	private final static long serialVersionUID = 5934808405475046263L;

	public ClassesResponse() {
	}

	public ClassesResponse(List<java.lang.Class> _class) {
		super(_class);
	}

	public ClassesResponse(List<java.lang.Class> _class, List<java.lang.Error> errors) {
		super(_class, errors);
	}

	@JsonProperty("classes")
	@JacksonXmlElementWrapper(localName = "classes") @JacksonXmlProperty(localName = "class")
	@Override public List<java.lang.Class> getData() {
		return super.getData();
	}

	@JsonProperty("classes")
	@JacksonXmlElementWrapper(localName = "classes") @JacksonXmlProperty(localName = "class")
	@Override public void setData(List<java.lang.Class> _class) {
		super.setData(_class);
	}
}