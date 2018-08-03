package org.ricone.library.exception;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
public class MissingArgumentException extends RuntimeException {
	public MissingArgumentException() {
	}

	public MissingArgumentException(String message) {
		super(message);
	}
}
