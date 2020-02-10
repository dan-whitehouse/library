package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.library.client.core.Model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.stream.Stream;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata"})
public abstract class Base extends Model implements Serializable {
	private final static long serialVersionUID = 602595453201771641L;
	@JsonProperty("sourcedId")
	private String sourcedId;
	@JsonProperty("status")
	private StatusType status;
	@JsonProperty("dateLastModified")
	private ZonedDateTime dateLastModified;
	@JsonProperty("metadata")
	private Metadata metadata;

	/**
	 * No args constructor for use in serialization
	 */
	public Base() {
	}

	/**
	 * @param status - active | tobedeleted
	 * @param dateLastModified - All objects must be annotated with the dateTime upon which they were last modified. DateTimes MUST be expressed in W3C profile of ISO 8601 and MUST contain the UTC timezone;
	 * @param metadata - Objects can be extended using the Metadata class. This specification is silent on what implementers may consider to be appropriate extensions.
	 * @param sourcedId - This is a GUID System ID for an object. This is the GUID that systems will refer to when making API calls, or when needing to identify an object
	 */
	public Base(String sourcedId, StatusType status, ZonedDateTime dateLastModified, Metadata metadata) {
		super();
		this.sourcedId = sourcedId;
		this.status = status;
		this.dateLastModified = dateLastModified;
		this.metadata = metadata;
	}

	@JsonProperty("sourcedId")
	public String getSourcedId() {
		return sourcedId;
	}

	@JsonProperty("sourcedId")
	public void setSourcedId(String sourcedId) {
		this.sourcedId = sourcedId;
	}

	@JsonProperty("status")
	public StatusType getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(StatusType status) {
		this.status = status;
	}

	@JsonProperty("dateLastModified")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	public ZonedDateTime getDateLastModified() {
		return dateLastModified;
	}

	@JsonProperty("dateLastModified")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	public void setDateLastModified(ZonedDateTime dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	@JsonProperty("metadata")
	public Metadata getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean isEmpty() {
		return Stream.of(sourcedId, status, dateLastModified, metadata).allMatch(Objects::isNull);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Base base = (Base) o;
		return Objects.equals(sourcedId, base.sourcedId) &&
				status == base.status &&
				Objects.equals(dateLastModified, base.dateLastModified) &&
				Objects.equals(metadata, base.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sourcedId, status, dateLastModified, metadata);
	}

	@Override
	public String toString() {
		return "sourcedId='" + sourcedId + '\'' +
				", status=" + status +
				", dateLastModified=" + dateLastModified +
				", metadata=" + metadata + ", ";
	}
}