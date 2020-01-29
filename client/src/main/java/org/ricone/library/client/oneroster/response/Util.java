package org.ricone.library.client.oneroster.response;

import org.ricone.library.client.xpress.response.XResponse;
import org.springframework.util.StringUtils;

public class Util {

	public static void debugResponse(Response response, boolean requestPath, boolean requestHeaders, boolean responseStatus, boolean responseHeaders, boolean data, boolean json, boolean xml) {
		if(requestPath) {
			System.out.println("\tRequest Path: " + response.getRequestPath());
		}
		if(requestHeaders) {
			System.out.println("\tRequest Headers: " + response.getRequestHeaders());
		}
		if(responseStatus) {
			System.out.println("\tResponse Status: " + response.getResponseStatus() + " " + response.getResponseStatusText());
		}
		if(responseHeaders) {
			System.out.println("\tResponse Headers: " + response.getResponseHeaders());
		}
		if(data) {
			System.out.println("\tResponse Data: " + response.getData());
		}
		if(json) {
			System.out.println("\tResponse JSON:");
			System.out.println(tabMultiLine(response.getJSON()));
		}
		if(xml) {
			System.out.println("\tResponse XML:");
			System.out.println(tabMultiLine(response.getXML()));
		}
	}

	public static void debugResponse(Response response) {
		debugResponse(response, true, true, true, true, true, true, true);
	}

	public static void debugResponseJsonNoXml(Response response) {
		debugResponse(response, true, true, true, true, true, true, false);
	}

	public static void debugResponseXmlNoJson(Response response) {
		debugResponse(response, true, true, true, true, true, false, true);
	}

	private static String tabMultiLine(String input) {
		if(!StringUtils.isEmpty(input)) {
			return input.replaceAll("(?m)^", "\t");
		}
		return null;
	}
}
