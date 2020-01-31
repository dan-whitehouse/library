package org.ricone.library.client.xpress.request;

import java.time.LocalDateTime;
import java.util.function.Consumer;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-31
 */

public final class XWithBuilder {
	private XPagingBuilder paging;
	private Integer schoolYear;
	private boolean accountProvisioning;
	private ChangesSince changesSince;

	public static Builder builder() {
		return new XWithBuilder.Builder();
	}

	private XWithBuilder() {
		accountProvisioning = false;
	}

	public XPagingBuilder paging() {
		return paging;
	}

	public Integer schoolYear() {
		return schoolYear;
	}

	public boolean isAccountProvisioning() {
		return accountProvisioning;
	}

	public ChangesSince getChangesSince() {
		return changesSince;
	}

	public static class Builder {
		private XWithBuilder instance = new XWithBuilder();
		private XRequest.Builder parentBuilder;
		private Consumer<XWithBuilder> callback;

		public Builder() {
		}

		public Builder(XRequest.Builder b, Consumer<XWithBuilder> f) {
			this.parentBuilder = b;
			this.callback = f;
		}


		public XPagingBuilder.Builder paging() {
			Consumer<XPagingBuilder> f = obj -> instance.paging = obj;
			return new XPagingBuilder.Builder(this, f);
		}

		public Builder schoolYear(Integer schoolYear) {
			instance.schoolYear = schoolYear;
			return this;
		}

		public Builder accountProvisioning() {
			instance.accountProvisioning = true;
			return this;
		}

		public Builder changesSince(LocalDateTime localDateTime) {
			instance.changesSince = new ChangesSince(localDateTime);
			return this;
		}
		
		public XRequest.Builder end() {
			callback.accept(instance);
			return parentBuilder;
		}
		
	}		
}