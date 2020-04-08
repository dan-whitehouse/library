package oneroster;

import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.client.oneroster.response.model.Class;
import org.ricone.library.exception.InvalidPathException;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestPlanEnrollments {
    private final boolean showResponseDetails, showTestResults, showTypes, showJson;
    private boolean c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14;

    public TestPlanEnrollments(boolean showResponseDetails, boolean showTestResults, boolean showTypes, boolean showJson) {
        this.showResponseDetails = showResponseDetails;
        this.showTestResults = showTestResults;
        this.showTypes = showTypes;
        this.showJson = showJson;
    }

    public void run(OneRoster oneRoster) throws InvalidPathException {
        final int LIMIT = 5;
        final String ENROLLMENT_SOURCED_ID = "036000F7-C377-4158-A715-2B22CA8CECCE";
        final String SCHOOL_SOURCED_ID = "E54E9D1F-7F2B-4B60-9CC7-AAD883614135"; //TODO Check Id, getting 204's
        final String CLASS_SOURCED_ID = "1EED1C72-997C-49DD-8F90-151060BCF52C"; //TODO Check Id, getting 204's

        //All
        c1 = testAll(oneRoster, ServicePath.GET_Enrollments);
        c2 = testAllWithPaging(oneRoster, ServicePath.GET_Enrollments, LIMIT);
        c3 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Enrollments, LIMIT, Field.Enrollments.School.sourcedId, SortOrder.DESC);
        c4 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Enrollments, LIMIT, new Filter(Field.Enrollments.role, Predicate.Equals, "student"));

        //By Id
        c5 = testById(oneRoster, ServicePath.GET_Enrollment_By_SourcedId, ENROLLMENT_SOURCED_ID);
        c6 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_Enrollment_By_SourcedId, ENROLLMENT_SOURCED_ID);

        //Enrollments By School
        c7 = testAllByPredicate(oneRoster, ServicePath.GET_Enrollments_By_School_SourcedId, SCHOOL_SOURCED_ID);
        c8 = testAllByPredicateWithPaging(oneRoster, ServicePath.GET_Enrollments_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT);
        c9 = testAllByPredicateWithPagingAndSorting(oneRoster, ServicePath.GET_Enrollments_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, Field.Enrollments.School.sourcedId, SortOrder.DESC);
        c10 = testAllByPredicateWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Enrollments_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, new Filter(Field.Enrollments.role, Predicate.Equals, "student"));

        //Enrollments By School and Class
        c11 = testAllByPredicates(oneRoster, ServicePath.GET_Enrollments_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID);
        c12 = testAllByPredicatesWithPaging(oneRoster, ServicePath.GET_Enrollments_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID, LIMIT);
        c13 = testAllByPredicatesWithPagingAndSorting(oneRoster, ServicePath.GET_Enrollments_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID, LIMIT, Field.Enrollments.School.sourcedId, SortOrder.DESC);
        c14 = testAllByPredicatesWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Enrollments_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID, LIMIT, new Filter(Field.Enrollments.role, Predicate.Equals, "student"));
    }

    public void results() {
        boolean testsPassed = (
            c1 && c2 && c3 && c4
            && c5 && c6
            && c7 && c8 && c9 && c10
            && c11 && c12 && c13 && c14
        );
        System.out.println("All Enrollment Tests Passed: " + testsPassed);
    }

    /** All **/
    private boolean testAll(OneRoster oneRoster, ServicePath servicePath) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllWithPaging(OneRoster oneRoster, ServicePath servicePath, int limit) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
                .sorting()
                    .field(field)
                    .orderBy(order)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean isSorted = isListOrdered(response.getData(), field);
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, int limit, Filter filter) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
                .filtering()
                    .filter(filter)
                .end()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Enrollments.role)
                    .field(Field.Enrollments.Clazz.sourcedId)
                    .field(Field.Enrollments.School.sourcedId)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean containsFilterValue = isFilter(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(Enrollment i : response.getData().getEnrollments()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " containsFilterValue: " + containsFilterValue + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && containsFilterValue && isFieldSelectionOnly;
    }

    /** By Id **/
    private boolean testById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Enrollment> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isCorrectSourcedId = isCorrectSourcedId(response.getData(), id);
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isCorrectSourcedId: " + isCorrectSourcedId);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId;
    }

    private boolean testByIdWithFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Enrollment> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Enrollments.role)
                    .field(Field.Enrollments.Clazz.sourcedId)
                    .field(Field.Enrollments.School.sourcedId)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isCorrectSourcedId = isCorrectSourcedId(response.getData(), id);
        boolean isFieldSelectionOnly = false;

        if(isFieldSelectionOnly(response.getData())) {
            isFieldSelectionOnly = true;
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isCorrectSourcedId: " + isCorrectSourcedId + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId && isFieldSelectionOnly;
    }

    /** By Predicate **/
    private boolean testAllByPredicate(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllByPredicateWithPaging(OneRoster oneRoster, ServicePath servicePath, String id, int limit) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllByPredicateWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, String id, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
                .sorting()
                    .field(field)
                    .orderBy(order)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean isSorted = isListOrdered(response.getData(), field);
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllByPredicateWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id, int limit, Filter filter) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
                .filtering()
                    .filter(filter)
                .end()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Enrollments.role)
                    .field(Field.Enrollments.Clazz.sourcedId)
                    .field(Field.Enrollments.School.sourcedId)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean containsFilterValue = isFilter(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(Enrollment i : response.getData().getEnrollments()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " containsFilterValue: " + containsFilterValue + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && containsFilterValue && isFieldSelectionOnly;
    }

    /** By Predicates **/
    private boolean testAllByPredicates(OneRoster oneRoster, ServicePath servicePath, String id, String id2) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                    .id(id2)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllByPredicatesWithPaging(OneRoster oneRoster, ServicePath servicePath, String id, String id2, int limit) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                    .id(id2)
                .end()
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllByPredicatesWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, String id, String id2, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                    .id(id2)
                .end()
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
                .sorting()
                    .field(field)
                    .orderBy(order)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean isSorted = isListOrdered(response.getData(), field);
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllByPredicatesWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id, String id2, int limit, Filter filter) throws InvalidPathException {
        IResponse<Enrollments> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                    .id(id2)
                .end()
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
                .filtering()
                    .filter(filter)
                .end()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Enrollments.role)
                    .field(Field.Enrollments.Clazz.sourcedId)
                    .field(Field.Enrollments.School.sourcedId)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean containsFilterValue = isFilter(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(Enrollment i : response.getData().getEnrollments()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " containsFilterValue: " + containsFilterValue + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "student(" + typeData[0] + ")" + " | teacher(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && containsFilterValue && isFieldSelectionOnly;
    }

    /** Test Methods **/
    private boolean hasData(Enrollment instance) {
        return instance != null && instance.hasData();
    }

    private boolean hasData(Enrollments instance) {
        return instance != null && !instance.getEnrollments().isEmpty();
    }

    private int[] typeCount(Enrollments instance) {
        int student = 0, teacher =  0, other = 0;

        for(Enrollment i : instance.getEnrollments()) {
            if(RoleType.student.equals(i.getRole())) {
               student++;
            }
            else if(RoleType.teacher.equals(i.getRole())) {
               teacher++;
            }
            else {
               other++;
            }
        }
        return new int[]{student, teacher, other};
    }

    private int[] typeCount(Enrollment instance) {
        int student = 0, teacher =  0, other = 0;

        if(RoleType.student.equals(instance.getRole())) {
            student++;
        }
        else if(RoleType.teacher.equals(instance.getRole())) {
            teacher++;
        }
        else {
            other++;
        }
        return new int[]{student, teacher, other};
    }

    private boolean isCorrectSourcedId(Enrollment instance, String sourcedId) {
        return sourcedId.equalsIgnoreCase(instance.getSourcedId());
    }

    private boolean isFilter(Enrollments instance, String filterValue) {
        for(Enrollment i : instance.getEnrollments()) {
            if(!filterValue.equalsIgnoreCase(i.getRole().getValue())) {
                return false;
            }
        }
        return true;
    }

    private boolean isFieldSelectionOnly(Enrollment instance) {
        return Stream.of(
                instance.getStatus(),
                instance.getDateLastModified(),
                instance.getMetadata(),
                instance.getPrimary(),
                instance.getUser(),
                instance.getClass_(),
                instance.getSchool(),
                instance.getBeginDate(),
                instance.getEndDate()
            ).allMatch(Objects::isNull)
        ;
    }

    private boolean isListOrdered(Enrollments instance, IField sortField) {
        LinkedList<String> originalRefIds = new LinkedList<>();
        LinkedList<String> manuallySortedRefIds = new LinkedList<>();

        if(sortField.equals(Field.Enrollments.School.sourcedId)) {
            instance.getEnrollments().forEach(enrollment -> originalRefIds.add(enrollment.getSchool().getSourcedId())); //Take the refIds of the list
            instance.getEnrollments().stream().sorted(Comparator.comparing(enrollment -> enrollment.getSchool().getSourcedId(), String::compareToIgnoreCase)); //Take the enrollments and reverse order them them on school sourcedId

        }
        else if(sortField.equals(Field.Enrollments.Clazz.sourcedId)) {
            instance.getEnrollments().forEach(enrollment -> originalRefIds.add(enrollment.getSchool().getSourcedId())); //Take the refIds of the list
            instance.getEnrollments().stream().sorted(Comparator.comparing(enrollment -> enrollment.getClass_().getSourcedId(), String::compareToIgnoreCase)); //Take the enrollments and reverse order them on class sourcedId
        }
        else {
            return false;
        }
        return originalRefIds.equals(manuallySortedRefIds);
    }

    private boolean isCountLimited(Enrollments instance, int limit) {
        return instance.getEnrollments().size() <= limit;
    }
}
