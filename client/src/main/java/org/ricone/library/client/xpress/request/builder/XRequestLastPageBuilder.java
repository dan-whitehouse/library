package org.ricone.library.client.xpress.request.builder;

import org.ricone.library.client.xpress.request.*;
import org.ricone.library.exception.InvalidPathException;
import org.ricone.library.exception.MissingArgumentException;

import java.util.List;
import java.util.stream.Collectors;

public class XRequestLastPageBuilder extends XBuilder {
	private ServicePath servicePath;
	private String id;
	private Integer schoolYear;
	private PagingInfo pagingInfo;

	public XRequestLastPageBuilder(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	public XRequestLastPageBuilder refId(String id) {
		this.id = id;
		return this;
	}

	public XRequestLastPageBuilder schoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
		return this;
	}

	public XRequestLastPageBuilder pageSize(Integer pageSize) {
		this.pagingInfo = new PagingInfo(1, pageSize);
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
			throw new InvalidPathException(servicePath + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
		}

		if(isMissingId(request)) {
			throw new MissingArgumentException(servicePath + " requires the refId method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}

		if(isMissingPagingInfo(request)) {
			throw new MissingArgumentException(servicePath + " requires the pagingInfo method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}
		return request;
	}


	boolean isInvalidPath(XRequest request) {
		return !request.containsRequestType(RequestType.BASIC);
	}


	boolean isMissingPagingInfo(XRequest request) {
		return !request.hasPaging();
	}


	boolean isMissingId(XRequest request) {
		return !request.isServicePathType(ServicePathType.OBJECT) && !request.hasId();
	}
}