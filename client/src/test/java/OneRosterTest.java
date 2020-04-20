import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Model;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.request.FieldClassType;
import org.ricone.library.client.oneroster.response.*;
import org.ricone.library.client.oneroster.response.model.Class;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.exception.InvalidPathException;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

@SuppressWarnings("deprecation")
public class OneRosterTest {
    private static final int LIMIT = 10;
    private static final String DISTRICT_ID = "530501";
    private static final String FORMAT = "| %-260s | %-6s | %-9s | %-18s | %-9s |%n";
    private static final Random random = new Random();
    private static final int randomNumber = 0; //random.nextInt(((LIMIT - 1) - 0 + 1) + 0); // 0 = min, LIMIT = max

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

    private static HashMap<ServicePath, List<String>> pathRefIds = new HashMap<>();


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
                .filtering()
                    .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                    .and()
                    .filter(Field.Orgs.type, Predicate.Equals, "school")
                .end()
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
                .filtering()
                    .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                    .and()
                    .filter(Field.AcademicSessions.type, Predicate.Equals, "term")
                .end()
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
                .filtering()
                    .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                    .and()
                    .filter(Field.Users.role, Predicate.Equals, "teacher")
                .end()
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
                .filtering()
                    .filter(Field.Metadata.ricone_districtId, Predicate.Equals, DISTRICT_ID)
                    .and()
                    .filter(Field.Users.role, Predicate.Equals, "student")
                .end()
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
        System.out.format("| Request                                                                                                                                                                                                                                                              | Status | Size      | Total Record Count | Duration  |%n");
        printTableBorder();

        for(ServicePath servicePath : ServicePath.values()) {
            /** Request - No Features **/
            IResponse<? extends Model> responseNoFeatures;
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getObject(servicePath))).end()
                    .end()
                    .build());
                printTableRow(responseNoFeatures);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                    .end()
                .build());
                printTableRow(responseNoFeatures);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                responseNoFeatures = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids().id(getId(getPredicate(servicePath))).end()
                    .end()
                .build());
                printTableRow(responseNoFeatures);
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);
                responseNoFeatures = oneRoster.request(Request.builder()
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

            /** Request - Sorting **/
            for(IField field : getFields(servicePath)) { //Field
                for(SortOrder sortOrder : SortOrder.values()) {  //Asc and Desc
                    IResponse<? extends Model> responseFieldSelection;
                    if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                        responseFieldSelection = oneRoster.request(Request.builder()
                            .request()
                                .path(servicePath)
                                .ids()
                                    .id(getId(getObject(servicePath)))
                                .end()
                            .end()
                            .with()
                                .sorting()
                                    .field(field)
                                    .orderBy(sortOrder)
                                .end()
                            .end()
                        .build());
                        printTableRow(responseFieldSelection);
                    }
                    else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                        responseFieldSelection = oneRoster.request(Request.builder()
                            .request()
                                .path(servicePath)
                            .end()
                            .with()
                                .sorting()
                                    .field(field)
                                    .orderBy(sortOrder)
                                .end()
                            .end()
                        .build());
                        printTableRow(responseFieldSelection);
                    }
                    else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                        responseFieldSelection = oneRoster.request(Request.builder()
                            .request()
                                .path(servicePath)
                                .ids()
                                    .id(getId(getPredicate(servicePath)))
                                .end()
                            .end()
                            .with()
                                .sorting()
                                    .field(field)
                                    .orderBy(sortOrder)
                                .end()
                            .end()
                        .build());
                        printTableRow(responseFieldSelection);
                    }
                    else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                        String[] predicates = getPredicates(servicePath);
                        responseFieldSelection = oneRoster.request(Request.builder()
                            .request()
                                .path(servicePath)
                                .ids()
                                    .id(getId(predicates[0]))
                                    .id(getId(predicates[1]))
                                .end()
                            .end()
                            .with()
                                .sorting()
                                    .field(field)
                                    .orderBy(sortOrder)
                                .end()
                            .end()
                        .build());
                        printTableRow(responseFieldSelection);
                    }
                }
            }

            /** Request - Field Selection **/
            for(IField field : getFields(servicePath)) {
                IResponse<? extends Model> responseFieldSelection;
                if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                    responseFieldSelection = oneRoster.request(Request.builder()
                        .request()
                            .path(servicePath)
                            .ids()
                                .id(getId(getObject(servicePath)))
                            .end()
                        .end()
                        .with()
                            .fieldSelection()
                                .field(field)
                            .end()
                        .end()
                    .build());
                    printTableRow(responseFieldSelection);
                }
                else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                    responseFieldSelection = oneRoster.request(Request.builder()
                        .request()
                            .path(servicePath)
                        .end()
                        .with()
                            .fieldSelection()
                                .field(field)
                            .end()
                        .end()
                    .build());
                    printTableRow(responseFieldSelection);
                }
                else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                    responseFieldSelection = oneRoster.request(Request.builder()
                        .request()
                            .path(servicePath)
                            .ids()
                                .id(getId(getPredicate(servicePath)))
                            .end()
                        .end()
                        .with()
                            .fieldSelection()
                                .field(field)
                            .end()
                        .end()
                    .build());
                    printTableRow(responseFieldSelection);
                }
                else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                    String[] predicates = getPredicates(servicePath);
                    responseFieldSelection = oneRoster.request(Request.builder()
                        .request()
                            .path(servicePath)
                            .ids()
                                .id(getId(predicates[0]))
                                .id(getId(predicates[1]))
                            .end()
                        .end()
                        .with()
                            .fieldSelection()
                                .field(field)
                            .end()
                        .end()
                    .build());
                    printTableRow(responseFieldSelection);
                }
            }

            /** Request - Paging **/
            final int limit = 500;
            if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                OffsetResponse offset = oneRoster.getOffset(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(getObject(servicePath)))
                        .end()
                    .end()
                    .with()
                        .paging()
                            .limit(limit)
                        .end()
                    .end()
                .build());

                while(offset.hasNext()) {
                    IResponse<? extends Model> responsePaging = oneRoster.request(Request.builder()
                        .request()
                            .path(servicePath)
                            .ids()
                                .id(getId(getObject(servicePath)))
                            .end()
                        .end()
                        .with()
                            .paging()
                                .limit(limit)
                                .offset(offset.next())
                            .end()
                        .end()
                    .build());
                    printTableRow(responsePaging);
                }
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                OffsetResponse offset = oneRoster.getOffset(Request.builder()
                    .request()
                        .path(servicePath)
                        .end()
                    .with()
                        .paging()
                            .limit(limit)
                        .end()
                    .end()
                .build());

                while(offset.hasNext()) {
                    IResponse<? extends Model> responsePaging = oneRoster.request(Request.builder()
                        .request()
                            .path(servicePath)
                        .end()
                        .with()
                            .paging()
                                .limit(limit)
                                .offset(offset.next())
                            .end()
                        .end()
                    .build());
                    printTableRow(responsePaging);
                }
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                OffsetResponse offset = oneRoster.getOffset(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(getPredicate(servicePath)))
                        .end()
                    .end()
                    .with()
                        .paging()
                            .limit(limit)
                        .end()
                    .end()
                .build());

                while(offset.hasNext()) {
                    IResponse<? extends Model> responsePaging = oneRoster.request(Request.builder()
                        .request()
                            .path(servicePath)
                            .ids()
                                .id(getId(getPredicate(servicePath)))
                            .end()
                        .end()
                        .with()
                            .paging()
                                .limit(limit)
                                .offset(offset.next())
                            .end()
                        .end()
                    .build());
                    printTableRow(responsePaging);
                }
            }
            else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                String[] predicates = getPredicates(servicePath);

                OffsetResponse offset = oneRoster.getOffset(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getId(predicates[0]))
                            .id(getId(predicates[1]))
                        .end()
                    .end()
                    .with()
                        .paging()
                            .limit(limit)
                        .end()
                    .end()
                .build());

                while(offset.hasNext()) {
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
                                .limit(limit)
                                .offset(offset.next())
                            .end()
                        .end()
                    .build());
                    printTableRow(responsePaging);
                }
            }

            /** Request - Filtering **/
            for(IField field : getFields(servicePath)) { //Field
                for(Predicate predicate : Predicate.values()) { //Predicates
                    IResponse<? extends Model> responseFiltering;

                    boolean isTestable = isFilterPredicateTestable(field, predicate);
                    if(isTestable) {
                        Filter filter = new Filter(field, predicate, getFieldValue(field, predicate, servicePath.getFieldType()));

                        if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
                            responseFiltering = oneRoster.request(Request.builder()
                                .request()
                                    .path(servicePath)
                                    .ids()
                                        .id(getId(getObject(servicePath)))
                                    .end()
                                .end()
                                .with()
                                    .filtering()
                                        .filter(filter)
                                    .end()
                                .end()
                            .build());
                            printTableRow(responseFiltering);
                        }
                        else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
                            responseFiltering = oneRoster.request(Request.builder()
                                .request()
                                    .path(servicePath)
                                .end()
                                .with()
                                    .filtering()
                                    .   filter(filter)
                                    .end()
                                .end()
                            .build());
                            printTableRow(responseFiltering);
                        }
                        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
                            responseFiltering = oneRoster.request(Request.builder()
                                .request()
                                    .path(servicePath)
                                    .ids()
                                        .id(getId(getPredicate(servicePath)))
                                    .end()
                                .end()
                                .with()
                                    .filtering()
                                        .filter(filter)
                                    .end()
                                .end()
                            .build());
                            printTableRow(responseFiltering);
                        }
                        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
                            String[] predicates = getPredicates(servicePath);
                            responseFiltering = oneRoster.request(Request.builder()
                                .request()
                                    .path(servicePath)
                                    .ids()
                                        .id(getId(predicates[0]))
                                        .id(getId(predicates[1]))
                                    .end()
                                .end()
                                .with()
                                    .filtering()
                                        .filter(filter)
                                    .end()
                                .end()
                            .build());
                            printTableRow(responseFiltering);
                        }
                    }
                }
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
        System.out.format("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+--------+-----------+--------------------+-----------+%n");
    }

    private static void printTableRow(IResponse<? extends Model> response) {
        System.out.format(FORMAT,
            response.getRequestPath(),
            response.getResponseStatus(),
            byteCount(response.getResponseHeaders().getFirst("Content-Length")),
            formatNumber(response.getResponseHeaders().getFirst("X-Record-Count")),
            response.getResponseHeaders().getFirst("X-Duration")
        );
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

    private static List<IField> getFields(ServicePath servicePath) {
        List<IField> fields = new ArrayList<>();
        fields.add(Field.sourcedId);
        fields.add(Field.dateLastModified);

        if(FieldType.Orgs.equals(servicePath.getFieldType())) {
            fields.addAll(Arrays.asList(Field.Orgs.values()));
            fields.addAll(Arrays.asList(Field.Orgs.Parent.values()));
            fields.addAll(Arrays.asList(Field.Orgs.Children.values()));
        }
        else if(FieldType.AcademicSessions.equals(servicePath.getFieldType())) {
            fields.addAll(Arrays.asList(Field.AcademicSessions.values()));
            fields.addAll(Arrays.asList(Field.AcademicSessions.Parent.values()));
            fields.addAll(Arrays.asList(Field.AcademicSessions.Children.values()));
        }
        else if(FieldType.Courses.equals(servicePath.getFieldType())) {
            fields.addAll(Arrays.asList(Field.Courses.values()));
            fields.addAll(Arrays.asList(Field.Courses.Org.values()));
            fields.addAll(Arrays.asList(Field.Courses.SchoolYear.values()));
        }
        else if(FieldType.Classes.equals(servicePath.getFieldType())) {
            fields.addAll(Arrays.asList(Field.Classes.values()));
            fields.addAll(Arrays.asList(Field.Classes.Course.values()));
            fields.addAll(Arrays.asList(Field.Classes.School.values()));
            fields.addAll(Arrays.asList(Field.Classes.Terms.values()));
        }
        else if(FieldType.Enrollments.equals(servicePath.getFieldType())) {
            fields.addAll(Arrays.asList(Field.Enrollments.values()));
            fields.addAll(Arrays.asList(Field.Enrollments.School.values()));
            fields.addAll(Arrays.asList(Field.Enrollments.Clazz.values()));
            fields.addAll(Arrays.asList(Field.Enrollments.User.values()));
        }
        else if(FieldType.Users.equals(servicePath.getFieldType())) {
            fields.addAll(Arrays.asList(Field.Users.values()));
            fields.addAll(Arrays.asList(Field.Users.Agents.values()));
            fields.addAll(Arrays.asList(Field.Users.Orgs.values()));
            fields.addAll(Arrays.asList(Field.Users.UserIds.values()));
        }
        else if(FieldType.Demographics.equals(servicePath.getFieldType())) {
            fields.addAll(Arrays.asList(Field.Demographics.values()));
        }
        return fields;
    }

    private static String getFieldValue(IField field, Predicate predicate, FieldType fieldType) {

        if(predicate.equals(Predicate.Equals) || predicate.equals(Predicate.Contains)) {
            return getFieldValue(field, fieldType);
        }
        else if(predicate.equals(Predicate.NotEquals)) {
            if(field.getFieldClassType().equals(FieldClassType.Number)) {
                return "0";
            }
            else if(field.getFieldClassType().equals(FieldClassType.Date)) {
                return LocalDateTime.now().toString();
            }
            else if(field.getFieldClassType().equals(FieldClassType.Boolean)) {
                return "false";
            }
            else {
                return "NOT_THIS_VALUE";
            }
        }
        else if(predicate.equals(Predicate.GreaterThan) || predicate.equals(Predicate.GreaterThanOrEqual) || predicate.equals(Predicate.LessThan) || predicate.equals(Predicate.LessThanOrEqual)) {
            if(field.getFieldClassType().equals(FieldClassType.Number) || field.getFieldClassType().equals(FieldClassType.Date)) {
                return getFieldValue(field, fieldType);
            }
            else {
                return "PREDICATE_NOT_ALLOWED_ON_FIELD";
            }
        }
        else {
            return "_HUH_";
        }
    }

    private static boolean isFilterPredicateTestable(IField field, Predicate predicate) {
        if(predicate.equals(Predicate.Equals) || predicate.equals(Predicate.NotEquals)) {
            return true;
        }
        if(predicate.equals(Predicate.Contains)) {
            return field.getFieldClassType().equals(FieldClassType.String) || field.getFieldClassType().equals(FieldClassType.Number) || field.getFieldClassType().equals(FieldClassType.Boolean);
        }
        else if(predicate.equals(Predicate.GreaterThan) || predicate.equals(Predicate.GreaterThanOrEqual) || predicate.equals(Predicate.LessThan) || predicate.equals(Predicate.LessThanOrEqual)) {
            return field.getFieldClassType().equals(FieldClassType.Number) || field.getFieldClassType().equals(FieldClassType.Date);
        }
        return false;
    }


    public static String getFieldValue(IField field, FieldType fieldType) {
        String value = "NO_VALUE";

        if(fieldType.equals(FieldType.Orgs)) { //Orgs
            Org instance =  orgs.get(0);

            if(field.equals(Field.sourcedId)) {
                value = instance.getSourcedId();
            }
            else if(field.equals(Field.dateLastModified)) {
                if(instance.getDateLastModified() != null) {
                    value = instance.getDateLastModified().toString();
                }
            }
            else if(field.equals(Field.status)) {
                if(instance.getStatus() != null) {
                    value = instance.getStatus().getValue();
                }
            }
            else if(field.equals(Field.Orgs.name)) {
                value = instance.getName();
            }
            else if(field.equals(Field.Orgs.type)) {
                if(instance.getType() != null) {
                    value = instance.getType().getValue();
                }
            }
            else if(field.equals(Field.Orgs.identifier)) {
                value = instance.getIdentifier();
            }
            else if(field.equals(Field.Orgs.Parent.sourcedId)) {
                if(instance.getParent() != null) {
                    value = instance.getParent().getSourcedId();
                }
            }
            else if(field.equals(Field.Orgs.Parent.type)) {
                if(instance.getParent() != null) {
                    value = instance.getParent().getType().getValue();
                }
            }
            else if(field.equals(Field.Orgs.Parent.href)) {
                if(instance.getParent() != null) {
                    value = instance.getParent().getHref();
                }
            }
            else if(field.equals(Field.Orgs.Children.sourcedId)) {
                if(!instance.getChildren().isEmpty()) {
                    value = instance.getChildren().get(0).getSourcedId();
                }
            }
            else if(field.equals(Field.Orgs.Children.type)) {
                if(!instance.getChildren().isEmpty()) {
                    value = instance.getChildren().get(0).getType().getValue();
                }
            }
            else if(field.equals(Field.Orgs.Children.href)) {
                if(!instance.getChildren().isEmpty()) {
                    value = instance.getChildren().get(0).getHref();
                }
            }
        }
        else if(fieldType.equals(FieldType.AcademicSessions)) {
            AcademicSession instance =  academicSessions.get(0);

            if(field.equals(Field.sourcedId)) {
                value = instance.getSourcedId();
            }
            else if(field.equals(Field.dateLastModified)) {
                if(instance.getDateLastModified() != null) {
                    value = instance.getDateLastModified().toString();
                }
            }
            else if(field.equals(Field.status)) {
                if(instance.getStatus() != null) {
                    value = instance.getStatus().getValue();
                }
            }
            else if(field.equals(Field.AcademicSessions.title)) {
                value = instance.getTitle();
            }
            else if(field.equals(Field.AcademicSessions.startDate)) {
                if(instance.getStartDate() != null) {
                    value = instance.getStartDate().toString();
                }
            }
            else if(field.equals(Field.AcademicSessions.endDate)) {
                if(instance.getEndDate() != null) {
                    value = instance.getEndDate().toString();
                }
            }
            else if(field.equals(Field.AcademicSessions.Parent.sourcedId)) {
                if(instance.getParent() != null) {
                    value = instance.getParent().getSourcedId();
                }
            }
            else if(field.equals(Field.AcademicSessions.Parent.type)) {
                if(instance.getParent() != null) {
                    value = instance.getParent().getType().getValue();
                }
            }
            else if(field.equals(Field.AcademicSessions.Parent.href)) {
                if(instance.getParent() != null) {
                    value = instance.getParent().getHref();
                }
            }
            else if(field.equals(Field.AcademicSessions.Children.sourcedId)) {
                if(!instance.getChildren().isEmpty()) {
                    value = instance.getChildren().get(0).getSourcedId();
                }
            }
            else if(field.equals(Field.AcademicSessions.Children.type)) {
                if(!instance.getChildren().isEmpty()) {
                    value = instance.getChildren().get(0).getType().getValue();
                }
            }
            else if(field.equals(Field.AcademicSessions.Children.href)) {
                if(!instance.getChildren().isEmpty()) {
                    value = instance.getChildren().get(0).getHref();
                }
            }
            //TODO: Does school year exist in spec?
        }
        else if(fieldType.equals(FieldType.Courses)) {
            Course instance =  courses.get(0);

            if(field.equals(Field.sourcedId)) {
                value = instance.getSourcedId();
            }
            else if(field.equals(Field.dateLastModified)) {
                if(instance.getDateLastModified() != null) {
                    value = instance.getDateLastModified().toString();
                }
            }
            else if(field.equals(Field.status)) {
                if(instance.getStatus() != null) {
                    value = instance.getStatus().getValue();
                }
            }
            else if(field.equals(Field.Courses.title)) {
                value = instance.getTitle();
            }
            else if(field.equals(Field.Courses.SchoolYear.sourcedId)) {
                if(instance.getSchoolYear() != null) {
                    value = instance.getSchoolYear().getSourcedId();
                }
            }
            else if(field.equals(Field.Courses.SchoolYear.type)) {
                if(instance.getSchoolYear() != null) {
                    value = instance.getSchoolYear().getType().getValue();
                }
            }
            else if(field.equals(Field.Courses.SchoolYear.href)) {
                if(instance.getSchoolYear() != null) {
                    value = instance.getSchoolYear().getHref();
                }
            }
            else if(field.equals(Field.Courses.courseCode)) {
                value = instance.getCourseCode();
            }
            else if(field.equals(Field.Courses.grades)) {
                if(!instance.getGrades().isEmpty()) {
                    value = instance.getGrades().get(0);
                }
            }
            else if(field.equals(Field.Courses.subjects)) {
                if(!instance.getSubjects().isEmpty()) {
                    value = instance.getSubjects().get(0);
                }
            }
            else if(field.equals(Field.Courses.Org.sourcedId)) {
                if(instance.getOrg() != null) {
                    value = instance.getOrg().getSourcedId();
                }
            }
            else if(field.equals(Field.Courses.Org.type)) {
                if(instance.getOrg() != null) {
                    value = instance.getOrg().getType().getValue();
                }
            }
            else if(field.equals(Field.Courses.Org.href)) {
                if(instance.getOrg() != null) {
                    value = instance.getOrg().getHref();
                }
            }
            else if(field.equals(Field.Courses.subjectCodes)) {
                if(!instance.getSubjectCodes().isEmpty()) {
                    value = instance.getSubjectCodes().get(0);
                }
            }
        }
        else if(fieldType.equals(FieldType.Classes)) {
            Class instance =  classes.get(0);

            if(field.equals(Field.sourcedId)) {
                value = instance.getSourcedId();
            }
            else if(field.equals(Field.dateLastModified)) {
                if(instance.getDateLastModified() != null) {
                    value = instance.getDateLastModified().toString();
                }
            }
            else if(field.equals(Field.status)) {
                if(instance.getStatus() != null) {
                    value = instance.getStatus().getValue();
                }
            }
            else if(field.equals(Field.Classes.title)) {
                value = instance.getTitle();
            }
            else if(field.equals(Field.Classes.classCode)) {
                value = instance.getClassCode();
            }
            else if(field.equals(Field.Classes.classType)) {
                if(instance.getClassType() != null) {
                    value = instance.getClassType().getValue();
                }
            }
            else if(field.equals(Field.Classes.location)) {
                value = instance.getLocation();
            }
            else if(field.equals(Field.Classes.grades)) {
                if(!instance.getGrades().isEmpty()) {
                    value = instance.getGrades().get(0);
                }
            }
            else if(field.equals(Field.Classes.subjects)) {
                if(!instance.getSubjects().isEmpty()) {
                    value = instance.getSubjects().get(0);
                }
            }
            else if(field.equals(Field.Classes.Terms.sourcedId)) {
                if(!instance.getTerms().isEmpty()) {
                    value = instance.getTerms().get(0).getSourcedId();
                }
            }
            else if(field.equals(Field.Classes.Terms.type)) {
                if(!instance.getTerms().isEmpty()) {
                    value = instance.getTerms().get(0).getType().getValue();
                }
            }
            else if(field.equals(Field.Classes.Terms.href)) {
                if(!instance.getTerms().isEmpty()) {
                    value = instance.getTerms().get(0).getHref();
                }
            }
            else if(field.equals(Field.Classes.subjectCodes)) {
                if(!instance.getSubjectCodes().isEmpty()) {
                    value = instance.getSubjectCodes().get(0);
                }
            }
            else if(field.equals(Field.Classes.periods)) {
                if(!instance.getPeriods().isEmpty()) {
                    value = instance.getPeriods().get(0);
                }
            }
        }
        else if(fieldType.equals(FieldType.Enrollments)) {
            Enrollment instance =  enrollments.get(0);

            if(field.equals(Field.sourcedId)) {
                value = instance.getSourcedId();
            }
            else if(field.equals(Field.dateLastModified)) {
                if(instance.getDateLastModified() != null) {
                    value = instance.getDateLastModified().toString();
                }
            }
            else if(field.equals(Field.status)) {
                if(instance.getStatus() != null) {
                    value = instance.getStatus().getValue();
                }
            }
            else if(field.equals(Field.Enrollments.role)) {
                if(instance.getRole() != null) {
                    value = instance.getRole().getValue();
                }
            }
            else if(field.equals(Field.Enrollments.primary)) {
                if(instance.getPrimary() != null) {
                    value = instance.getPrimary().toString();
                }
            }
            else if(field.equals(Field.Enrollments.User.sourcedId)) {
                if(instance.getUser() != null) {
                    value = instance.getUser().getSourcedId();
                }
            }
            else if(field.equals(Field.Enrollments.User.type)) {
                if(instance.getUser() != null) {
                    value = instance.getUser().getType().getValue();
                }
            }
            else if(field.equals(Field.Enrollments.User.href)) {
                if(instance.getUser() != null) {
                    value = instance.getUser().getHref();
                }
            }
            else if(field.equals(Field.Enrollments.Clazz.sourcedId)) {
                if(instance.getClass_() != null) {
                    value = instance.getClass_().getSourcedId();
                }
            }
            else if(field.equals(Field.Enrollments.Clazz.type)) {
                if(instance.getClass_() != null) {
                    value = instance.getClass_().getType().getValue();
                }
            }
            else if(field.equals(Field.Enrollments.Clazz.href)) {
                if(instance.getClass_() != null) {
                    value = instance.getClass_().getHref();
                }
            }
            else if(field.equals(Field.Enrollments.School.sourcedId)) {
                if(instance.getSchool() != null) {
                    value = instance.getSchool().getSourcedId();
                }
            }
            else if(field.equals(Field.Enrollments.School.type)) {
                if(instance.getSchool() != null) {
                    value = instance.getSchool().getType().getValue();
                }
            }
            else if(field.equals(Field.Enrollments.School.href)) {
                if(instance.getSchool() != null) {
                    value = instance.getSchool().getHref();
                }
            }
            else if(field.equals(Field.Enrollments.beginDate)) {
                if(instance.getBeginDate() != null) {
                    value = instance.getBeginDate().toString();
                }
            }
            else if(field.equals(Field.Enrollments.endDate)) {
                if(instance.getEndDate() != null) {
                    value = instance.getEndDate().toString();
                }
            }
        }
        else if(fieldType.equals(FieldType.Users)) {
            User instance =  users.get(0);

            if(field.equals(Field.sourcedId)) {
                value = instance.getSourcedId();
            }
            else if(field.equals(Field.dateLastModified)) {
                if(instance.getDateLastModified() != null) {
                    value = instance.getDateLastModified().toString();
                }
            }
            else if(field.equals(Field.status)) {
                if(instance.getStatus() != null) {
                    value = instance.getStatus().getValue();
                }
            }
            else if(field.equals(Field.Enrollments.role)) {
                if(instance.getRole() != null) {
                    value = instance.getRole().getValue();
                }
            }
            else if(field.equals(Field.Users.username)) {
                value = instance.getUsername();
            }
            else if(field.equals(Field.Users.UserIds.type)) {
                if(!instance.getUserIds().isEmpty()) {
                    value = instance.getUserIds().get(0).getType();
                }
            }
            else if(field.equals(Field.Users.UserIds.identifier)) {
                if(!instance.getUserIds().isEmpty()) {
                    value = instance.getUserIds().get(0).getIdentifier();
                }
            }
            else if(field.equals(Field.Users.enabledUser)) {
                if(instance.getEnabledUser() != null) {
                    value = instance.getEnabledUser().toString();
                }
            }
            else if(field.equals(Field.Users.givenName)) {
                value = instance.getGivenName();
            }
            else if(field.equals(Field.Users.familyName)) {
                value = instance.getFamilyName();
            }
            else if(field.equals(Field.Users.middleName)) {
                value = instance.getMiddleName();
            }
            else if(field.equals(Field.Users.role)) {
                if(instance.getRole() != null) {
                    value = instance.getRole().getValue();
                }
            }
            else if(field.equals(Field.Users.identifier)) {
                value = instance.getIdentifier();
            }
            else if(field.equals(Field.Users.email)) {
                value = instance.getEmail();
            }
            else if(field.equals(Field.Users.sms)) {
                value = instance.getSms();
            }
            else if(field.equals(Field.Users.phone)) {
                value = instance.getPhone();
            }
            else if(field.equals(Field.Users.Agents.sourcedId)) {
                if(!instance.getAgents().isEmpty()) {
                    value = instance.getAgents().get(0).getSourcedId();
                }
            }
            else if(field.equals(Field.Users.Agents.type)) {
                if(!instance.getAgents().isEmpty()) {
                    value = instance.getAgents().get(0).getType().getValue();
                }
            }
            else if(field.equals(Field.Users.Agents.href)) {
                if(!instance.getAgents().isEmpty()) {
                    value = instance.getAgents().get(0).getHref();
                }
            }
            else if(field.equals(Field.Users.Orgs.sourcedId)) {
                if(!instance.getOrgs().isEmpty()) {
                    value = instance.getOrgs().get(0).getSourcedId();
                }
            }
            else if(field.equals(Field.Users.Orgs.type)) {
                if(!instance.getOrgs().isEmpty()) {
                    value = instance.getOrgs().get(0).getType().getValue();
                }
            }
            else if(field.equals(Field.Users.Orgs.href)) {
                if(!instance.getOrgs().isEmpty()) {
                    value = instance.getOrgs().get(0).getHref();
                }
            }
            else if(field.equals(Field.Users.grades)) {
                if(!instance.getGrades().isEmpty()) {
                    value = instance.getGrades().get(0);
                }
            }
            else if(field.equals(Field.Users.password)) {
                value = instance.getPassword();
            }
        }
        else if(fieldType.equals(FieldType.Demographics)) {
            Demographic instance =  demographics.get(0);

            if(field.equals(Field.sourcedId)) {
                value = instance.getSourcedId();
            }
            else if(field.equals(Field.dateLastModified)) {
                if(instance.getDateLastModified() != null) {
                    value = instance.getDateLastModified().toString();
                }
            }
            else if(field.equals(Field.status)) {
                if(instance.getStatus() != null) {
                    value = instance.getStatus().getValue();
                }
            }
            else if(field.equals(Field.Demographics.birthDate)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getBirthDate().toString();
                }
            }
            else if(field.equals(Field.Demographics.sex)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getSex().getValue();
                }
            }
            else if(field.equals(Field.Demographics.americanIndianOrAlaskaNative)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getAmericanIndianOrAlaskaNative().toString();
                }
            }
            else if(field.equals(Field.Demographics.asian)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getAsian().toString();
                }
            }
            else if(field.equals(Field.Demographics.blackOrAfricanAmerican)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getBlackOrAfricanAmerican().toString();
                }
            }
            else if(field.equals(Field.Demographics.nativeHawaiianOrOtherPacificIslander)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getNativeHawaiianOrOtherPacificIslander().toString();
                }
            }
            else if(field.equals(Field.Demographics.white)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getWhite().toString();
                }
            }
            else if(field.equals(Field.Demographics.demographicRaceTwoOrMoreRaces)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getDemographicRaceTwoOrMoreRaces().toString();
                }
            }
            else if(field.equals(Field.Demographics.hispanicOrLatinoEthnicity)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getHispanicOrLatinoEthnicity().toString();
                }
            }
            else if(field.equals(Field.Demographics.countryOfBirthCode)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getCountryOfBirthCode();
                }
            }
            else if(field.equals(Field.Demographics.stateOfBirthAbbreviation)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getStateOfBirthAbbreviation();
                }
            }
            else if(field.equals(Field.Demographics.cityOfBirth)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getCityOfBirth();
                }
            }
            else if(field.equals(Field.Demographics.publicSchoolResidenceStatus)) {
                if(instance.getBirthDate() != null) {
                    value = instance.getPublicSchoolResidenceStatus();
                }
            }
        }
        return value;
    }


}
