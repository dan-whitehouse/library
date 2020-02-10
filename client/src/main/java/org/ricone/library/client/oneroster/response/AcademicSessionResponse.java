package org.ricone.library.client.oneroster.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.library.client.oneroster.response.BaseSingleResponse;
import org.ricone.library.client.oneroster.response.model.AcademicSession;
import org.ricone.library.client.oneroster.response.model.Error;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-31
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","academicSession"})
public class AcademicSessionResponse extends BaseSingleResponse<AcademicSession> implements Serializable {
	private final static long serialVersionUID = 254536018508141411L;

	public AcademicSessionResponse() {
	}

	public AcademicSessionResponse(AcademicSession academicSession) {
		super(academicSession);
	}

	public AcademicSessionResponse(AcademicSession academicSession, List<Error> errors) {
		super(academicSession, errors);
	}

	@JsonProperty("academicSession")
	@Override public AcademicSession getData() {
		return super.getData();
	}

	@JsonProperty("academicSession")
	@Override public void setData(AcademicSession academicSession) {
		super.setData(academicSession);
	}
}