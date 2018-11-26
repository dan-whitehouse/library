/**
 * @project: APIClient
 * @author: Dan on 6/28/2018.
 */
module org.ricone.library.client {
	requires com.fasterxml.jackson.annotation;
	requires spring.web;
	requires spring.core;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.dataformat.xml;
	requires org.ricone.library.authentication;
	requires org.ricone.library.exception;

	opens org.ricone.library.client.response.model to com.fasterxml.jackson.databind;

	exports org.ricone.library.client.response to com.fasterxml.jackson.databind;
	exports org.ricone.library.client.response.model to com.fasterxml.jackson.databind;

	//See https://stackify.com/java-xml-jackson/
}