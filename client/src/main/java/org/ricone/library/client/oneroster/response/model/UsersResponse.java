package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","users"})
public class UsersResponse extends BaseMultiResponse<User> implements Serializable {
	private final static long serialVersionUID = -2820152702413553355L;

	public UsersResponse() {
	}

	public UsersResponse(List<User> users) {
		super(users);
	}

	public UsersResponse(List<User> users, List<java.lang.Error> errors) {
		super(users, errors);
	}

	@JsonProperty("users")
	@JacksonXmlElementWrapper(localName = "users") @JacksonXmlProperty(localName = "user")
	@Override public List<User> getData() {
		return super.getData();
	}

	@JsonProperty("users")
	@JacksonXmlElementWrapper(localName = "users") @JacksonXmlProperty(localName = "user")
	@Override public void setData(List<User> users) {
		super.setData(users);
	}
}