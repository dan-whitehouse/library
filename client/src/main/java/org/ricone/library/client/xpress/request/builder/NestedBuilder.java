package org.ricone.library.client.xpress.request.builder;

import org.ricone.library.client.xpress.request.*;
import org.ricone.library.exception.InvalidPathException;
import org.ricone.library.exception.MissingArgumentException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class NestedBuilder {
	private ServicePath servicePath;

	public NestedBuilder(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	/* Builder Access Methods */
	public Builder builder() {
		return new Builder(servicePath);
	}

	public IdBuilder idBuilder() {
		return new IdBuilder(servicePath);
	}

	public ChangesSinceBuilder changesSinceBuilder() {
		return new ChangesSinceBuilder(servicePath);
	}

	public ProvisioningBuilder auppBuilder() {
		return new ProvisioningBuilder(servicePath);
	}

	public LastPageBuilder lastPageBuilder() {
		return new LastPageBuilder(servicePath);
	}

	/* Inner Classes */
	public static final class Builder extends XBuilder {
		private ServicePath servicePath;
		private String id;
		private Integer schoolYear;
		private PagingInfo pagingInfo;

		Builder(ServicePath servicePath) {
			this.servicePath = servicePath;
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

	public static final class LastPageBuilder extends XBuilder {
		private ServicePath servicePath;
		private String id;
		private Integer schoolYear;
		private PagingInfo pagingInfo;

		LastPageBuilder(ServicePath servicePath) {
			this.servicePath = servicePath;
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

	public static final class ProvisioningBuilder extends XBuilder {
		private ServicePath servicePath;
		private String id;
		private PagingInfo pagingInfo;
		private AUPPType auppType;

		ProvisioningBuilder(ServicePath servicePath) {
			this.servicePath = servicePath;
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

		IdBuilder(ServicePath servicePath) {
			this.servicePath = servicePath;
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
		private PagingInfo pagingInfo;
		private ChangesSince changesSince;

		public ChangesSinceBuilder(ServicePath servicePath) {
			this.servicePath = servicePath;
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


		boolean isInvalidPath(XRequest request) {
			return !request.containsRequestType(RequestType.CHANGES_SINCE);
		}


		boolean isMissingChangesSince(XRequest request) {
			return !request.hasChangesSince();
		}
	}
}
