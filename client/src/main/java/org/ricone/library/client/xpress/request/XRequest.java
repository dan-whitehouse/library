package org.ricone.library.client.xpress.request;

import org.ricone.library.exception.InvalidPathException;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class XRequest {
	private XConfigurationBuilder configuration;
	private XRequestBuilder request;
	private XWithBuilder with;

	public static Builder builder() {
		return new Builder();
	}
	private XRequest() {
		configuration = new XConfigurationBuilder();
	}

	XConfigurationBuilder configuration() {
		return configuration;
	}
	XRequestBuilder request() {
		return request;
	}
	XWithBuilder with() {
		return with;
	}

	public static class Builder {
		private XRequest instance = new XRequest();

		public XConfigurationBuilder.Builder configuration() {
			Consumer<XConfigurationBuilder> f = obj -> { instance.configuration = obj;};
			return new XConfigurationBuilder.Builder(this, f);
		}

		public XRequestBuilder.Builder request() {
			Consumer<XRequestBuilder> f = obj -> { instance.request = obj;};
			return new XRequestBuilder.Builder(this, f);
		}

		public XWithBuilder.Builder with() {
			Consumer<XWithBuilder> f = obj -> { instance.with = obj;};
			return new XWithBuilder.Builder(this, f);
		}

		public XRequest build() throws InvalidPathException {
			//validate();
			return instance;
		}

		private void validate() throws InvalidPathException {
			if(isInvalidPath()) {
				List<String> xPressRequestTypeValues = instance.request.path().getXPressRequestTypes().stream().map(RequestType::getValue).collect(Collectors.toList());
				throw new InvalidPathException("ServicePath: " + instance.request.path() + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
			}

			//Id Conflicts
			if(isMissingId()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.path() + " isMissingId");
			}

			if(hasIdButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.path() + " hasIdButShouldNot");
			}

			if(hasIdButWrongType()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.path() + " hasIdButWrongType ");
			}

			//Paging Conflicts
			if(hasPagingButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.path() + " hasPagingButShouldNot");
			}

			//ChangesSince Conflicts
			if(hasChangesSinceButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.path() + " hasChangesSinceButShouldNot");
			}

			if(hasChangesSinceAndAUPP()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.path() + " hasChangesSinceAndAUPP");
			}

			if(hasSchoolYearButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.path() + " hasSchoolYearButShouldNot");
			}

			//AUPP
			if(hasAUPPButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.path() + " hasAUPPButShouldNot");
			}
		}

		//Validation
		boolean isInvalidPath() {
			return !containsRequestType(RequestType.BASIC);
		}

		boolean isMissingId() {
			return (isServicePathType(ServicePathType.SINGLE) && !hasId()) || (isServicePathType(ServicePathType.PREDICATE) && !hasId());
		}

		boolean hasIdButWrongType() {
			return (isServicePathType(ServicePathType.SINGLE) && containsRequestType(RequestType.ID) && hasIdType(IdType.RefId)) || (isServicePathType(ServicePathType.PREDICATE) && !hasIdType(IdType.RefId));
		}

		boolean hasIdButShouldNot() {
			return isServicePathType(ServicePathType.OBJECT) && hasId();
		}

		boolean hasPagingButShouldNot() {
			return isServicePathType(ServicePathType.SINGLE) && hasPaging();
		}

		boolean hasChangesSinceButShouldNot() {
			return isServicePathType(ServicePathType.SINGLE) && hasChangesSince();
		}

		boolean hasChangesSinceAndAUPP() {
			return hasAUPP() && hasChangesSince();
		}

		boolean hasAUPPButShouldNot() {
			return (hasAUPP() && !instance.request.path().equals(ServicePath.GET_XSTAFFS_BY_XSCHOOL_REFID)) || (hasAUPP() && !instance.request.path().equals(ServicePath.GET_XSTUDENTS_BY_XSCHOOL_REFID));
		}

		boolean hasSchoolYearButShouldNot() {
			return hasSchoolYear() && hasChangesSince();
		}


		//Validation Helpers
		boolean hasId() {
			if(instance.request.id() == null) {
				return false;
			}
			return StringUtils.hasText(instance.request.id());
		}

		boolean hasIdType(IdType idType) {
			if(instance.request.id() == null) {
				return false;
			}
			return instance.request.idType().equals(idType);
		}

		boolean hasPaging() {
			if(instance.with == null) {
				return false;
			}
			return instance.with.paging() != null;
		}

		boolean hasSchoolYear() {
			if(instance.with == null) {
				return false;
			}
			return instance.with.schoolYear() != null;
		}

		boolean hasChangesSince() {
			if(instance.with == null || instance.with.getChangesSince() == null) {
				return false;
			}
			return instance.with.getChangesSince().getOpaqueMarker() != null;
		}

		boolean hasAUPP() {
			if(instance.with == null) {
				return false;
			}
			return instance.with.isAccountProvisioning();
		}

		boolean containsRequestType(RequestType requestType) {
			if(instance.request == null) {
				return false;
			}
			return instance.request.path().getXPressRequestTypes().contains(requestType);
		}

		boolean isServicePathType(ServicePathType servicePathType) {
			if(instance.request() == null || instance.request.path() == null) {
				return false;
			}
			return instance.request.path().getServicePathType().equals(servicePathType);
		}
	}

	boolean hasId() {
		if(request() == null) {
			return false;
		}
		return StringUtils.hasText(request().id());
	}

	boolean hasIdType() {
		if(request() == null) {
			return false;
		}
		return request().idType() != null;
	}

	boolean hasPaging() {
		if(with() == null) {
			return false;
		}
		return with().paging() != null;
	}

	boolean hasSchoolYear() {
		if(with() == null) {
			return false;
		}
		return with().schoolYear() != null;
	}

	boolean hasAUPP() {
		if(with() == null) {
			return false;
		}
		return with().isAccountProvisioning();
	}

	boolean hasChangesSince() {
		if(with() == null) {
			return false;
		}
		return with().getChangesSince() != null;
	}
}
