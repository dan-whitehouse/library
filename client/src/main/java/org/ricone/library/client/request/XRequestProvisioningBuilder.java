package org.ricone.library.client.request;

import org.ricone.library.exception.InvalidPathException;
import org.ricone.library.exception.MissingArgumentException;

import java.util.List;
import java.util.stream.Collectors;

public class XRequestProvisioningBuilder extends PathVerifier {
	private ServicePath servicePath;
	private String id;
	private PagingInfo pagingInfo;
	private AUPPType auppType;

	public XRequestProvisioningBuilder(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	public XRequestProvisioningBuilder id(String id) {
		this.id = id;
		return this;
	}

	public XRequestProvisioningBuilder pagingInfo(Integer pageNumber, Integer pageSize) {
		this.pagingInfo = new PagingInfo(pageNumber, pageSize);
		return this;
	}

	public XRequestProvisioningBuilder auppType(AUPPType auppType) {
		this.auppType = auppType;
		return this;
	}

	public XRequest build() throws InvalidPathException, MissingArgumentException, IllegalArgumentException {
		XRequest request = new XPressRequest();
		request.setServicePath(this.servicePath);
		request.setId(this.id);
		request.setPagingInfo(this.pagingInfo);
		request.setAuppType(this.auppType);

		if(isInvalidPath(request)) {
			List<String> xPressRequestTypeValues = servicePath.getXPressRequestTypes().stream().map(RequestType::getValue).collect(Collectors.toList());
			throw new InvalidPathException(servicePath + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
		}

		if(isMissingId(request)) {
			throw new MissingArgumentException(servicePath + " requires the refId method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}

		if(isMissingAUPPType(request)) {
			throw new MissingArgumentException(servicePath + " requires the auppType method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}

		return request;
	}

	@Override
	boolean isInvalidPath(XRequest request) {
		return !request.containsRequestType(RequestType.AUPP);
	}

	@Override
	boolean isMissingId(XRequest request) {
		return !request.isServicePathType(ServicePathType.OBJECT) && !request.hasId();
	}

	@Override
	boolean isMissingAUPPType(XRequest request) {
		return !request.hasAUPP();
	}
}