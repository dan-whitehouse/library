package org.ricone.library.client.xpress.request.builder;

import org.ricone.library.client.xpress.request.*;
import org.ricone.library.exception.InvalidPathException;
import org.ricone.library.exception.MissingArgumentException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class XRequestBuilder3 {
	private ServicePath servicePath;
	private String id;
	private IdType idType;
	private Integer schoolYear;
	private PagingInfo pagingInfo;
	private AUPPType auppType;
	private ChangesSince changesSince;

	public XRequestBuilder3() {

	}

	/* Builder Access Methods */
	public Builder builder() {
		return new Builder();
	}

	public IdBuilder idBuilder() {
		return new IdBuilder();
	}

	public ChangesSinceBuilder changesSinceBuilder() {
		return new ChangesSinceBuilder();
	}

	public ProvisioningBuilder auppBuilder() {
		return new ProvisioningBuilder();
	}

	public LastPageBuilder lastPageBuilder() {
		return new LastPageBuilder();
	}

	public XRequest build() throws InvalidPathException, MissingArgumentException, IllegalArgumentException {
		XRequest request = new XRequestImp();
		request.setServicePath(this.servicePath);
		request.setId(this.id);
		request.setIdType(this.idType);
		request.setSchoolYear(this.schoolYear);
		request.setPagingInfo(this.pagingInfo);
		request.setAuppType(this.auppType);
		request.setChangesSince(changesSince);

		/*if(isInvalidPath(request)) {
			List<String> xPressRequestTypeValues = servicePath.getXPressRequestTypes().stream().map(RequestType::getValue).collect(Collectors.toList());
			throw new InvalidPathException("ServicePath: " + servicePath + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
		}

		if(hasIdButShouldNot(request)) {
			throw new IllegalArgumentException("ServicePath: " + servicePath + " requires the refId method not be set on " + this.getClass().getCanonicalName() + ". Remove the value or try a different ServicePath.");
		}

		if(isMissingId(request)) {
			throw new MissingArgumentException("ServicePath: " + servicePath + " requires the refId method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
		}*/
		return request;
	}

	/* Inner Classes */
	public static final class Builder extends XBuilder {
		private ServicePath servicePath;
		private String id;
		private Integer schoolYear;
		private PagingInfo pagingInfo;

		Builder() {

		}

		public Builder path(ServicePath servicePath) {
			this.servicePath = servicePath;
			return this;
		}

		public Builder refId(String id) {
			this.id = id;
			return this;
		}

		public Builder schoolYear(int schoolYear) {
			this.schoolYear = schoolYear;
			return this;
		}

		public Builder pagingInfo(Integer pageNumber, Integer pageSize) {
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

			if(hasIdButShouldNot(request)) {
				throw new IllegalArgumentException("ServicePath: " + servicePath + " requires the refId method not be set on " + this.getClass().getCanonicalName() + ". Remove the value or try a different ServicePath.");
			}

			if(isMissingId(request)) {
				throw new MissingArgumentException("ServicePath: " + servicePath + " requires the refId method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
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


		boolean hasIdButShouldNot(XRequest request) {
			return request.isServicePathType(ServicePathType.OBJECT) && request.hasId();
		}
	}

	public static final class LastPageBuilder extends XBuilder {
		private ServicePath servicePath;
		private String id;
		private Integer schoolYear;
		private PagingInfo pagingInfo;

		LastPageBuilder() {

		}

		public LastPageBuilder path(ServicePath servicePath) {
			this.servicePath = servicePath;
			return this;
		}

		public LastPageBuilder refId(String id) {
			this.id = id;
			return this;
		}

		public LastPageBuilder schoolYear(int schoolYear) {
			this.schoolYear = schoolYear;
			return this;
		}

		public LastPageBuilder pageSize(Integer pageSize) {
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

			if(hasIdButShouldNot(request)) {
				throw new IllegalArgumentException("ServicePath: " + servicePath + " requires the refId method not be set on " + this.getClass().getCanonicalName() + ". Remove the value or try a different ServicePath.");
			}

			if(isMissingId(request)) {
				throw new MissingArgumentException(servicePath + " requires the refId method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
			}

			if(isMissingPagingInfo(request)) {
				throw new MissingArgumentException(servicePath + " requires the pageSize method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
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

		boolean hasIdButShouldNot(XRequest request) {
			return request.isServicePathType(ServicePathType.OBJECT) && request.hasId();
		}
	}

	public static final class ProvisioningBuilder extends XBuilder {
		private ServicePath servicePath;
		private String id;
		private PagingInfo pagingInfo;
		private AUPPType auppType;

		ProvisioningBuilder() {

		}

		public ProvisioningBuilder path(ServicePath servicePath) {
			this.servicePath = servicePath;
			return this;
		}

		public ProvisioningBuilder refId(String id) {
			this.id = id;
			return this;
		}

		public ProvisioningBuilder pagingInfo(Integer pageNumber, Integer pageSize) {
			this.pagingInfo = new PagingInfo(pageNumber, pageSize);
			return this;
		}

		public ProvisioningBuilder auppType(AUPPType auppType) {
			this.auppType = auppType;
			return this;
		}

		public XRequest build() throws InvalidPathException, MissingArgumentException, IllegalArgumentException {
			XRequest request = new XRequestImp();
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

		boolean isInvalidPath(XRequest request) {
			return !request.containsRequestType(RequestType.AUPP);
		}

		boolean isMissingId(XRequest request) {
			return !request.isServicePathType(ServicePathType.OBJECT) && !request.hasId();
		}

		boolean isMissingAUPPType(XRequest request) {
			return !request.hasAUPP();
		}
	}

	public static final class IdBuilder extends XBuilder {
		private ServicePath servicePath;
		private String id;
		private IdType idType;
		private Integer schoolYear;

		IdBuilder() {

		}

		public IdBuilder path(ServicePath servicePath) {
			this.servicePath = servicePath;
			return this;
		}

		public IdBuilder id(String id) {
			this.id = id;
			return this;
		}

		public IdBuilder schoolYear(int schoolYear) {
			this.schoolYear = schoolYear;
			return this;
		}

		public IdBuilder idType(IdType idType) {
			this.idType = idType;
			return this;
		}

		public XRequest build() throws InvalidPathException, MissingArgumentException, IllegalArgumentException {
			XRequest request = new XRequestImp();
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


		boolean isInvalidPath(XRequest request) {
			return !request.containsRequestType(RequestType.ID);
		}


		boolean isMissingId(XRequest request) {
			return !request.isServicePathType(ServicePathType.OBJECT) && !request.hasId();
		}


		boolean isMissingIdType(XRequest request) {
			return !request.hasIdType();
		}
	}

	public static final class ChangesSinceBuilder extends XBuilder {
		private ServicePath servicePath;
		private String id;
		private PagingInfo pagingInfo;
		private ChangesSince changesSince;

		ChangesSinceBuilder() {

		}

		public ChangesSinceBuilder path(ServicePath servicePath) {
			this.servicePath = servicePath;
			return this;
		}

		public ChangesSinceBuilder refId(String id) {
			this.id = id;
			return this;
		}

		public ChangesSinceBuilder pagingInfo(Integer pageNumber, Integer pageSize) {
			this.pagingInfo = new PagingInfo(pageNumber, pageSize);
			return this;
		}

		public ChangesSinceBuilder changesSince(LocalDateTime opaqueMarker) {
			this.changesSince = new ChangesSince(opaqueMarker);
			return this;
		}

		public XRequest build() throws InvalidPathException, MissingArgumentException, IllegalArgumentException {
			XRequest request = new XRequestImp();
			request.setServicePath(this.servicePath);
			request.setId(this.id);
			request.setPagingInfo(this.pagingInfo);
			request.setChangesSince(this.changesSince);

			if(isInvalidPath(request)) {
				List<String> xPressRequestTypeValues = servicePath.getXPressRequestTypes().stream().map(RequestType::getValue).collect(Collectors.toList());
				throw new InvalidPathException(servicePath + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
			}

			if(isMissingId(request)) {
				throw new MissingArgumentException(servicePath + " requires the refId method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
			}

			if(isMissingChangesSince(request)) {
				throw new MissingArgumentException(servicePath + " requires the changesSince method be set on " + this.getClass().getCanonicalName() + ". Set a value or try a different ServicePath.");
			}
			return request;
		}

		boolean isInvalidPath(XRequest request) {
			return !request.containsRequestType(RequestType.CHANGES_SINCE);
		}


		boolean isMissingId(XRequest request) {
			return  (request.isServicePathType(ServicePathType.SINGLE) && !request.hasId()) ||
					(request.isServicePathType(ServicePathType.PREDICATE) && !request.hasId()) ;
		}


		boolean isMissingChangesSince(XRequest request) {
			return !request.hasChangesSince();
		}
	}
}
