package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.library.client.oneroster.response.BaseSingleResponse;
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
@JsonPropertyOrder({"warnings","user"})
public class UserResponse extends BaseSingleResponse<User> implements Serializable {
	private final static long serialVersionUID = -8931879378806569113L;

	public UserResponse() { }

	public UserResponse(User user) {
		super(user);
	}

	public UserResponse(User user, List<Error> errors) {
		super(user, errors);
	}

	@JsonProperty("user")
	@Override public User getData() {
		return super.getData();
	}

	@JsonProperty("user")
	@Override public void setData(User user) {
		super.setData(user);
	}
}