package org.ricone.library.client.xpress.request;

import org.ricone.library.exception.InvalidPathException;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-31
 */

public class XRequest {
	private XRequestBuilder request;
	private XWithBuilder with;

	public static Builder builder() {
		return new Builder();
	}

	private XRequest() {
	}

	XRequestBuilder getRequest() {
		return request;
	}

	XWithBuilder getWith() {
		return with;
	}

	public static class Builder {
		private XRequest instance = new XRequest();

		public XRequestBuilder.Builder request() {
			Consumer<XRequestBuilder> f = obj -> { instance.request = obj;};
			return new XRequestBuilder.Builder(this, f);
		}

		public XWithBuilder.Builder with() {
			Consumer<XWithBuilder> f = obj -> { instance.with = obj;};
			return new XWithBuilder.Builder(this, f);
		}

		public XRequest build() throws InvalidPathException {
			validate();
			return instance;
		}

		private void validate() throws InvalidPathException {
			if(isInvalidPath()) {
				List<String> xPressRequestTypeValues = instance.request.getPath().getXPressRequestTypes().stream().map(RequestType::getValue).collect(Collectors.toList());
				throw new InvalidPathException("ServicePath: " + instance.request.getPath() + " does not work with " + this.getClass().getCanonicalName() + ". Try a different ServicePath or use one of the following classes: " + String.join(", ", xPressRequestTypeValues));
			}

			//Id Conflicts
			if(isMissingId()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.getPath() + " isMissingId");
			}

			if(hasIdButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.getPath() + " hasIdButShouldNot");
			}

			if(hasIdButWrongType()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.getPath() + " hasIdButWrongType ");
			}

			//Paging Conflicts
			if(hasPagingButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.getPath() + " hasPagingButShouldNot");
			}

			//ChangesSince Conflicts
			if(hasChangesSinceButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.getPath() + " hasChangesSinceButShouldNot");
			}

			if(hasChangesSinceAndAUPP()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.getPath() + " hasChangesSinceAndAUPP");
			}

			if(hasSchoolYearButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.getPath() + " hasSchoolYearButShouldNot");
			}

			//AUPP
			if(hasAUPPButShouldNot()) {
				throw new IllegalArgumentException("ServicePath: " + instance.request.getPath() + " hasAUPPButShouldNot");
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
			return (hasAUPP() && !instance.request.getPath().equals(ServicePath.GET_XSTAFFS_BY_XSCHOOL_REFID)) || (hasAUPP() && !instance.request.getPath().equals(ServicePath.GET_XSTUDENTS_BY_XSCHOOL_REFID));
		}

		boolean hasSchoolYearButShouldNot() {
			return hasSchoolYear() && hasChangesSince();
		}


		//Validation Helpers
		boolean hasId() {
			if(instance.request.getId() == null) {
				return false;
			}
			return StringUtils.hasText(instance.request.getId());
		}

		boolean hasIdType(IdType idType) {
			if(instance.request.getId() == null) {
				return false;
			}
			return instance.request.getIdType().equals(idType);
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
			return instance.request.getPath().getXPressRequestTypes().contains(requestType);
		}

		boolean isServicePathType(ServicePathType servicePathType) {
			if(instance.getRequest() == null || instance.request.getPath() == null) {
				return false;
			}
			return instance.request.getPath().getServicePathType().equals(servicePathType);
		}
	}

	boolean hasId() {
		if(getRequest() == null) {
			return false;
		}
		return StringUtils.hasText(getRequest().getId());
	}

	boolean hasIdType() {
		if(getRequest() == null) {
			return false;
		}
		return getRequest().getIdType() != null;
	}

	boolean hasPaging() {
		if(getWith() == null) {
			return false;
		}
		return getWith().paging() != null;
	}

	boolean hasSchoolYear() {
		if(getWith() == null) {
			return false;
		}
		return getWith().schoolYear() != null;
	}

	boolean hasAUPP() {
		if(getWith() == null) {
			return false;
		}
		return getWith().isAccountProvisioning();
	}

	boolean hasChangesSince() {
		if(getWith() == null) {
			return false;
		}
		return getWith().getChangesSince() != null;
	}
}
