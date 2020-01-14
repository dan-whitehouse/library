import org.ricone.library.authentication.Authenticator2;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.xpress.request.IdType;
import org.ricone.library.client.xpress.request.ServicePath;
import org.ricone.library.client.xpress.request.XPress;
import org.ricone.library.client.xpress.request.XRequest;
import org.ricone.library.client.xpress.response.Util;
import org.ricone.library.client.xpress.response.XLastPageResponse;
import org.ricone.library.client.xpress.response.XResponse;
import org.ricone.library.client.xpress.response.model.XLea;
import org.ricone.library.client.xpress.response.model.XLeas;
import org.ricone.library.exception.InvalidPathException;
import org.ricone.library.exception.MissingArgumentException;

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
		Authenticator2 authenticator = Authenticator2.builder()
				.url(authUrl).credentials(username, password)
				.provider(providerId)
				.authenticate();

		if(authenticator.isAuthenticated()) {
			Optional<Endpoint> endpoint = authenticator.getEndpoint();

			if(endpoint.isPresent()) {
				XPress xPress = new XPress(endpoint.get());

				//getXLea(xPress);
				//getXLeas(xPress);
				getXLeasWithPaging(xPress);
				//System.out.println("!!!!!!!!!!!");
				//getXLastPage(xPress);
			}
		}
		else {
			System.out.println("Authentication Failed");
		}
	}

	private static void getXLea(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLea */");

		XResponse<XLea> response = xPress.getXLea(XRequest.builder()
			.request()
				.path(ServicePath.GET_XLEA_BY_REFID)
				.id("A9F798CE-DA1A-3195-88CD-13AAC9416187", IdType.RefId).end()
			.build()
		);

		Util.debugResponse(response);
		System.out.println(response.getData().getRefId() + " - " + response.getData().getLeaName());
	}

	private static void getXLeas(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLeas */");

		XResponse<XLeas> response = xPress.getXLeas(XRequest.builder()
			.request().path(ServicePath.GET_XLEAS).end()
			.build()
		);

		Util.debugResponse(response);
		for (XLea xLea : response.getData().getXLeas()) {
			System.out.println(xLea.getRefId() + " - " + xLea.getLeaName());
		}
	}

	private static void getXLeasWithPaging(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLeasWithPaging */");
		ServicePath servicePath = ServicePath.GET_XLEAS;
		int pageSize = 1;

		XLastPageResponse lastPage = xPress.getXLastPage(XRequest.builder()
			.request().path(servicePath).end()
			.with().paging(1, pageSize).end()
			.build()
		);

		for (int pageNumber = 1; pageNumber <= lastPage.getData(); pageNumber++) {
			XResponse<XLeas> response = xPress.getXLeas(XRequest.builder()
				.request().path(servicePath).end()
				.with().paging(pageNumber, pageSize).end()
				.build()
			);
			Util.debugResponse(response);
		}
	}

	private static void getXLastPage(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLastPage */");
		int pageSize = 2;

		XResponse<Integer> lastPageResponse = xPress.getXLastPage(XRequest.builder()
			.request().path(ServicePath.GET_XLEAS).end()
			.with().paging(1, pageSize).end()
			.build()
		);
		Integer lastPage = lastPageResponse.getData();

		System.out.println("Last Page 1: " + lastPage);
		Util.debugResponse(lastPageResponse);
	}
}
