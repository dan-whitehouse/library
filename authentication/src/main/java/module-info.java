/**
 * @project: APIClient
 * @author: Dan on 6/28/2018.
 */
module org.ricone.library.authentication {
	requires spring.web;
	requires spring.core;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.databind;
	requires java.jwt;

	opens org.ricone.library.authentication to com.fasterxml.jackson.databind;

	exports org.ricone.library.authentication to com.fasterxml.jackson.databind, org.ricone.library.client;
}