package org.ricone.library.client.xpress.request.builder;

import org.ricone.library.client.xpress.request.XRequest;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
abstract class XBuilder {
	boolean isInvalidPath(XRequest request) {
		return false;
	}
	boolean isMissingId(XRequest request) {
		return false;
	}
	boolean hasIdButShouldNot(XRequest request) {
		return false;
	}
	boolean isMissingIdType(XRequest request) {
		return false;
	}
	boolean isMissingChangesSince(XRequest request) {
		return false;
	}
	boolean isMissingAUPPType(XRequest request) {
		return false;
	}
	boolean isMissingPagingInfo(XRequest request) {
		return false;
	}
}
