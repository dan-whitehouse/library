import org.ricone.library.config.request2.*;
import org.ricone.library.config.response.ConfigResponse;
import org.ricone.library.config.response.model.*;
import org.ricone.library.exception.MissingArgumentException;
import org.springframework.http.HttpMethod;

public class Request2Test {
	private static final String url = System.getenv("config_url");
	private static final String username = System.getenv("api_config_username");
	private static final String password = System.getenv("api_config_password");

	public static void main(String[] args) throws MissingArgumentException {
		ConfigService configService = ConfigService.getInstance();
		configService.authenticate(url, username, password);

		if(configService.isAuthenticated()) {
			ConfigRequest request = new ConfigRequest(configService.getEndpoint());

			//postVendor(request);
			//putVendor(request);
			//patchVendor(request);
			//getVendor(request);
			//headVendor(request);
			//deleteVendor(request);

			/* Errors */
			//deleteVendors(request);
			//optionsVendor(request);

		}
		else {
			System.out.println("Authentication Failed");
		}
	}



	private static void getVendor(ConfigRequest request) throws MissingArgumentException {
		ConfigPath path = new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.GET).id("PostVendor1").build();
		ConfigResponse<Vendor> response = request.vendor(path);
		System.out.println(path.getHttpMethod() + " " + response.getRequestPath() + " - " +  response.getResponseStatus());
		System.out.println(response.getData());
	}


	private static void postVendor(ConfigRequest request) throws MissingArgumentException {
		Vendor vendor = new Vendor();
		vendor.setId("PostVendor1");
		vendor.setEnabled(true);
		vendor.setName("PostVendor");
		vendor.setStatus("N/A");

		ConfigPath path = new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.POST).body(vendor).build();
		ConfigResponse<Vendor> response = request.vendor(path);
		System.out.println(path.getHttpMethod() + " " + response.getRequestPath() + " - " +  response.getResponseStatus());
		System.out.println(response.getData());
	}

	private static void putVendor(ConfigRequest request) throws MissingArgumentException {
		Vendor vendor = new Vendor();
		vendor.setName("PutVendor");

		ConfigPath path = new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.PUT).id("PostVendor1").body(vendor).build();
		ConfigResponse<Vendor> response = request.vendor(path);
		System.out.println(path.getHttpMethod() + " " + response.getRequestPath() + " - " +  response.getResponseStatus());
		System.out.println(response.getData());
	}

	private static void patchVendor(ConfigRequest request) throws MissingArgumentException {
		Vendor vendor = new Vendor();
		vendor.setName("PatchVendor");

		ConfigPath path = new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.PATCH).id("PostVendor1").body(vendor).build();
		ConfigResponse<Vendor> response = request.vendor(path);
		System.out.println(path.getHttpMethod() + " " + response.getRequestPath() + " - " +  response.getResponseStatus());
		System.out.println(response.getData());
	}

	private static void headVendor(ConfigRequest request) throws MissingArgumentException {
		ConfigPath path = new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.HEAD).id("PostVendor1").build();
		ConfigResponse response = request.vendor(path);
		System.out.println(path.getHttpMethod() + " " + response.getRequestPath() + " - " +  response.getResponseStatus());
	}

	private static void optionsVendor(ConfigRequest request) throws MissingArgumentException {
		ConfigPath path = new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.OPTIONS).id("PostVendor1").build();
		ConfigResponse response = request.vendor(path);
		System.out.println(path.getHttpMethod() + " " + response.getRequestPath() + " - " +  response.getResponseStatus());
	}


	private static void deleteVendor(ConfigRequest request) throws MissingArgumentException {
		ConfigPath path = new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.DELETE).id("PostVendor1").build();
		ConfigResponse response = request.vendor(path);
		System.out.println(path.getHttpMethod() + " " + response.getRequestPath() + " - " +  response.getResponseStatus());
	}

	private static void deleteVendors(ConfigRequest request) throws MissingArgumentException {
		ConfigPath path = new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.DELETE).build();
		ConfigResponse response = request.vendor(path);
		System.out.println(path.getHttpMethod() + " " + response.getRequestPath() + " - " +  response.getResponseStatus());
	}
}
