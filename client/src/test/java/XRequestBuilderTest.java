import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.request.*;
import org.ricone.library.client.response.Util;
import org.ricone.library.client.response.XLastPageResponse;
import org.ricone.library.client.response.XResponse;
import org.ricone.library.client.response.model.XLea;
import org.ricone.library.client.response.model.XLeas;
import org.ricone.library.client.response.model.XStaffs;
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

		Authenticator authenticator = Authenticator.getInstance();
		authenticator.authenticate(authUrl, username, password);

		if(authenticator.isAuthenticated()) {
			Optional<Endpoint> endpoint = authenticator.getEndpoint(providerId);

			if(endpoint.isPresent()) {
				XPress xPress = new XPress(endpoint.get());

				badBuildTest(xPress);
				//getXLea(xPress);
				//getXLeas(xPress);
				//getXLeasWithPaging(xPress);
				//System.out.println("!!!!!!!!!!!");
				//getXLastPage(xPress);
			}
		}
		else {
			System.out.println("Authentication Failed");
		}
	}

	private static void badBuildTest(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLea */");

		/*XRequest request = new XRequestBuilder(ServicePath.GET_XLEA_BY_REFID)
				.id("A9F798CE-DA1A-3195-88CD-13AAC9416187")
				.pagingInfo(1, 1)
				.schoolYear(2020)
				.build();*/

		/*XRequest request = new XRequestProvisioningBuilder(ServicePath.GET_XSTAFFS_BY_XSCHOOL_REFID)
				.id("17891ACD-6072-39C6-8473-EC9CAB2615A1")
				.auppType(AUPPType.GET)
				.build();
		XResponse<XStaffs> response = xPress.getXStaffs(request);*/

		XRequest request = new XRequestBuilder(ServicePath.GET_XSTAFFS_BY_XSCHOOL_REFID)
				.id("A9F798CE-DA1A-3195-88CD-13AAC9416187")
				.pagingInfo(1, 1)
				.schoolYear(2020)
				.build();
		XResponse<XStaffs> response = xPress.getXStaffs(request);

		Util.debugResponse(response);

	}

	private static void getXLea(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLea */");

		XRequest request = new XRequestBuilder(ServicePath.GET_XLEA_BY_REFID)
				.id("A9F798CE-DA1A-3195-88CD-13AAC9416187")
				.build();
		XResponse<XLea> response = xPress.getXLea(request);

		Util.debugResponse(response);
		System.out.println(response.getData().getRefId() + " - " + response.getData().getLeaName());
	}

	private static void getXLeas(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLeas */");
		XRequest request = new XRequestBuilder(ServicePath.GET_XLEAS).build();
		XResponse<XLeas> response = xPress.getXLeas(request);

		Util.debugResponse(response);
		for (XLea xLea : response.getData().getXLeas()) {
			System.out.println(xLea.getRefId() + " - " + xLea.getLeaName());
		}
	}

	private static void getXLeasWithPaging(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLeasWithPaging */");
		ServicePath servicePath = ServicePath.GET_XLEAS;
		int pageSize = 1;

		XRequest lastPageRequest = new XRequestLastPageBuilder(servicePath)
				.pageSize(pageSize)
				.build();
		XLastPageResponse lastPage = xPress.getXLastPage(lastPageRequest);

		for (int pageNumber = 1; pageNumber <= lastPage.getData(); pageNumber++) {
			XRequest request = new XRequestBuilder(servicePath)
					.pagingInfo(pageNumber, pageSize)
					.build();
			XResponse<XLeas> response = xPress.getXLeas(request);

			Util.debugResponse(response);
		}
	}

	private static void getXLastPage(XPress xPress) throws MissingArgumentException, InvalidPathException {
		System.out.println("/* getXLastPage */");
		int pageSize = 2;

		XLastPageResponse lastPageResponse = xPress.getXLastPage(new XRequestLastPageBuilder(ServicePath.GET_XLEAS).pageSize(pageSize).build());
		Integer lastPage = lastPageResponse.getData();

		System.out.println("Last Page 1: " + lastPage);
		Util.debugResponse(lastPageResponse);

		XResponse<Integer> lastPageResponse2 = xPress.getXLastPage(new XRequestLastPageBuilder(ServicePath.GET_XLEAS).pageSize(pageSize).build());
		Integer lastPage2 = lastPageResponse2.getData();

		System.out.println("Last Page 1: " + lastPage2);
		Util.debugResponse(lastPageResponse2);

	}
}
