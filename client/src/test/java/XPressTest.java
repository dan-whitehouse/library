import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Model;
import org.ricone.library.client.xpress.request.*;
import org.ricone.library.client.xpress.response.model.*;
import org.ricone.library.client.xpress.request.XPress;
import org.ricone.library.exception.InvalidPathException;
import org.ricone.library.client.xpress.request.XRequest;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SuppressWarnings("deprecation")
public class XPressTest {
    private static final int PAGE = 1;
    private static final int SIZE = 2;
    private static final int SCHOOL_YEAR = 2020;
    private static final LocalDateTime CHANGES_SINCE = LocalDateTime.now();
    private static final String DISTRICT_ID = "530501";
    private static final String FORMAT = "| %-200s | %-6s | %-9s | %-18s | %-9s |%n";
    //private static final String FORMAT = "| %-113s | %-11s | %-7s | %-8s | %-10s | %-6s | %-9s | %-18s |%n";
    private static final Random random = new Random();
    private static final int randomNumber = 0; //random.nextInt(((SIZE - 1) - 0 + 1) + 0); // 0 = min, LIMIT = max

    private static List<XLea> xLeas = new ArrayList<>();
    private static List<XSchool> xSchools = new ArrayList<>();
    private static List<XCalendar> xCalendars = new ArrayList<>();
    private static List<XCourse> xCourses = new ArrayList<>();
    private static List<XRoster> xRosters = new ArrayList<>();
    private static List<XStaff> xStaffs = new ArrayList<>();
    private static List<XStudent> xStudents = new ArrayList<>();
    private static List<XContact> xContacts = new ArrayList<>();

    public static void main(String[] args) throws InvalidPathException {
        Authenticator authenticator = Authenticator.builder()
            .url(System.getenv("url")).api(API.xPress)
            .credentials(System.getenv("username"), System.getenv("password"))
            .provider(System.getenv("provider"))
        .authenticate();

        Optional<Endpoint> endpoint = authenticator.getEndpoint("localhost");
        if(endpoint.isPresent()) {
            XPress xPress = new XPress(endpoint.get());

            //Add values to the lists, so subsequent calls can be made using their ids.
            loadLists(xPress);

            runTests(xPress);
        }
    }

    private static void loadLists(XPress xPress) throws InvalidPathException {
        xLeas = loadXLeas(xPress);
        xSchools = loadXSchools(xPress);
        xCalendars = loadXCalendars(xPress);
        xCourses = loadXCourses(xPress);
        xRosters = loadXRosters(xPress);
        xStaffs = loadXStaffs(xPress);
        xStudents = loadXStudents(xPress);
        xContacts = loadXContacts(xPress);
    }

    private static List<XLea> loadXLeas(XPress xPress) throws InvalidPathException {
        IResponse<XLeas> response = xPress.request(XRequest.builder()
            .request()
                .path(ServicePath.GET_XLEAS)
            .end()
            .with()
                .paging()
                    .page(PAGE)
                    .size(SIZE)
                .end()
                .schoolYear(2020)
            .end()
        .build());

        showResults(response);

        return response.getData().getXLeas();
    }

    private static List<XSchool> loadXSchools(XPress xPress) throws InvalidPathException {
        IResponse<XSchools> response = xPress.request(XRequest.builder()
            .request()
                .path(ServicePath.GET_XSCHOOLS)
            .end()
            .with()
                .paging()
                    .page(PAGE)
                    .size(SIZE)
                .end()
                .schoolYear(SCHOOL_YEAR)
            .end()
        .build());

        showResults(response);

        return response.getData().getXSchool();
    }

    private static List<XCalendar> loadXCalendars(XPress xPress) throws InvalidPathException {
        IResponse<XCalendars> response = xPress.request(XRequest.builder()
            .request()
                .path(ServicePath.GET_XCALENDARS)
            .end()
            .with()
                .paging()
                    .page(PAGE)
                    .size(SIZE)
                .end()
                .schoolYear(SCHOOL_YEAR)
            .end()
        .build());

        showResults(response);

        return response.getData().getXCalendar();
    }

    private static List<XCourse> loadXCourses(XPress xPress) throws InvalidPathException {
        IResponse<XCourses> response = xPress.request(XRequest.builder()
            .request()
                .path(ServicePath.GET_XCOURSES)
            .end()
            .with()
                .paging()
                    .page(PAGE)
                    .size(SIZE)
                .end()
                .schoolYear(SCHOOL_YEAR)
            .end()
        .build());

        showResults(response);

        return response.getData().getXCourse();
    }

    private static List<XRoster> loadXRosters(XPress xPress) throws InvalidPathException {
        IResponse<XRosters> response = xPress.request(XRequest.builder()
            .request()
                .path(ServicePath.GET_XROSTERS)
                .end()
            .with()
                .paging()
                    .page(PAGE)
                    .size(SIZE)
                .end()
                .schoolYear(SCHOOL_YEAR)
            .end()
        .build());

        showResults(response);

        return response.getData().getXRoster();
    }

    private static List<XStaff> loadXStaffs(XPress xPress) throws InvalidPathException {
        IResponse<XStaffs> response = xPress.request(XRequest.builder()
            .request()
                .path(ServicePath.GET_XSTAFFS)
                .end()
            .with()
                .paging()
                    .page(PAGE)
                    .size(SIZE)
                .end()
                .schoolYear(SCHOOL_YEAR)
            .end()
        .build());

        showResults(response);

        return response.getData().getXStaff();
    }

    private static List<XStudent> loadXStudents(XPress xPress) throws InvalidPathException {
        IResponse<XStudents> response = xPress.request(XRequest.builder()
            .request()
                .path(ServicePath.GET_XSTUDENTS)
            .end()
            .with()
                .paging()
                    .page(PAGE)
                    .size(SIZE)
                .end()
                .schoolYear(SCHOOL_YEAR)
            .end()
        .build());

        showResults(response);

        return response.getData().getXStudent();
    }

    private static List<XContact> loadXContacts(XPress xPress) throws InvalidPathException {
        IResponse<XContacts> response = xPress.request(XRequest.builder()
            .request()
                .path(ServicePath.GET_XCONTACTS)
            .end()
            .with()
                .paging()
                    .page(PAGE)
                    .size(SIZE)
                .end()
                .schoolYear(SCHOOL_YEAR)
            .end()
        .build());

        showResults(response);

        return response.getData().getXContact();
    }

    private static void runTests(XPress xPress) throws InvalidPathException {
        printTableBorder();
        //System.out.format("| Request                                                                                                           | School Year | Page    | PageSize | LastPage   | Status | Size      | Total Record Count |%n");
        System.out.format("| Request                                                                                                                                                                                                  | Status | Size      | Total Record Count | Duration  |%n");
        printTableBorder();

        for(ServicePath servicePath : ServicePath.values()) {
            /* The Type of Request - Requires RefId, LocalId, or StateId */
            IdType type = servicePath.getXPressRequestTypes().contains(RequestType.ID) ? IdType.Local : IdType.RefId;

            /* Request - No Features */
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                IResponse<? extends Model> responseNoFeatures = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                        .id(getId(getObject(servicePath), type), type)
                    .end()
                    .build());
                printTableRow(responseNoFeatures);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                IResponse<? extends Model> responseNoFeatures = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                    .end()
                .build());
                printTableRow(responseNoFeatures);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                IResponse<? extends Model> responseNoFeatures = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                        .id(getId(getPredicate(servicePath), type), type)
                    .end()
                .build());
                printTableRow(responseNoFeatures);
            }

            /* Request - School Year */
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                IResponse<? extends Model> responseFieldSelection = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                        .id(getId(getObject(servicePath), type), type)
                    .end()
                    .with()
                        .schoolYear(SCHOOL_YEAR)
                    .end()
                    .configuration()
                        .withAsQueryParameter(true)
                    .end()
                .build());
                printTableRow(responseFieldSelection);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                IResponse<? extends Model> responseFieldSelection = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                    .end()
                    .with()
                        .schoolYear(SCHOOL_YEAR)
                    .end()
                    .configuration()
                        .withAsQueryParameter(true)
                    .end()
                .build());
                printTableRow(responseFieldSelection);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                IResponse<? extends Model> responseFieldSelection = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                        .id(getId(getPredicate(servicePath), type), type)
                    .end()
                    .with()
                        .schoolYear(SCHOOL_YEAR)
                    .end()
                    .configuration()
                        .withAsQueryParameter(true)
                    .end()
                .build());
                printTableRow(responseFieldSelection);
            }


            /* Request - Paging */
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                IResponse<? extends Model> responsePaging = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                        .id(getId(getObject(servicePath), type), type)
                    .end()
                    .with()
                        .paging()
                            .page(PAGE)
                            .size(100)
                        .end()
                    .end()
                    .configuration()
                        .withAsQueryParameter(true)
                    .end()
                .build());
                printTableRow(responsePaging);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                IResponse<? extends Model> responsePaging = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                    .end()
                    .with()
                        .paging()
                            .page(PAGE)
                            .size(100)
                        .end()
                    .end()
                    .configuration()
                        .withAsQueryParameter(true)
                    .end()
                .build());
                printTableRow(responsePaging);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                IResponse<? extends Model> responsePaging = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                        .id(getId(getPredicate(servicePath), type), type)
                    .end()
                    .with()
                        .paging()
                            .page(PAGE)
                            .size(100)
                        .end()
                    .end()
                    .configuration()
                        .withAsQueryParameter(true)
                    .end()
                .build());
                printTableRow(responsePaging);
            }

            /* Request - Changes Since */
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                IResponse<? extends Model> responseFiltering = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                        .id(getId(getObject(servicePath), type), type)
                    .end()
                    .with()
                        .changesSince(CHANGES_SINCE)
                    .end()
                .build());
                printTableRow(responseFiltering);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                IResponse<? extends Model> responseFiltering = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                    .end()
                    .with()
                        .changesSince(CHANGES_SINCE)
                    .end()
                .build());
                printTableRow(responseFiltering);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                IResponse<? extends Model> responseFiltering = xPress.request(XRequest.builder()
                    .request()
                        .path(servicePath)
                        .id(getId(getPredicate(servicePath), type), type)
                    .end()
                    .with()
                        .changesSince(CHANGES_SINCE)
                    .end()
                .build());
                printTableRow(responseFiltering);
            }

            //Separate the next service path
            printTableBorder();
        }
    }

    //Helper Methods
    private static void showResults(IResponse<? extends Model> response) {
        System.out.println("Request: " + response.getRequestPath() + " | Status: " + response.getResponseStatus() + " | HasData: " + response.getData().hasData() + " | Response Headers: " + response.getResponseHeaders());
    }

    private static void printTableBorder() {
        //System.out.format("+-------------------------------------------------------------------------------------------------------------------+-------------+---------+----------+------------+--------+-----------+--------------------+%n");
        System.out.format("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+--------+-----------+--------------------+-----------+%n");
    }

    private static void printTableRow(IResponse<? extends Model> response) {
        /*System.out.format(FORMAT,
            trimUrl(response.getRequestPath()),
            response.getResponseHeaders().getFirst("SchoolYear"),
            response.getResponseHeaders().getFirst("NavigationPage"),
            response.getResponseHeaders().getFirst("NavigationPageSize"),
            response.getResponseHeaders().getFirst("NavigationLastPage"),
            response.getResponseStatus(),
            byteCount(response.getResponseHeaders().getFirst("Content-Length")),
            formatNumber(response.getResponseHeaders().getFirst("X-Total-Count")));
        */
        System.out.format(FORMAT, trimUrl(response.getRequestPath()), response.getResponseStatus(), byteCount(response.getResponseHeaders().getFirst("Content-Length")), formatNumber(response.getResponseHeaders().getFirst("X-Total-Count")), response.getResponseHeaders().getFirst("X-Duration"));
    }

    private static String trimUrl(String url) {
        return url.replace("http://localhost:8080/api/requests","");
    }

    private static String byteCount(String stringBytes) {
        if(stringBytes == null) {
            stringBytes = "0";
        }

        long bytes = Long.parseLong(stringBytes);
        String s = bytes < 0 ? "-" : "";
        long b = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        return b < 1000L ? bytes + " B"
                : b < 999_950L ? String.format("%s%.1f kB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f MB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f GB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f TB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f PB", s, b / 1e3)
                : String.format("%s%.1f EB", s, b / 1e6);
    }

    private static String formatNumber(String number) {
        if(number == null) {
            return "N/A";
        }
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###");

        return formatter.format(amount);
    }

    private static String getObject(ServicePath servicePath) {
        //GET_Object_By_SourcedId
        String[] array = servicePath.toString().split("_");
        return array[1];
    }

    private static String getPredicate(ServicePath servicePath) {
        //GET_Object_By_Predicate_SourcedId
        String[] array = servicePath.toString().split("_");
        return array[3];
    }

    private static String getId(String predicateObject, IdType type) {
        //final int min = 0;
        //final int max = LIMIT - 1;
        //Random random = new Random();
        //int randomNumber = random.nextInt(max - min + 1) + min;

        switch(predicateObject) {
            case "XLEA":
            case "XLEAS":
                switch (type) {
                    case RefId:
                        return xLeas.get(randomNumber).getRefId();
                    case Local:
                        return xLeas.get(randomNumber).getLocalId();
                    case State:
                        return xLeas.get(randomNumber).getStateProvinceId();
                }
            case "XSCHOOL":
            case "XSCHOOLS":
                switch (type) {
                    case RefId:
                        return xSchools.get(randomNumber).getRefId();
                    case Local:
                        return xSchools.get(randomNumber).getLocalId();
                    case State:
                        return xSchools.get(randomNumber).getStateProvinceId();
                }
            case "XCALENDAR":
            case "XCALENDARS":
                return xCalendars.get(randomNumber).getRefId();
            case "XCOURSE":
            case "XCOURSES":
                return xCourses.get(randomNumber).getRefId();
            case "XROSTER":
            case "XROSTERS":
                return xRosters.get(randomNumber).getRefId();
            case "XSTAFF":
            case "XSTAFFS":
                return xStaffs.get(randomNumber).getRefId();
            case "XSTUDENT":
            case "XSTUDENTS":
                return xStudents.get(randomNumber).getRefId();
            case "XCONTACT":
            case "XCONTACTS":
                return xContacts.get(randomNumber).getRefId();
            default: return "NO_ID_FOUND";
        }
    }
}
