package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.ricone.library.client.oneroster.response.BaseMultiResponse;
import org.ricone.library.client.oneroster.response.model.Error;
import org.ricone.library.client.oneroster.response.model.Users;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-31
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","users"})
@JsonRootName("users")
public class UsersResponse extends BaseMultiResponse<Users> implements Serializable {
	private final static long serialVersionUID = -2820152702413553355L;

	public UsersResponse() { }

	public UsersResponse(Users users) {
		super(users);
	}

	public UsersResponse(Users users, List<Error> errors) {
		super(users, errors);
	}

	@JsonUnwrapped @JsonProperty("users")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "user")
	@Override public Users getData() {
		return super.getData();
	}

	@JsonUnwrapped @JsonProperty("users")
	@JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "user")
	@Override public void setData(Users users) {
		super.setData(users);
	}
}