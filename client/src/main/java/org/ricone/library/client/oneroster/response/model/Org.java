package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "name", "type", "identifier", "parent", "children"})
@JsonRootName("org")
public class Org extends Base implements Serializable {
	private final static long serialVersionUID = -1025191997431202352L;
	@JsonProperty("name")
	private String name;
	@JsonProperty("type")
	private OrgType type;
	@JsonProperty("identifier")
	private String identifier;
	@JsonProperty("parent")
	private GUIDRef parent;
	@JsonProperty("children")
	@JacksonXmlElementWrapper(localName = "children") @JacksonXmlProperty(localName = "child")
	private List<GUIDRef> children = new ArrayList<>();

	/**
	 * No args constructor for use in serialization
	 */
	public Org() {
	}

	/**
	 * @param name
	 * @param children
	 * @param parent
	 * @param type
	 * @param identifier
	 */
	public Org(String name, OrgType type, String identifier, GUIDRef parent, List<GUIDRef> children) {
		super();
		this.name = name;
		this.type = type;
		this.identifier = identifier;
		this.parent = parent;
		this.children = children;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("type")
	public OrgType getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(OrgType type) {
		this.type = type;
	}

	@JsonProperty("identifier")
	public String getIdentifier() {
		return identifier;
	}

	@JsonProperty("identifier")
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@JsonProperty("parent")
	public GUIDRef getParent() {
		return parent;
	}

	@JsonProperty("parent")
	public void setParent(GUIDRef parent) {
		this.parent = parent;
	}

	@JsonProperty("children")
	@JacksonXmlElementWrapper(localName = "children") @JacksonXmlProperty(localName = "child")
	public List<GUIDRef> getChildren() {
		return children;
	}

	@JsonProperty("children")
	@JacksonXmlElementWrapper(localName = "children") @JacksonXmlProperty(localName = "child")
	public void setChildren(List<GUIDRef> children) {
		this.children = children;
	}

	@JsonIgnore
	@Override
	public boolean isEmptyObject() {
		return Stream.of(super.getSourcedId(), super.getStatus(), super.getDateLastModified(), super.getMetadata(), name, type, identifier, parent, children).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "Org{" +
				super.toString() +
				"name='" + name + '\'' +
				", type=" + type +
				", identifier='" + identifier + '\'' +
				", parent=" + parent +
				", children=" + children +
				"} ";
	}
}