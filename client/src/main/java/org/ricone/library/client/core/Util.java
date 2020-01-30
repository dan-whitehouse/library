package org.ricone.library.client.core;

import org.springframework.util.StringUtils;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public class Util {
	public static void debugResponse(BaseResponse<?> response, boolean requestPath, boolean requestHeaders, boolean responseStatus, boolean responseHeaders, boolean data, boolean json, boolean xml) {
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

	public static void debugResponse(BaseResponse<?> response) {
		debugResponse(response, true, true, true, true, true, true, true);
	}

	public static void debugResponseJsonNoXml(BaseResponse<?> response) {
		debugResponse(response, true, true, true, true, true, true, false);
	}

	public static void debugResponseXmlNoJson(BaseResponse<?> response) {
		debugResponse(response, true, true, true, true, true, false, true);
	}

	private static String tabMultiLine(String input) {
		if(!StringUtils.isEmpty(input)) {
			return input.replaceAll("(?m)^", "\t");
		}
		return null;
	}
}
