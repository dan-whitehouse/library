package org.ricone.library.config.request;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
abstract class PathVerifier {
	boolean isInvalidPath(ConfigPathBase request) {
		return false;
	}
	boolean isMissingId(ConfigPathBase request) {
		return false;
	}
}
