package org.ricone.library.client.request;

import org.ricone.library.exception.InvalidPathException;
import org.ricone.library.exception.MissingArgumentException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class XRequestChangesSinceBuilder extends PathVerifier {
	private ServicePath servicePath;
	private PagingInfo pagingInfo;
	private ChangesSince changesSince;

	public XRequestChangesSinceBuilder(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	public XRequestChangesSinceBuilder pagingInfo(Integer pageNumber, Integer pageSize) {
		this.pagingInfo = new PagingInfo(pageNumber, pageSize);
		return this;
	}

	public XRequestChangesSinceBuilder changesSince(LocalDateTime opaqueMarker) {
		this.changesSince = new ChangesSince(opaqueMarker);
		return this;
	}

	public XRequest build() throws InvalidPathException, MissingArgumentException, IllegalArgumentException {
		XRequest request = new XPressRequest();
		request.setServicePath(this.servicePath);
		request.setPagingInfo(this.pagingInfo);
		request.setChangesSince(this.changesSince);

		if(isInvalidPath(request)) {
			List<String> xPressRequestTypeValues = servicePath.getXPressRequestTypes().stream().map(RequestType::getValue).collect(Collectors.toList());
			throw new InvalidPathException(servicePath + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
		}

		if(isMissingChangesSince(request)) {
			throw new MissingArgumentException(servicePath + " requires the changesSince method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}
		return request;
	}

	@Override
	boolean isInvalidPath(XRequest request) {
		return !request.containsRequestType(RequestType.CHANGES_SINCE);
	}

	@Override
	boolean isMissingChangesSince(XRequest request) {
		return !request.hasChangesSince();
	}
}