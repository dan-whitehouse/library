/**
 * @project: APIClient
 * @author: Dan on 6/28/2018.
 */
module org.ricone.library.client {
	requires com.fasterxml.jackson.annotation;
	requires spring.web;
	requires spring.core;
	requires com.fasterxml.jackson.datatype.jdk8;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires com.fasterxml.jackson.module.paramnames;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.dataformat.xml;
	requires org.ricone.library.authentication;
	requires org.ricone.library.exception;
	requires org.apache.commons.io;

	opens org.ricone.library.client.xpress.response.model to com.fasterxml.jackson.databind;
	opens org.ricone.library.client.oneroster.response.model to com.fasterxml.jackson.databind;

	exports org.ricone.library.client.xpress.response to com.fasterxml.jackson.databind;
	exports org.ricone.library.client.xpress.response.model to com.fasterxml.jackson.databind;
	exports org.ricone.library.client.oneroster.response to com.fasterxml.jackson.databind;
	exports org.ricone.library.client.oneroster.response.model to com.fasterxml.jackson.databind;

	//See https://stackify.com/java-xml-jackson/
}