import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.Model;
import org.ricone.library.client.core.Util;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.OffsetResponse;
import org.ricone.library.client.oneroster.response.Response;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.client.oneroster.response.model.Class;
import org.ricone.library.exception.InvalidPathException;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OneRosterTest {
    private static int LIMIT = 5;
    private static String DISTRICT_ID = "530501";
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

            test2(oneRoster);
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
        Response<Orgs> response = oneRoster.request(Request.builder()
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
        Response<Orgs> response = oneRoster.request(Request.builder()
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
        Response<AcademicSessions> response = oneRoster.request(Request.builder()
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
        Response<AcademicSessions> response = oneRoster.request(Request.builder()
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
        Response<Courses> response = oneRoster.request(Request.builder()
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
        Response<Classes> response = oneRoster.request(Request.builder()
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
        Response<Enrollments> response = oneRoster.request(Request.builder()
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
        Response<Users> response = oneRoster.request(Request.builder()
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
        Response<Users> response = oneRoster.request(Request.builder()
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
        Response<Users> response = oneRoster.request(Request.builder()
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
        Response<Demographics> response = oneRoster.request(Request.builder()
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

    private static void test2(OneRoster oneRoster) throws InvalidPathException {
        String format = "| %-150s | %-6s | %-9s | %-18s |%n";
        System.out.format("+--------------------------------------------------------------------------------------------------------------------------------------------------------+--------+-----------+--------------------+%n");
        System.out.format("| Request                                                                                                                                                | Status | Size      | Total Record Count |%n");
        System.out.format("+--------------------------------------------------------------------------------------------------------------------------------------------------------+--------+-----------+--------------------+%n");

        for(ServicePath servicePath : ServicePath.values()) {
            /** Request - No Features **/
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                Response responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getObject(servicePath))).end()
                    .end()
                    .build());
                System.out.format(format, responseNoFeatures.getRequestPath(), responseNoFeatures.getResponseStatus(), byteCount(responseNoFeatures.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseNoFeatures.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                Response responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                    .end()
                .build());
                System.out.format(format, responseNoFeatures.getRequestPath(), responseNoFeatures.getResponseStatus(), byteCount(responseNoFeatures.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseNoFeatures.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                Response responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getPredicate(servicePath))).end()
                    .end()
                .build());
                System.out.format(format, responseNoFeatures.getRequestPath(), responseNoFeatures.getResponseStatus(), byteCount(responseNoFeatures.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseNoFeatures.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);
                Response responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(predicates[0]))
                            .id(predicates[1])
                        .end()
                    .end()
                .build());
                System.out.format(format, responseNoFeatures.getRequestPath(), responseNoFeatures.getResponseStatus(), byteCount(responseNoFeatures.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseNoFeatures.getResponseHeaders().getFirst("X-Total-Count")));
            }

            /** Request - Field Selection **/
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                Response responseFieldSelection = oneRoster.request(Request.builder()
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
                System.out.format(format, responseFieldSelection.getRequestPath(), responseFieldSelection.getResponseStatus(), byteCount(responseFieldSelection.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseFieldSelection.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                Response responseFieldSelection = oneRoster.request(Request.builder()
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
                System.out.format(format, responseFieldSelection.getRequestPath(), responseFieldSelection.getResponseStatus(), byteCount(responseFieldSelection.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseFieldSelection.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                Response responseFieldSelection = oneRoster.request(Request.builder()
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
                System.out.format(format, responseFieldSelection.getRequestPath(), responseFieldSelection.getResponseStatus(), byteCount(responseFieldSelection.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseFieldSelection.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);
                Response responseFieldSelection = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(predicates[0]))
                            .id(predicates[1])
                        .end()
                    .end()
                    .with()
                        .fieldSelection()
                            .field(Field.sourcedId)
                            .field(Field.dateLastModified)
                        .end()
                    .end()
                .build());
                System.out.format(format, responseFieldSelection.getRequestPath(), responseFieldSelection.getResponseStatus(), byteCount(responseFieldSelection.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseFieldSelection.getResponseHeaders().getFirst("X-Total-Count")));
            }

            /** Request - Paging **/
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                Response responsePaging = oneRoster.request(Request.builder()
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
                System.out.format(format, responsePaging.getRequestPath(), responsePaging.getResponseStatus(), byteCount(responsePaging.getResponseHeaders().getFirst("Content-Length")), formatNumber(responsePaging.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                Response responsePaging = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                    .end()
                    .with()
                        .paging()
                            .limit(100)
                        .end()
                    .end()
                .build());
                System.out.format(format, responsePaging.getRequestPath(), responsePaging.getResponseStatus(), byteCount(responsePaging.getResponseHeaders().getFirst("Content-Length")), formatNumber(responsePaging.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                Response responsePaging = oneRoster.request(Request.builder()
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
                System.out.format(format, responsePaging.getRequestPath(), responsePaging.getResponseStatus(), byteCount(responsePaging.getResponseHeaders().getFirst("Content-Length")), formatNumber(responsePaging.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);
                Response responsePaging = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(predicates[0]))
                            .id(predicates[1])
                        .end()
                    .end()
                    .with()
                        .paging()
                            .limit(100)
                        .end()
                    .end()
                .build());
                System.out.format(format, responsePaging.getRequestPath(), responsePaging.getResponseStatus(), byteCount(responsePaging.getResponseHeaders().getFirst("Content-Length")), formatNumber(responsePaging.getResponseHeaders().getFirst("X-Total-Count")));
            }


            /** Request - Filtering **/
            if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                Response responseFiltering = oneRoster.request(Request.builder()
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
                System.out.format(format, responseFiltering.getRequestPath(), responseFiltering.getResponseStatus(), byteCount(responseFiltering.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseFiltering.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                Response responseFiltering = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                    .end()
                    .with()
                        .filtering()
                            .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                        .end()
                    .end()
                .build());
                System.out.format(format, responseFiltering.getRequestPath(), responseFiltering.getResponseStatus(), byteCount(responseFiltering.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseFiltering.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                Response responseFiltering = oneRoster.request(Request.builder()
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
                System.out.format(format, responseFiltering.getRequestPath(), responseFiltering.getResponseStatus(), byteCount(responseFiltering.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseFiltering.getResponseHeaders().getFirst("X-Total-Count")));
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);
                Response responseFiltering = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(predicates[0]))
                            .id(predicates[1])
                        .end()
                    .end()
                    .with()
                        .filtering()
                            .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                        .end()
                    .end()
                .build());
                System.out.format(format, responseFiltering.getRequestPath(), responseFiltering.getResponseStatus(), byteCount(responseFiltering.getResponseHeaders().getFirst("Content-Length")), formatNumber(responseFiltering.getResponseHeaders().getFirst("X-Total-Count")));
            }


            //Separate the next service path
            System.out.format("+--------------------------------------------------------------------------------------------------------------------------------------------------------+--------+-----------+--------------------+%n");
        }


    }

    //Helper Methods
    private static void showResults(Response<? extends Model> response) {
        //System.out.println("Request: " + response.getRequestPath() + " | Status: " + response.getResponseStatus() + " | HasData: " + response.getData().hasData());
    }

    private static String byteCount(String stringBytes) {
        if(stringBytes == null) {
            return null;
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
        return new String[] {array[5], array[3]};
    }

    private static String getId(String predicateObject) {
        switch(predicateObject) {
            case "Org": return orgs.get(0).getSourcedId();
            case "School": return schools.get(0).getSourcedId();
            case "AcademicSession": return academicSessions.get(0).getSourcedId();
            case "Term": return terms.get(0).getSourcedId();
            case "Course": return courses.get(0).getSourcedId();
            case "Class": return classes.get(0).getSourcedId();
            case "Enrollment": return enrollments.get(0).getSourcedId();
            case "User": return users.get(0).getSourcedId();
            case "Teacher": return teachers.get(0).getSourcedId();
            case "Student": return students.get(0).getSourcedId();
            case "Demographic": return demographics.get(0).getSourcedId();
            default: return "NO_ID_FOUND";
        }
    }
}
