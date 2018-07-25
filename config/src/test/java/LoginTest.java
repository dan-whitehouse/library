import org.ricone.library.config.App;
import org.ricone.library.config.request.*;
import org.ricone.library.config.response.ConfigResponse;

public class LoginTest {
    private static final String url = System.getenv("config_url");
    private static final String username = System.getenv("api_config_username");
    private static final String password = System.getenv("api_config_password");

    public static void main(String[] args) {
        ConfigService configService = ConfigService.getInstance();
        configService.authenticate(url, username, password);

        if(configService.isAuthenticated()) {
            ConfigRequest request = new ConfigRequest(configService.getEndpoint());

            ConfigPath path = new ConfigPathBuilder(ServicePath.GET_APP).id("CastleLearningOnline").build();

            ConfigResponse<App> response = request.getApp(path);
        }
        else {
            System.out.println("Authentication Failed");
        }
    }
}
