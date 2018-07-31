import org.ricone.library.config.request.*;
import org.ricone.library.config.response.ConfigResponse;
import org.ricone.library.config.response.model.App;
import org.ricone.library.config.response.model.Apps;
import org.ricone.library.config.response.model.District;
import org.ricone.library.config.response.model.Districts;

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
            getDistrict(request);
            System.out.println("----------------");
            getDistrictsByApp(request);
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
        ConfigResponse<Apps> response = request.getApps(path);
        for (App app : response.getData().getApps()) {
            System.out.println(app.getId());
        }
    }

    private static void getAppsByDistrict(ConfigRequest request) {
        ConfigPath path = new ConfigPathBuilder(ServicePath.GET_APPS_BY_DISTRICT).id("530501").build();
        ConfigResponse<Apps> response = request.getApps(path);
        for (App app : response.getData().getApps()) {
            System.out.println(app.getId());
        }
    }

    private static void getDistrict(ConfigRequest request) {
        ConfigPath path = new ConfigPathBuilder(ServicePath.GET_DISTRICT).id("530501").build();
        ConfigResponse<District> response = request.getDistrict(path);
        System.out.println(response.getData().getId() + " - " + response.getData().getName());
    }

    private static void getDistrictsByApp(ConfigRequest request) {
        ConfigPath path = new ConfigPathBuilder(ServicePath.GET_DISTRICTS_BY_APP).id("CastleLearningOnline").build();
        ConfigResponse<Districts> response = request.getDistricts(path);
        for (District district : response.getData().getDistricts()) {
            System.out.println(district.getId() + " - " + district.getName());
        }
		System.out.println(response.getRequestPath());
    }
}
