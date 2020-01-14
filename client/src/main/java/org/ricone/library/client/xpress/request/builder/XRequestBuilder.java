package org.ricone.library.client.xpress.request.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.ricone.library.client.xpress.request.*;
import org.ricone.library.exception.MissingArgumentException;
import org.ricone.library.exception.InvalidPathException;

public class XRequestBuilder extends XBuilder {
	private ServicePath servicePath;
	private String id;
	private Integer schoolYear;
	private PagingInfo pagingInfo;

	public XRequestBuilder(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	public XRequestBuilder refId(String id) {
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
		XRequest request = new XRequestImp();
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


	boolean isInvalidPath(XRequest request) {
		return !request.containsRequestType(RequestType.BASIC);
	}


	boolean isMissingId(XRequest request) {
		return  (request.isServicePathType(ServicePathType.SINGLE) && !request.hasId()) ||
				(request.isServicePathType(ServicePathType.PREDICATE) && !request.hasId()) ;
	}
}