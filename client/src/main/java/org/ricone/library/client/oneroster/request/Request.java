package org.ricone.library.client.oneroster.request;

import org.ricone.library.exception.InvalidPathException;

import java.util.function.Consumer;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public class Request {
	private RequestBuilder request;
	private WithBuilder with;

	public static Builder builder() {
		return new Builder();
	}
	private Request() { }

	RequestBuilder request() {
		return request;
	}
	WithBuilder with() {
		return with;
	}

	public static class Builder {
		private Request instance = new Request();

		public RequestBuilder.Builder request() {
			Consumer<RequestBuilder> f = obj -> instance.request = obj;
			return new RequestBuilder.Builder(this, f);
		}

		public WithBuilder.Builder with() {
			Consumer<WithBuilder> f = obj -> instance.with = obj;
			return new WithBuilder.Builder(this, f);
		}

		public Request build() throws InvalidPathException {
			/* RequestBuilder - request builder requires request option */
			if(isMissingRequest()) {
				throw new InvalidPathException("RequestBuilder requires request() be used.");
			}

			/* RequestBuilder - request builder request requires path option */
			if(isMissingRequestPath()) {
				throw new InvalidPathException("RequestBuilder requires request().path() be set.");
			}

			/* RequestBuilder - request builder request path requires ids option */
			if(isMissingRequestIds()) {
				throw new InvalidPathException("RequestBuilder requires request().ids() be set when using the service path: " + instance.request.path());
			}

			/* RequestBuilder - request builder request path requires a single id in the ids option */
			if(isMissingRequestIdsId() || doesRequestIdsIdHaveTooManyIds()) {
				throw new InvalidPathException("RequestBuilder requires .ids().id() be set with one id() when using the service path: " + instance.request.path());
			}

			/* RequestBuilder - request builder request path requires two id options in the ids option */
			if(isMissingRequestIdsIds() || doesRequestIdsIdsHaveTooManyIds()) {
				throw new InvalidPathException("RequestBuilder requires the .ids().id() be set with two id() when using the service path: " + instance.request.path());
			}

			/* RequestBuilder - request builder request path requires two id options in the ids option */
			if(hasRequestIdsIdWhenNotNeeded()) {
				throw new InvalidPathException("RequestBuilder requires that .ids().id() not be set when using the service path: " + instance.request.path());
			}

			/* RequestBuilder - request builder with sorting requires a field option */
			if(isMissingWithSortingField()) {
				throw new InvalidPathException("RequestBuilder requires .with().sorting() be set with a field() option.");
			}

			/* RequestBuilder - request builder with sorting requires a field option */
			if(doesWithSortingFieldNotMatchRequestPathType()) {
				throw new InvalidPathException("RequestBuilder requires .sorting().field() be set with a field that matches the return type.");
			}

			/* RequestBuilder - request builder with filtering requires a filter option */
			if(isMissingWithFilteringFilter()) {
				throw new InvalidPathException("RequestBuilder requires with().filtering() be set with at least one filter option.");
			}

			if(doesWithFilteringHaveTooManyFilters()) {
				throw new InvalidPathException("RequestBuilder requires with().filtering() be set with no more than two filter options.");
			}

			if(isMissingWithFilteringLogicalOperation()) {
				throw new InvalidPathException("RequestBuilder requires with().filtering() be set with a logical operation when using two filter options.");
			}

			if(doesWithFilteringHaveUnnecessaryLogicalOperation()) {
				throw new InvalidPathException("RequestBuilder requires with().filtering() not be set with a logical operation when using a single filter option.");
			}

			/* RequestBuilder - request builder with fieldSelection requires at least one field option */
			if(isMissingWithFieldSelectionField()) {
				throw new InvalidPathException("RequestBuilder requires with().fieldSelection() be set with at least one field option.");
			}

			/* RequestBuilder - request builder with fieldSelection requires all fields match fields of the right type */
			if(doesWithFieldSelectionFieldsNotMatchRequestPathType()) {
				throw new InvalidPathException("RequestBuilder requires with().fieldSelection() fields match the correct return type.");
			}

			return instance;
		}

		/** Error Checking
		 * 	Methods that the verify the builder has been set up properly.
		 * **/
		private boolean isMissingRequest() {
			return instance.request() == null;
		}

		private boolean isMissingRequestPath() {
			return instance.request().path() == null;
		}

		private boolean isMissingRequestIds() {
			//If the service path is like {id}. Because {id} is never present in requests of type object
			if(!instance.request.path().getServicePathType().equals(ServicePathType.OBJECT))  {
				if(instance.request.ids() == null) {
					return true;
				}
				return instance.request().ids().getIds() == null;
			}
			return false;
		}

		private boolean isMissingRequestIdsId() {
			//If the service path is like {id}. Because {id} is never present in requests of type object
			ServicePathType type = instance.request.path().getServicePathType();
			if(type.equals(ServicePathType.SINGLE) || type.equals(ServicePathType.PREDICATE))  {
				return instance.request().ids().getIds().size() != 1;
			}
			return false;
		}

		private boolean isMissingRequestIdsIds() {
			//Predicates require two {id} be set
			if(instance.request.path().getServicePathType().equals(ServicePathType.PREDICATES))  {
				return instance.request().ids().getIds().size() != 2;
			}
			return false;
		}

		private boolean hasRequestIdsIdWhenNotNeeded() {
			//If the service path doesn't require ids, but the user has included them.
			ServicePathType type = instance.request().path().getServicePathType();
			if(type.equals(ServicePathType.OBJECT))  {
				if(instance.request().ids() != null) {
					return instance.request().ids().getIds().size() > 0;
				}
			}
			return false;
		}

		private boolean doesRequestIdsIdHaveTooManyIds() {
			//If the service path is like {id}. Because {id} is never present in requests of type object
			ServicePathType type = instance.request.path().getServicePathType();
			if(type.equals(ServicePathType.SINGLE) || type.equals(ServicePathType.PREDICATE))  {
				if(instance.request().ids() != null) {
					return instance.request().ids().getIds().size() > 1;
				}
			}
			return false;
		}

		private boolean doesRequestIdsIdsHaveTooManyIds() {
			//Predicates require two {id} be set
			if(instance.request.path().getServicePathType().equals(ServicePathType.PREDICATES))  {
				if(instance.request().ids() != null) {
					return instance.request().ids().getIds().size() != 2;
				}
			}
			return false;
		}

		//With - Paging
		//Paging doesn't require checking. It's set up in a way that if the paging option is set with no options (limit, offset), it will use one rosters default settings.

		//With - Sorting
		private boolean isMissingWithSortingField() {
			if(instance.with() != null && instance.with().sorting() != null) {
				return instance.with().sorting().getField() == null;
			}
			return false;
		}

		private boolean doesWithSortingFieldNotMatchRequestPathType() {
			if(instance.with() != null && instance.with().sorting() != null) {
				IField field = instance.with().sorting().getField();
				return !field.getType().equals(FieldType.All) && !field.getType().equals(instance.request().path().getFieldType());
			}
			return false;
		}

		//With - Filtering
		private boolean isMissingWithFilteringFilter() {
			if(instance.with() != null && instance.with().filtering() != null) {
				return instance.with().filtering().getFilters().size() == 0;
			}
			return false;
		}

		private boolean doesWithFilteringHaveTooManyFilters() {
			if(instance.with() != null && instance.with().filtering() != null) {
				return instance.with().filtering().getFilters().size() > 2;
			}
			return false;
		}

		private boolean isMissingWithFilteringLogicalOperation() {
			if(instance.with() != null && instance.with().filtering() != null) {
				return instance.with().filtering().getFilters().size() == 2 && instance.with().filtering().getLogicalOperation() == LogicalOperation.NONE;
			}
			return false;
		}

		private boolean doesWithFilteringHaveUnnecessaryLogicalOperation() {
			if(instance.with() != null && instance.with().filtering() != null) {
				if (instance.with().filtering().getFilters().size() == 1) {
					return instance.with().filtering().getLogicalOperation() != LogicalOperation.NONE;
				}
			}
			return false;
		}

		//With - Fields
		private boolean isMissingWithFieldSelectionField() {
			if(instance.with() != null && instance.with().fieldSelection() != null) {
				return instance.with().fieldSelection().getFields().size() == 0;
			}
			return false;
		}

		private boolean doesWithFieldSelectionFieldsNotMatchRequestPathType() {
			if(instance.with() != null && instance.with().fieldSelection() != null) {
				for (IField field : instance.with().fieldSelection().getFields()) {
					if (!field.getType().equals(FieldType.All) && !field.getType().equals(instance.request().path().getFieldType())) {
						return true;
					}
				}
			}
			return false;
		}
	}

	boolean hasPaging() {
		return with() != null && with().paging() != null;
	}

	boolean hasSorting() {
		return with() != null && with().sorting() != null;
	}

	boolean hasFieldSelection() {
		return with() != null && with().fieldSelection() != null;
	}

	boolean hasFiltering() {
		return with() != null && with().filtering() != null;
	}
}
