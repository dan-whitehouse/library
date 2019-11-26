package org.ricone.library.client.request;

import java.util.List;
import java.util.stream.Collectors;
import org.ricone.library.exception.MissingArgumentException;
import org.ricone.library.exception.InvalidPathException;

public class XRequestBuilder extends PathVerifier {
	private ServicePath servicePath;
	private String id;
	private Integer schoolYear;
	private PagingInfo pagingInfo;

	public XRequestBuilder(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	public XRequestBuilder id(String id) {
		this.id = id;
		return this;
	}

	public XRequestBuilder schoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
		return this;
	}

	public XRequestBuilder pagingInfo(Integer pageNumber, Integer pageSize) {
		this.pagingInfo = new PagingInfo(pageNumber, pageSize);
		return this;
	}

	public XRequest build() throws InvalidPathException, MissingArgumentException, IllegalArgumentException {
		XRequest request = new XPressRequest();
		request.setServicePath(this.servicePath);
		request.setPagingInfo(this.pagingInfo);
		request.setId(this.id);
		request.setSchoolYear(this.schoolYear);

		if(isInvalidPath(request)) {
			List<String> xPressRequestTypeValues = servicePath.getXPressRequestTypes().stream().map(RequestType::getValue).collect(Collectors.toList());
			throw new InvalidPathException("ServicePath: " + servicePath + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
		}

		if(isMissingId(request)) {
			throw new MissingArgumentException("ServicePath: " + servicePath + " requires the id method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}
		return request;
	}

	@Override
	boolean isInvalidPath(XRequest request) {
		return !request.containsRequestType(RequestType.BASIC);
	}

	@Override
	boolean isMissingId(XRequest request) {
		return  (request.isServicePathType(ServicePathType.SINGLE) && !request.hasId()) ||
				(request.isServicePathType(ServicePathType.PREDICATE) && !request.hasId()) ;
	}
}