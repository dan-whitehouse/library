package ci;

import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Model;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.OffsetResponse;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.client.oneroster.response.model.Class;
import org.ricone.library.exception.InvalidPathException;

import java.time.LocalDateTime;
import java.util.*;

public class Init {
    private static HashMap<ServicePath, String[]> ids;

    public static void main(String[] args) throws InvalidPathException {
        Authenticator authenticator = Authenticator.builder()
            .url(System.getenv("url")).api(API.OneRoster)
            .credentials(System.getenv("username"), System.getenv("password"))
            .provider(System.getenv("provider"))
        .authenticate();

        Optional<Endpoint> endpoint = authenticator.getEndpoint("localhost");
        if(endpoint.isPresent()) {
            OneRoster oneRoster = new OneRoster(endpoint.get());

            ids = loadIds(oneRoster);

            Util.printTableHeader();
            for(ServicePath servicePath : ServicePath.values()) {

                //No Feature Requests
                requestWithNoFeatures(oneRoster, servicePath);

                //Sorting Requests
                for(IField field : Util.getFields(servicePath)) { //Field
                    for (SortOrder sortOrder : SortOrder.values()) {  //Asc and Desc
                        requestWithSorting(oneRoster, servicePath, field, sortOrder);
                    }
                }

                //Paging Requests
                requestWithPaging(oneRoster, servicePath, 500);


                Util.printTableBorder();
            }
        }
    }

    /** Request - No Features **/
    private static void requestWithNoFeatures(OneRoster oneRoster, ServicePath servicePath) throws InvalidPathException {
        IResponse<? extends Model> response;
        if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                        .id(getIds(servicePath)[1])
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
    }

    /** Request - Sorting **/
    private static void requestWithSorting(OneRoster oneRoster, ServicePath servicePath, IField field, SortOrder sortOrder) throws InvalidPathException {
        IResponse<? extends Model> response;
        if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
                .with()
                    .sorting()
                        .field(field)
                        .orderBy(sortOrder)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
            response = oneRoster.request(Request.builder()
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

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
                .with()
                    .sorting()
                        .field(field)
                        .orderBy(sortOrder)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                        .id(getIds(servicePath)[1])
                    .end()
                .end()
                .with()
                    .sorting()
                        .field(field)
                        .orderBy(sortOrder)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
    }

    /** Request - Field Selection **/
    private static void requestWithFieldSelection(OneRoster oneRoster, ServicePath servicePath, IField field) throws InvalidPathException {
        IResponse<? extends Model> response;
        if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
                .with()
                    .fieldSelection()
                        .field(field)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                .end()
                .with()
                    .fieldSelection()
                        .field(field)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
                .with()
                    .fieldSelection()
                        .field(field)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                        .id(getIds(servicePath)[1])
                    .end()
                .end()
                .with()
                    .fieldSelection()
                        .field(field)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
    }

    /** Request - Paging **/
    private static void requestWithPaging(OneRoster oneRoster, ServicePath servicePath, final int limit) throws InvalidPathException {
        IResponse<? extends Model> response = null;
        if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
            OffsetResponse offset = oneRoster.getOffset(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
                .with()
                    .paging()
                        .limit(limit)
                    .end()
                .end()
            .build());

            while(offset.hasNext()) {
                IResponse<? extends Model> pagedResponse = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getIds(servicePath)[0])
                        .end()
                    .end()
                    .with()
                        .paging()
                            .limit(limit)
                            .offset(offset.next())
                        .end()
                    .end()
                .build());

                Util.printTableRow(pagedResponse);
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
                IResponse<? extends Model> pagedResponse = oneRoster.request(Request.builder()
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

                Util.printTableRow(pagedResponse);
            }
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
            OffsetResponse offset = oneRoster.getOffset(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
                .with()
                    .paging()
                        .limit(limit)
                    .end()
                .end()
            .build());

            while(offset.hasNext()) {
                IResponse<? extends Model> pagedResponse = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getIds(servicePath)[0])
                        .end()
                    .end()
                    .with()
                        .paging()
                            .limit(limit)
                            .offset(offset.next())
                        .end()
                    .end()
                .build());

                Util.printTableRow(pagedResponse);
            }
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
            OffsetResponse offset = oneRoster.getOffset(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                        .id(getIds(servicePath)[1])
                    .end()
                .end()
                .with()
                    .paging()
                        .limit(limit)
                    .end()
                .end()
            .build());

            while(offset.hasNext()) {
                IResponse<? extends Model> pagedResponse = oneRoster.request(Request.builder()
                    .request()
                        .path(servicePath)
                        .ids()
                            .id(getIds(servicePath)[0])
                            .id(getIds(servicePath)[0])
                        .end()
                    .end()
                    .with()
                        .paging()
                            .limit(limit)
                            .offset(offset.next())
                        .end()
                    .end()
                .build());

                Util.printTableRow(pagedResponse);
            }
        }
    }

    /** Request - Filtering **/
    private static void requestWithFiltering(OneRoster oneRoster, ServicePath servicePath, IField field, Predicate predicate, String value) throws InvalidPathException {
        final Filter filter = new Filter(field, predicate, value);

        IResponse<? extends Model> response;
        if (servicePath.getServicePathType().equals(ServicePathType.SINGLE)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
                .with()
                    .filtering()
                        .filter(filter)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.OBJECT)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                .end()
                .with()
                    .filtering()
                    .   filter(filter)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATE)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                    .end()
                .end()
                .with()
                    .filtering()
                        .filter(filter)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
        else if (servicePath.getServicePathType().equals(ServicePathType.PREDICATES)) {
            response = oneRoster.request(Request.builder()
                .request()
                    .path(servicePath)
                    .ids()
                        .id(getIds(servicePath)[0])
                        .id(getIds(servicePath)[1])
                    .end()
                .end()
                .with()
                    .filtering()
                        .filter(filter)
                    .end()
                .end()
            .build());

            Util.printTableRow(response);
        }
    }

    /** Id Population and Retrieval **/
    private static HashMap<ServicePath, String[]> loadIds(OneRoster oneRoster) throws InvalidPathException {
        List<Org> orgs = loadOrgs(oneRoster);
        List<Org> schools = loadSchools(oneRoster);
        List<AcademicSession> academicSessions = loadAcademicSessions(oneRoster);
        List<AcademicSession> terms = loadTerms(oneRoster);
        List<Course> courses = loadCourses(oneRoster);
        List<Class> classes = loadClasses(oneRoster);
        List<Enrollment> enrollments = loadEnrollments(oneRoster);
        List<User> users = loadUsers(oneRoster);
        List<User> teachers = loadTeachers(oneRoster);
        List<User> students = loadStudents(oneRoster);
        List<Demographic> demographics = loadDemographics(oneRoster);

        HashMap<ServicePath, String[]> map = new HashMap<>();
        for(ServicePath servicePath : ServicePath.values()) {

            if(servicePath.getServicePathType() != ServicePathType.OBJECT) {
                String[] predicates = servicePath.getValue().split("/");

                switch(servicePath.getServicePathType()) {
                    case SINGLE:
                    case PREDICATE:{
                        switch(predicates[0]) {
                            case "orgs":  map.put(servicePath, new String[]{orgs.get(0).getSourcedId()}); break;
                            case "schools":  map.put(servicePath, new String[]{schools.get(0).getSourcedId()}); break;
                            case "academicSessions":  map.put(servicePath, new String[]{academicSessions.get(0).getSourcedId()}); break;
                            case "terms":  map.put(servicePath, new String[]{terms.get(0).getSourcedId()}); break;
                            case "courses":  map.put(servicePath, new String[]{courses.get(0).getSourcedId()}); break;
                            case "classes":  map.put(servicePath, new String[]{classes.get(0).getSourcedId()}); break;
                            case "enrollments":  map.put(servicePath, new String[]{enrollments.get(0).getSourcedId()}); break;
                            case "users": map.put(servicePath, new String[]{users.get(0).getSourcedId()});break;
                            case "teachers":map.put(servicePath, new String[]{teachers.get(0).getSourcedId()});break;
                            case "students": map.put(servicePath, new String[]{students.get(0).getSourcedId()}); break;
                            case "demographics":  map.put(servicePath, new String[]{demographics.get(0).getSourcedId()}); break;
                        }
                    } break;
                    case PREDICATES: {
                        String value1;
                        String value2;
                        switch(predicates[0]) {
                            case "orgs": value1 = orgs.get(0).getSourcedId(); break;
                            case "schools":  value1 = schools.get(0).getSourcedId(); break;
                            case "academicSessions":  value1 = academicSessions.get(0).getSourcedId(); break;
                            case "terms":  value1 = terms.get(0).getSourcedId(); break;
                            case "courses":  value1 = courses.get(0).getSourcedId(); break;
                            case "classes":  value1 = classes.get(0).getSourcedId(); break;
                            case "enrollments":  value1 = enrollments.get(0).getSourcedId(); break;
                            case "users": value1 = users.get(0).getSourcedId(); break;
                            case "teachers": value1 = teachers.get(0).getSourcedId(); break;
                            case "students": value1 = students.get(0).getSourcedId(); break;
                            case "demographics": value1 = demographics.get(0).getSourcedId(); break;
                            default: value1 = "No_ID";
                        }
                        switch(predicates[2]) {
                            case "orgs": value2 = orgs.get(0).getSourcedId(); break;
                            case "schools":  value2 = schools.get(0).getSourcedId(); break;
                            case "academicSessions":  value2 = academicSessions.get(0).getSourcedId(); break;
                            case "terms":  value2 = terms.get(0).getSourcedId(); break;
                            case "courses":  value2 = courses.get(0).getSourcedId(); break;
                            case "classes":  value2 = classes.get(0).getSourcedId(); break;
                            case "enrollments":  value2 = enrollments.get(0).getSourcedId(); break;
                            case "users": value2 = users.get(0).getSourcedId(); break;
                            case "teachers": value2 = teachers.get(0).getSourcedId(); break;
                            case "students": value2 = students.get(0).getSourcedId(); break;
                            case "demographics": value2 = demographics.get(0).getSourcedId(); break;
                            default: value2 = "No_ID";
                        }
                        map.put(servicePath, new String[]{value1, value2});
                    } break;
                }

            }
        }

        /*map.forEach((s, s2) -> {
            System.out.println(s.getValue() + " - " + Arrays.toString(s2));
        });*/

        return map;
    }

    private static String[] getIds(ServicePath servicePath) {
        return ids.get(servicePath);
    }

    private static List<Org> loadOrgs(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getOrgs();
    }

    private static List<Org> loadSchools(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Schools)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getOrgs();
    }

    private static List<AcademicSession> loadAcademicSessions(OneRoster oneRoster) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_AcademicSessions)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getAcademicSessions();
    }

    private static List<AcademicSession> loadTerms(OneRoster oneRoster) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Terms)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getAcademicSessions();
    }

    private static List<Course> loadCourses(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Courses)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getCourses();
    }

    private static List<Class> loadClasses(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Classes)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getClasses();
    }

    private static List<Enrollment> loadEnrollments(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Enrollments)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getEnrollments();
    }

    private static List<User> loadUsers(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Users)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getUsers();
    }

    private static List<User> loadTeachers(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Teachers)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getUsers();
    }

    private static List<User> loadStudents(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Students)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
        .build());
        return response.getData().getUsers();
    }

    private static List<Demographic> loadDemographics(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Demographics> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Demographics)
            .end()
            .with()
                .paging()
                    .limit(1)
                .end()
            .end()
            .build());
        return response.getData().getDemographics();
    }
}
