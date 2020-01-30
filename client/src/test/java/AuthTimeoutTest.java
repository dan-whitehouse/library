import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.exception.InvalidPathException;

import java.time.LocalDateTime;

public class AuthTimeoutTest {

    public static void main(String[] args) throws InvalidPathException, InterruptedException {
        Authenticator authenticator = Authenticator.builder()
            .url(System.getenv("url")).api(API.OneRoster)
            .credentials(System.getenv("username"), System.getenv("password"))
            .provider(System.getenv("provider"))
        .authenticate();

        while(authenticator.getEndpoint().isPresent()) {
            Endpoint e = authenticator.getEndpoint().get();
            System.out.println(LocalDateTime.now() + " | " + e.getDecodedToken().willTokenExpire() + " | " + e.getDecodedToken().isTokenExpired() + " | " + e.getDecodedToken().getToken());
            Thread.sleep(60000);
        }
    }
}
