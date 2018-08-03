import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ricone.library.authentication.Authenticator;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Authentication Test")
public class AuthenticatorTest {
    private static Authenticator authenticator;
    private static final String url = System.getenv("url");
    private static final String username = System.getenv("username");
    private static final String password = System.getenv("password");
    private static final String providerId = System.getenv("providerId");

    @BeforeAll
    static void initAll() {
        authenticator = Authenticator.getInstance();
        authenticator.authenticate(url, username, password);
    }

    @Test
    void isAuthenticated() {
        assertTrue(authenticator.isAuthenticated());
    }

    @Test
    void singleEndpoint() {
        assertTrue(authenticator.getEndpoint(providerId).isPresent());
    }

    @Test
    void multipleEndpoints() {
        assertTrue(!authenticator.getEndpoints().isEmpty());
    }
}
