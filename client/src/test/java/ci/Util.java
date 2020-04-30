package ci;

import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Model;
import org.ricone.library.client.oneroster.request.Field;
import org.ricone.library.client.oneroster.request.FieldType;
import org.ricone.library.client.oneroster.request.IField;
import org.ricone.library.client.oneroster.request.ServicePath;
import org.ricone.library.client.oneroster.response.*;
import org.ricone.library.client.oneroster.response.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Init.class);
    private static final String FORMAT = "| %-250s | %-6s | %-9s | %-12s | %-9s | %-300s";

    /** Output **/
    static void printTableHeader() {
        logger.info("| Request                                                                                                                                                                                                                                                    | Status | Size      | Record Count | Duration  | Warning/Error                                                                                                                                                                                                                                                                                            |");
        printTableBorder();
    }

    static void printTableBorder() {
        logger.info("+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+--------+-----------+--------------+-----------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }

    static void printTableRow(IResponse<? extends Model> response) {
        logger.info(String.format(FORMAT,
                trimUrl(response.getRequestPath()),
                response.getResponseStatus(),
                byteCount(response.getResponseHeaders().getFirst("Content-Length")),
                formatNumber(response.getResponseHeaders().getFirst("X-Record-Count")),
                response.getResponseHeaders().getFirst("X-Duration"),
                getWarning(response)
        ));
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

    private static String getWarning(IResponse<? extends Model> response) {
        if(response.getResponseStatus().isError()) {
            return "error - " + response.getResponseStatusText();
        }

        List<Error> warnings = getWarnings(response);
        if(warnings == null) {
            warnings = new ArrayList<>();
        }

        if(warnings.size() >= 1) {
            Error warning =   warnings.get(0);
            return "warning - " + warning.getDescription();
        }
        else {
            return "N/A";
        }
    }

    private static List<Error> getWarnings(IResponse<? extends Model> response) {
        switch(response.getClass().getSimpleName()) {
            case "OrgResponse": return ((OrgResponse)response).getWarnings();
            case "OrgsResponse": return ((OrgsResponse)response).getWarnings();
            case "AcademicSessionResponse": return ((AcademicSessionResponse)response).getWarnings();
            case "AcademicSessionsResponse": return ((AcademicSessionsResponse)response).getWarnings();
            case "CourseResponse": return ((CourseResponse)response).getWarnings();
            case "CoursesResponse": return ((CoursesResponse)response).getWarnings();
            case "ClassResponse": return ((ClassResponse)response).getWarnings();
            case "ClassesResponse": return ((ClassesResponse)response).getWarnings();
            case "EnrollmentResponse": return ((EnrollmentResponse)response).getWarnings();
            case "EnrollmentsResponse": return ((EnrollmentsResponse)response).getWarnings();
            case "UserResponse": return ((UserResponse)response).getWarnings();
            case "UsersResponse": return ((UsersResponse)response).getWarnings();
            case "DemographicResponse": return ((DemographicResponse)response).getWarnings();
            case "DemographicsResponse": return ((DemographicsResponse)response).getWarnings();
            default: return new ArrayList<>();
        }
    }

    /** Helpers **/
    static List<IField> getFields(ServicePath servicePath) {
        List<IField> fields = new ArrayList<>();
        fields.add(Field.sourcedId);
        fields.add(Field.dateLastModified);

        fields.add(Field.BLANK_FIELD);
        fields.add(Field.FAKE_FIELD);

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
}
