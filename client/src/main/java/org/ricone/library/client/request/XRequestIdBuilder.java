package org.ricone.library.client.request;

import org.ricone.library.exception.InvalidPathException;
import org.ricone.library.exception.MissingArgumentException;

import java.util.List;
import java.util.stream.Collectors;

public class XRequestIdBuilder extends PathVerifier {
	private ServicePath servicePath;
	private String id;
	private IdType idType;
	private Integer schoolYear;

	public XRequestIdBuilder(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	public XRequestIdBuilder id(String id) {
		this.id = id;
		return this;
	}

	public XRequestIdBuilder schoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
		return this;
	}

	public XRequestIdBuilder idType(IdType idType) {
		this.idType = idType;
		return this;
	}

	public XRequest build() throws InvalidPathException, MissingArgumentException, IllegalArgumentException {
		XRequest request = new XPressRequest();
		request.setServicePath(this.servicePath);
		request.setId(this.id);
		request.setIdType(this.idType);
		request.setSchoolYear(this.schoolYear);

		if(isInvalidPath(request)) {
			List<String> xPressRequestTypeValues = servicePath.getXPressRequestTypes().stream().map(RequestType::getValue).collect(Collectors.toList());
			throw new InvalidPathException(servicePath + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
		}

		if(isMissingId(request)) {
			throw new MissingArgumentException(servicePath + " requires the id method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}

		if(isMissingIdType(request)) {
			throw new MissingArgumentException(servicePath + " requires the idType method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}
		return request;
	}

	@Override
	boolean isInvalidPath(XRequest request) {
		return !request.containsRequestType(RequestType.ID);
	}

	@Override
	boolean isMissingId(XRequest request) {
		return !request.isServicePathType(ServicePathType.OBJECT) && !request.hasId();
	}

	@Override
	boolean isMissingIdType(XRequest request) {
		return !request.hasIdType();
	}
}