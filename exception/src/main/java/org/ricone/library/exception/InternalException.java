package org.ricone.library.exception;

import java.io.IOException;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
public class InternalException extends IOException {
	public InternalException() {
	}

	public InternalException(String message) {
		super(message);
	}
}
