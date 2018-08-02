import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.request.*;
import org.ricone.library.client.response.XResponse;
import org.ricone.library.client.response.model.XLea;
import org.ricone.library.client.response.model.XLeas;
import org.ricone.library.exception.InvalidPathException;
import org.ricone.library.exception.MissingArgumentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @project: client
 * @author: Dan on 6/28/2018.
 */
public class XRequestBuilderTest {
	private static final String authUrl = System.getenv("url");
	private static final String username = System.getenv("username");
	private static final String password = System.getenv("password");
	private static final String providerId = System.getenv("provider");

	public static void main(String[] args) throws InvalidPathException, MissingArgumentException {

		Authenticator authenticator = Authenticator.getInstance();
		authenticator.authenticate(authUrl, username, password);

		if(authenticator.isAuthenticated()) {
			Optional<Endpoint> endpoint = authenticator.getEndpoint(providerId);

			if(endpoint.isPresent()) {
				XPress xPress = new XPress(endpoint.get());

				//getXLea(xPress);
				getXLeas(xPress);
				//getXLeasWithPaging(xPress);
			}
		}
		else {
			System.out.println("Authentication Failed");
		}
	}

	private static void getXLea(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLea */");
		XRequest request = new XRequestBuilder(ServicePath.GET_XLEA_BY_REFID).id("5776DEB4-A6C8-43B1-AB08-CD5B95983177").build();
		XResponse<XLea> response = xPress.getXLea(request);

		printResponse(response);
		System.out.println(response.getData().getRefId() + " - " + response.getData().getLeaName());
	}

	private static void getXLeas(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLeas */");
		XRequest request = new XRequestBuilder(ServicePath.GET_XLEAS).build();
		XResponse<XLeas> response = xPress.getXLeas(request);

		printResponse(response);
		for (XLea xLea : response.getData().getXLeas()) {
			System.out.println(xLea.getRefId() + " - " + xLea.getLeaName());
		}
	}

	private static void getXLeasWithPaging(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLeasWithPaging */");
		int pageSize = 2;
		Integer lastPage = xPress.getLastPage(new XRequestLastPageBuilder(ServicePath.GET_XLEAS).pageSize(pageSize).build());

		List<XLea> xLeas = new ArrayList<>();
		for (int pageNumber = 1; pageNumber <= lastPage; pageNumber++) {
			XRequest request = new XRequestBuilder(ServicePath.GET_XLEAS).pagingInfo(pageNumber, pageSize).build();
			XResponse<XLeas> response = xPress.getXLeas(request);

			printResponse(response);
			xLeas.addAll(response.getData().getXLeas());
		}

		for (XLea xLea : xLeas) {
			System.out.println(xLea.getRefId() + " - " + xLea.getLeaName());
		}
	}

	private static void printResponse(XResponse response) {
		System.out.println("\tRequest Path: " + response.getRequestPath());
		//System.out.println("\tRequest Headers: " + response.getRequestHeaders());
		System.out.println("\tResponse Status: " + response.getResponseStatus());
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
