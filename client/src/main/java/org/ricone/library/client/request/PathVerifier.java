package org.ricone.library.client.request;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
abstract class PathVerifier {
	boolean isInvalidPath(XRequest request) {
		return false;
	}
	boolean isMissingId(XRequest request) {
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
