import org.ricone.library.config.request.*;
import org.ricone.library.config.response.ConfigResponse;
import org.ricone.library.config.response.model.App;

public class LoginTest {
    private static final String url = System.getenv("config_url");
    private static final String username = System.getenv("api_config_username");
    private static final String password = System.getenv("api_config_password");

    public static void main(String[] args) {
        ConfigService configService = ConfigService.getInstance();
        configService.authenticate(url, username, password);

        if(configService.isAuthenticated()) {
            ConfigRequest request = new ConfigRequest(configService.getEndpoint());

            getApp(request);
            System.out.println("----------------");
            getApps(request);
            System.out.println("----------------");
            getAppsByDistrict(request);
            System.out.println("----------------");

        }
        else {
            System.out.println("Authentication Failed");
        }
    }

    private static void getApp(ConfigRequest request) {
        ConfigPath path = new ConfigPathBuilder(ServicePath.GET_APP).id("CastleLearningOnline").build();
        ConfigResponse<App> response = request.getApp(path);
        System.out.println(response.getData().getId());
    }

    private static void getApps(ConfigRequest request) {
        ConfigPath path = new ConfigPathBuilder(ServicePath.GET_APPS).build();
        ConfigResponse<App[]> response = request.getApps(path);
        for (App app : response.getData()) {
            System.out.println(app.getId());
        }
    }

    private static void getAppsByDistrict(ConfigRequest request) {
        ConfigPath path = new ConfigPathBuilder(ServicePath.GET_APPS_BY_DISTRICT).id("530501").build();
        ConfigResponse<App[]> response = request.getApps(path);
        for (App app : response.getData()) {
            System.out.println(app.getId());
        }
    }
}
