package org.ricone.library.client.xpress.request;

import java.time.LocalDateTime;
import java.util.function.Consumer;

public final class XRequestWith {

	private PagingInfo pagingInfo;
	private Integer schoolYear;
	private boolean accountProvisioning;
	private ChangesSince changesSince;

	public static Builder builder() {
		return new XRequestWith.Builder();
	}

	private XRequestWith() {
	}

	public PagingInfo getPagingInfo() {
		return pagingInfo;
	}

	public Integer getSchoolYear() {
		return schoolYear;
	}

	public boolean isAccountProvisioning() {
		return accountProvisioning;
	}

	public ChangesSince getChangesSince() {
		return changesSince;
	}

	public static class Builder {
		private XRequestWith instance = new XRequestWith();
		private XRequest.Builder parentBuilder;
		private Consumer<XRequestWith> callback;

		public Builder() {
		}

		public Builder(XRequest.Builder b, Consumer<XRequestWith> f) {
			this.parentBuilder = b;
			this.callback = f;
		}


		public Builder paging(Integer pageNumber, Integer pageSize) {
			instance.pagingInfo = new PagingInfo(pageNumber, pageSize);
			return this;
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