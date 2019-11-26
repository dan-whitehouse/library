package org.ricone.library.client.request;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
class XPressRequest extends XRequest {
	private ServicePath servicePath;
	private String id;
	private IdType idType;
	private Integer schoolYear;
	private PagingInfo pagingInfo;
	private AUPPType auppType;
	private ChangesSince changesSince;

	XPressRequest() {
	}

	public void setServicePath(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	@Override
	public void setSchoolYear(Integer schoolYear) {
		this.schoolYear = schoolYear;
	}

	@Override
	public void setPagingInfo(PagingInfo pagingInfo) {
		this.pagingInfo = pagingInfo;
	}

	@Override
	public void setAuppType(AUPPType auppType) {
		this.auppType = auppType;
	}

	@Override
	public void setChangesSince(ChangesSince changesSince) {
		this.changesSince = changesSince;
	}

	@Override
	public ServicePath getServicePath() {
		return this.servicePath;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public IdType getIdType() {
		return this.idType;
	}

	@Override
	public Integer getSchoolYear() {
		return this.schoolYear;
	}

	@Override
	public PagingInfo getPagingInfo() {
		return this.pagingInfo;
	}

	@Override
	public AUPPType getAuppType() {
		return this.auppType;
	}

	@Override
	public ChangesSince getChangesSince() {
		return this.changesSince;
	}
}
