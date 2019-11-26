package org.ricone.library.client.request;

import org.springframework.util.StringUtils;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
public abstract class XRequest {
	abstract ServicePath getServicePath();

	abstract void setServicePath(ServicePath servicePath);

	abstract String getId();

	abstract void setId(String id);

	abstract IdType getIdType();

	abstract void setIdType(IdType idType);

	abstract Integer getSchoolYear();

	abstract void setSchoolYear(Integer schoolYear);

	abstract PagingInfo getPagingInfo();

	abstract void setPagingInfo(PagingInfo pagingInfo);

	abstract AUPPType getAuppType();

	abstract void setAuppType(AUPPType auppType);

	abstract ChangesSince getChangesSince();

	abstract void setChangesSince(ChangesSince changesSince);

	boolean hasId() {
		return StringUtils.hasText(getId());
	}

	boolean hasIdType() {
		return getIdType() != null;
	}

	boolean hasPaging() {
		return getPagingInfo() != null;
	}

	boolean hasSchoolYear() {
		return getSchoolYear() != null;
	}

	boolean hasAUPP() {
		return getAuppType() != null;
	}

	boolean hasChangesSince() {
		return getChangesSince() != null;
	}

	boolean containsRequestType(RequestType requestType) {
		return getServicePath().getXPressRequestTypes().contains(requestType);
	}

	boolean isServicePathType(ServicePathType servicePathType) {
		return getServicePath().getServicePathType().equals(servicePathType);
	}
}
