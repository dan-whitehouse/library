package org.ricone.library.client.response;

public class Util {
	public static void debugResponse(XResponse response) {
		System.out.println("\tRequest Path: " + response.getRequestPath());
		System.out.println("\tRequest Headers: " + response.getRequestHeaders());
		System.out.println("\tResponse Status: " + response.getResponseStatus() + " " + response.getResponseStatusText());
		System.out.println("\tResponse Headers: " + response.getResponseHeaders());
		System.out.println("\tResponse Data: " + response.getData());
		System.out.println("\tResponse JSON:");
		System.out.println(tabMultiLine(response.getJSON()));
		System.out.println("\tResponse XML:");
		System.out.println(tabMultiLine(response.getXML()));
	}

	private static String tabMultiLine(String input) {
		return input.replaceAll("(?m)^", "\t");
	}
}
