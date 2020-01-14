package org.ricone.library.client.xpress.request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
public class ChangesSince {
	private LocalDateTime opaqueMarker;

	public ChangesSince(LocalDateTime opaqueMarker) {
		this.opaqueMarker = opaqueMarker;
	}

	public LocalDateTime getOpaqueMarker() {
		return opaqueMarker;
	}

	String iso8601() {
		return opaqueMarker.format(DateTimeFormatter.ISO_DATE_TIME);
	}
}
