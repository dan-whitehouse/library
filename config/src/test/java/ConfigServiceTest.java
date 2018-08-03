import org.junit.jupiter.api.*;
import org.ricone.library.config.request.ConfigService;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JUnit Test")
class ConfigServiceTest {
    private static ConfigService configService;
    private static final String url = System.getenv("config_url");
    private static final String username = System.getenv("api_config_username");
    private static final String password = System.getenv("api_config_password");

    @BeforeAll
    static void initAll() {
        configService = ConfigService.getInstance();
        configService.authenticate(url, username, password);
    }

    @Test
    void isAuthenticated() {
        assertTrue(configService.isAuthenticated());
    }

    @Test
    void endpointIsNotNull() {
        assertNotNull(configService.getEndpoint());
        assertNotNull(configService.getEndpoint().getCreated());
        assertNotNull(configService.getEndpoint().getExpires());
        assertNotNull(configService.getEndpoint().getHref());
        assertNotNull(configService.getEndpoint().getToken());
    }
}
