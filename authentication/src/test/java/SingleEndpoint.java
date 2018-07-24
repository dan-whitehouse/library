import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;

import java.util.Optional;

/**
 * @project: authentication
 * @author: Dan on 7/23/2018.
 */
public class SingleEndpoint {
	private static final String authUrl = System.getenv("url");
	private static final String username = System.getenv("username");
	private static final String password = System.getenv("password");
	private static final String providerId = System.getenv("provider");

	public static void main(String[] args) {

		Authenticator authenticator = Authenticator.getInstance();
		authenticator.authenticate(authUrl, username, password);

		if(authenticator.isAuthenticated()) {
			Optional<Endpoint> endpoint = authenticator.getEndpoint(providerId);

			if(endpoint.isPresent()) {
				System.out.println("Token: " + endpoint.get().getToken());
				System.out.println("Href: " + endpoint.get().getHref());
				System.out.println("ProviderId: " + endpoint.get().getProviderId());
				System.out.println("Provider Name: " + endpoint.get().getName());
			}
		}
		else {
			System.out.println("Authentication Failed");
		}
	}
}
