import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;

import java.util.List;

/**
 * @project: authentication
 * @author: Dan on 7/23/2018.
 */
public class MultiEndpoint {
	private static final String authUrl = System.getenv("url");
	private static final String username = System.getenv("username");
	private static final String password = System.getenv("password");

	public static void main(String[] args) {

		Authenticator authenticator = Authenticator.getInstance();
		authenticator.authenticate(authUrl, username, password);

		if(authenticator.isAuthenticated()) {
			List<Endpoint> endpoints = authenticator.getEndpoints();

			for (Endpoint endpoint : endpoints) {
				System.out.println("Token: " + endpoint.getToken());
				System.out.println("Href: " + endpoint.getHref());
				System.out.println("ProviderId: " + endpoint.getProviderId());
				System.out.println("Provider Name: " + endpoint.getName());
			}
		}
		else {
			System.out.println("Authentication Failed");
		}
	}
}
