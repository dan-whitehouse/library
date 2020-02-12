import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Model;
import org.ricone.library.client.oneroster.request.*;
//import org.ricone.library.client.oneroster.response.Response;
import org.ricone.library.client.oneroster.response.model.Class;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.exception.InvalidPathException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SuppressWarnings("deprecation")
public class OneRosterTest {
    private static final int LIMIT = 10;
    private static final String DISTRICT_ID = "530501";
    private static final String FORMAT = "| %-200s | %-6s | %-9s | %-18s |%n";
    private static final Random random = new Random();
    private static final int randomNumber = random.nextInt(((LIMIT - 1) - 0 + 1) + 0); // 0 = min, LIMIT = max

    private static List<Org> orgs = new ArrayList<>();
    private static List<Org> schools = new ArrayList<>();
    private static List<AcademicSession> academicSessions = new ArrayList<>();
    private static List<AcademicSession> terms = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Class> classes = new ArrayList<>();
    private static List<Enrollment> enrollments = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<User> teachers = new ArrayList<>();
    private static List<User> students = new ArrayList<>();
    private static List<Demographic> demographics = new ArrayList<>();

    public static void main(String[] args) throws InvalidPathException {
        Authenticator authenticator = Authenticator.builder()
            .url(System.getenv("url")).api(API.OneRoster)
            .credentials(System.getenv("username"), System.getenv("password"))
            .provider(System.getenv("provider"))
        .authenticate();

        Optional<Endpoint> endpoint = authenticator.getEndpoint("localhost");
        if(endpoint.isPresent()) {
            OneRoster oneRoster = new OneRoster(endpoint.get());

            //Add values to the lists, so subsequent calls can be made using their ids.
            loadLists(oneRoster);

            runTests(oneRoster);
        }
    }

    private static void loadLists(OneRoster oneRoster) throws InvalidPathException {
        orgs = loadOrgs(oneRoster);
        schools = loadSchools(oneRoster);
        academicSessions = loadAcademicSessions(oneRoster);
        terms = loadTerms(oneRoster);
        courses = loadCourses(oneRoster);
        classes = loadClasses(oneRoster);
        enrollments = loadEnrollments(oneRoster);
        users = loadUsers(oneRoster);
        teachers = loadTeachers(oneRoster);
        students = loadStudents(oneRoster);
        demographics = loadDemographics(oneRoster);
    }

    private static List<Org> loadOrgs(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
            .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getOrgs();
    }

    private static List<Org> loadSchools(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
            .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getOrgs();
    }

    private static List<AcademicSession> loadAcademicSessions(OneRoster oneRoster) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_AcademicSessions)
            .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getAcademicSessions();
    }

    private static List<AcademicSession> loadTerms(OneRoster oneRoster) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Terms)
            .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getAcademicSessions();
    }

    private static List<Course> loadCourses(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Courses)
                .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getCourses();
    }

    private static List<Class> loadClasses(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Classes)
                .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getClasses();
    }

    private static List<Enrollment> loadEnrollments(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Enrollments)
            .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getEnrollments();
    }

    private static List<User> loadUsers(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Users)
            .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getUsers();
    }

    private static List<User> loadTeachers(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Teachers)
            .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getUsers();
    }

    private static List<User> loadStudents(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Students)
            .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getUsers();
    }

    private static List<Demographic> loadDemographics(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Demographics> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Demographics)
            .end()
            .with()
                .paging()
                    .limit(LIMIT)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .filtering().filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID).end()
            .end()
        .build());

        showResults(response);

        return response.getData().getDemographics();
    }

    private static void runTests(OneRoster oneRoster) throws InvalidPathException {

        printTableBorder();
        System.out.format("| Request                                                                                                                                                                                                  | Status | Size      | Total Record Count |%n");
        printTableBorder();

        for(ServicePath servicePath : ServicePath.values()) {
            /* Request - No Features */
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                IResponse<? extends Model> responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getObject(servicePath))).end()
                    .end()
                    .build());
                printTableRow(responseNoFeatures);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                IResponse<? extends Model> responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                    .end()
                .build());
                printTableRow(responseNoFeatures);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                IResponse<? extends Model> responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getPredicate(servicePath))).end()
                    .end()
                .build());
                printTableRow(responseNoFeatures);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);
                IResponse<? extends Model> responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(predicates[0]))
                            .id(getId(predicates[1]))
                        .end()
                    .end()
                .build());
                printTableRow(responseNoFeatures);
            }

            /* Request - Field Selection */
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                IResponse<? extends Model> responseFieldSelection = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getObject(servicePath))).end()
                    .end()
                    .with()
                        .fieldSelection()
                            .field(Field.sourcedId)
                            .field(Field.dateLastModified)
                        .end()
                    .end()
                .build());
                printTableRow(responseFieldSelection);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                IResponse<? extends Model> responseFieldSelection = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                    .end()
                    .with()
                        .fieldSelection()
                            .field(Field.sourcedId)
                            .field(Field.dateLastModified)
                        .end()
                    .end()
                .build());
                printTableRow(responseFieldSelection);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                IResponse<? extends Model> responseFieldSelection = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getPredicate(servicePath))).end()
                    .end()
                    .with()
                        .fieldSelection()
                            .field(Field.sourcedId)
                            .field(Field.dateLastModified)
                        .end()
                    .end()
                .build());
                printTableRow(responseFieldSelection);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);
                IResponse<? extends Model> responseFieldSelection = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(predicates[0]))
                            .id(getId(predicates[1]))
                        .end()
                    .end()
                    .with()
                        .fieldSelection()
                            .field(Field.sourcedId)
                            .field(Field.dateLastModified)
                        .end()
                    .end()
                .build());
                printTableRow(responseFieldSelection);
            }

            /* Request - Paging */
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                IResponse<? extends Model> responsePaging = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getObject(servicePath))).end()
                    .end()
                    .with()
                        .paging()
                            .limit(100)
                        .end()
                    .end()
                .build());
                printTableRow(responsePaging);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                IResponse<? extends Model> responsePaging = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                    .end()
                    .with()
                        .paging()
                            .limit(100)
                        .end()
                    .end()
                .build());
                printTableRow(responsePaging);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                IResponse<? extends Model> responsePaging = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getPredicate(servicePath))).end()
                    .end()
                    .with()
                        .paging()
                            .limit(100)
                        .end()
                    .end()
                .build());
                printTableRow(responsePaging);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);
                IResponse<? extends Model> responsePaging = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(predicates[0]))
                            .id(getId(predicates[1]))
                        .end()
                    .end()
                    .with()
                        .paging()
                            .limit(100)
                        .end()
                    .end()
                .build());
                printTableRow(responsePaging);
            }

            /* Request - Filtering */
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                IResponse<? extends Model> responseFiltering = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getObject(servicePath))).end()
                    .end()
                    .with()
                        .filtering()
                            .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                        .end()
                    .end()
                .build());
                printTableRow(responseFiltering);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                IResponse<? extends Model> responseFiltering = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                    .end()
                    .with()
                        .filtering()
                            .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                        .end()
                    .end()
                .build());
                printTableRow(responseFiltering);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                IResponse<? extends Model> responseFiltering = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getPredicate(servicePath))).end()
                    .end()
                    .with()
                        .filtering()
                            .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                        .end()
                    .end()
                .build());
                printTableRow(responseFiltering);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);
                IResponse<? extends Model> responseFiltering = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(predicates[0]))
                            .id(getId(predicates[1]))
                        .end()
                    .end()
                    .with()
                        .filtering()
                            .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                        .end()
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
        System.out.format("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+--------+-----------+--------------------+%n");
    }

    private static void printTableRow(IResponse<? extends Model> response) {
        System.out.format(FORMAT, trimUrl(response.getRequestPath()), response.getResponseStatus(), byteCount(response.getResponseHeaders().getFirst("Content-Length")), formatNumber(response.getResponseHeaders().getFirst("X-Total-Count")));
    }

    private static String trimUrl(String url) {
        return url.replace("http://localhost:8080/api/ims/oneroster/v1p1","");
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

    private static String[] getPredicates(ServicePath servicePath) {
        /*
            Return them in reverse order. The enum suggests the class id should be entered first into the request builder,
            even though the url is the reverse.

            Enum: GET_Students_By_Class_SourcedId_With_School_SourcedId
            Url:  schools/{id}/classes/{id}/students
         */

        String[] array = servicePath.toString().split("_");
        return new String[] {array[6], array[3]};
    }

    private static String getId(String predicateObject) {
        //final int min = 0;
        //final int max = LIMIT - 1;
        //Random random = new Random();
        //int randomNumber = random.nextInt(max - min + 1) + min;

        switch(predicateObject) {
            case "Org": return orgs.get(randomNumber).getSourcedId();
            case "School":
            case "Schools":
                return schools.get(randomNumber).getSourcedId();
            case "AcademicSession": return academicSessions.get(randomNumber).getSourcedId();
            case "Term": return terms.get(randomNumber).getSourcedId();
            case "Course": return courses.get(randomNumber).getSourcedId();
            case "Class": return classes.get(randomNumber).getSourcedId();
            case "Enrollment": return enrollments.get(randomNumber).getSourcedId();
            case "User": return users.get(randomNumber).getSourcedId();
            case "Teacher": return teachers.get(randomNumber).getSourcedId();
            case "Student": return students.get(randomNumber).getSourcedId();
            case "Demographic": return demographics.get(randomNumber).getSourcedId();
            default: return "NO_ID_FOUND";
        }
    }
}
