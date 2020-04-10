package oneroster;

import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.exception.InvalidPathException;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestPlanUsers {
    private final boolean showResponseDetails, showTestResults, showTypes, showJson;
    private boolean c1, c2, c3, c4, c5, c6,
        c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24,
        c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37, c38, c39, c40, c41, c42
    ;

    public TestPlanUsers(boolean showResponseDetails, boolean showTestResults, boolean showTypes, boolean showJson) {
        this.showResponseDetails = showResponseDetails;
        this.showTestResults = showTestResults;
        this.showTypes = showTypes;
        this.showJson = showJson;
    }

    public void run(OneRoster oneRoster) throws InvalidPathException {
        final int LIMIT = 5;
        final String USER_SOURCED_ID = "8BA06EA6-58FC-45D2-A6A3-8C2A34A7C82F";
        final String SCHOOL_SOURCED_ID = "78C72FBF-8C29-4107-BF2B-E3BA9F9849C5";
        final String SCHOOL_SOURCED_ID_PREDICATES = "E54E9D1F-7F2B-4B60-9CC7-AAD883614135";
        final String TEACHER_SOURCED_ID = "8BA06EA6-58FC-45D2-A6A3-8C2A34A7C82F";
        final String CLASS_SOURCED_ID = "1EED1C72-997C-49DD-8F90-151060BCF52C";
        final String STUDENT_SOURCED_ID = "9852E65B-FDB7-4C2B-9545-ABD33CF8E9C8";



        //All - Users
        c1 = testAll(oneRoster, ServicePath.GET_Users);
        c2 = testAllWithPaging(oneRoster, ServicePath.GET_Users, LIMIT);
        c3 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Users, LIMIT, Field.Users.givenName, SortOrder.DESC);
        c4 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Users, LIMIT, new Filter(Field.Users.familyName, Predicate.Equals, "Smith"));

        //By Id - Users
        c5 = testById(oneRoster, ServicePath.GET_User_By_SourcedId, USER_SOURCED_ID);
        c6 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_User_By_SourcedId, USER_SOURCED_ID);

        //All - Teachers
        c7 = testAll(oneRoster, ServicePath.GET_Teachers);
        c8 = testAllWithPaging(oneRoster, ServicePath.GET_Teachers, LIMIT);
        c9 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Teachers, LIMIT, Field.Users.givenName, SortOrder.DESC);
        c10 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Teachers, LIMIT, new Filter(Field.Users.familyName, Predicate.Equals, "Guzek"));

        //By Id - Teachers
        c11 = testById(oneRoster, ServicePath.GET_Teacher_By_SourcedId, USER_SOURCED_ID);
        c12 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_Teacher_By_SourcedId, USER_SOURCED_ID);

        //Teachers By School
        c13 = testAllByPredicate(oneRoster, ServicePath.GET_Teachers_By_School_SourcedId, SCHOOL_SOURCED_ID);
        c14 = testAllByPredicateWithPaging(oneRoster, ServicePath.GET_Teachers_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT);
        c15 = testAllByPredicateWithPagingAndSorting(oneRoster, ServicePath.GET_Teachers_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, Field.Users.givenName, SortOrder.DESC);
        c16 = testAllByPredicateWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Teachers_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, new Filter(Field.Users.familyName, Predicate.Equals, "Guzek"));

        //Teachers By Class
        c17 = testAllByPredicate(oneRoster, ServicePath.GET_Teachers_By_Class_SourcedId, CLASS_SOURCED_ID);
        c18 = testAllByPredicateWithPaging(oneRoster, ServicePath.GET_Teachers_By_Class_SourcedId, CLASS_SOURCED_ID, LIMIT);
        c19 = testAllByPredicateWithPagingAndSorting(oneRoster, ServicePath.GET_Teachers_By_Class_SourcedId, CLASS_SOURCED_ID, LIMIT, Field.Users.givenName, SortOrder.DESC);
        c20 = testAllByPredicateWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Teachers_By_Class_SourcedId, CLASS_SOURCED_ID, LIMIT, new Filter(Field.Users.familyName, Predicate.Equals, "Dvorscak"));

        //Teachers By School and Class
        c21 = testAllByPredicates(oneRoster, ServicePath.GET_Teachers_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID_PREDICATES);
        c22 = testAllByPredicatesWithPaging(oneRoster, ServicePath.GET_Teachers_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID_PREDICATES, LIMIT);
        c23 = testAllByPredicatesWithPagingAndSorting(oneRoster, ServicePath.GET_Teachers_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID_PREDICATES, LIMIT, Field.Users.givenName, SortOrder.DESC);
        c24 = testAllByPredicatesWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Teachers_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID_PREDICATES, LIMIT, new Filter(Field.Users.familyName, Predicate.Equals, "Dvorscak"));

        //All - Students
        c25 = testAll(oneRoster, ServicePath.GET_Students);
        c26 = testAllWithPaging(oneRoster, ServicePath.GET_Students, LIMIT);
        c27 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Students, LIMIT, Field.Users.givenName, SortOrder.DESC);
        c28 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Students, LIMIT, new Filter(Field.Users.familyName, Predicate.Equals, "Colon"));

        //By Id - Students
        c29 = testById(oneRoster, ServicePath.GET_Student_By_SourcedId, STUDENT_SOURCED_ID);
        c30 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_Student_By_SourcedId, STUDENT_SOURCED_ID);

        //Students By School
        c31 = testAllByPredicate(oneRoster, ServicePath.GET_Students_By_School_SourcedId, SCHOOL_SOURCED_ID);
        c32 = testAllByPredicateWithPaging(oneRoster, ServicePath.GET_Students_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT);
        c33 = testAllByPredicateWithPagingAndSorting(oneRoster, ServicePath.GET_Students_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, Field.Users.givenName, SortOrder.DESC);
        c34 = testAllByPredicateWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Students_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, new Filter(Field.Users.familyName, Predicate.Equals, "Colon"));

        //Students By Class
        c35 = testAllByPredicate(oneRoster, ServicePath.GET_Students_By_Class_SourcedId, CLASS_SOURCED_ID);
        c36 = testAllByPredicateWithPaging(oneRoster, ServicePath.GET_Students_By_Class_SourcedId, CLASS_SOURCED_ID, LIMIT);
        c37 = testAllByPredicateWithPagingAndSorting(oneRoster, ServicePath.GET_Students_By_Class_SourcedId, CLASS_SOURCED_ID, LIMIT, Field.Users.givenName, SortOrder.DESC);
        c38 = testAllByPredicateWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Students_By_Class_SourcedId, CLASS_SOURCED_ID, LIMIT, new Filter(Field.Users.familyName, Predicate.Equals, "Colon"));

        //Students By School and Class
        c39 = testAllByPredicates(oneRoster, ServicePath.GET_Students_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID_PREDICATES);
        c40 = testAllByPredicatesWithPaging(oneRoster, ServicePath.GET_Students_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID_PREDICATES, LIMIT);
        c41 = testAllByPredicatesWithPagingAndSorting(oneRoster, ServicePath.GET_Students_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID_PREDICATES, LIMIT, Field.Users.givenName, SortOrder.DESC);
        c42 = testAllByPredicatesWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Students_By_Class_SourcedId_With_School_SourcedId, CLASS_SOURCED_ID, SCHOOL_SOURCED_ID_PREDICATES, LIMIT, new Filter(Field.Users.familyName, Predicate.Equals, "Colon"));
    }

    public void results() {
        boolean testsPassed = (
            c1 && c2 && c3 && c4 && c5 && c6 //User Tests
            && c7 && c8 && c9 && c10 && c11 && c12 && c13 && c14 && c15 && c16 && c17 && c18 && c19 && c20 && c21 && c22 && c23 && c24 //Teacher Tests
            && c25 && c26 && c27 && c28 && c29 && c30 && c31 && c32 && c33 && c34 && c35 && c36 && c37 && c38 && c39 && c40 && c41 && c42 //Student Tests
        );
        System.out.println("All Users Tests Passed: " + testsPassed);
    }

    /** All **/
    private boolean testAll(OneRoster oneRoster, ServicePath servicePath) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
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
        IResponse<Users> response = oneRoster.request(Request.builder()
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
        IResponse<Users> response = oneRoster.request(Request.builder()
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
        IResponse<Users> response = oneRoster.request(Request.builder()
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
                    .field(Field.Users.role)
                    .field(Field.Users.givenName)
                    .field(Field.Users.middleName)
                    .field(Field.Users.familyName)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean containsFilterValue = isFilter(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(User i : response.getData().getUsers()) {
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
        IResponse<User> response = oneRoster.request(Request.builder()
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
        IResponse<User> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Users.role)
                    .field(Field.Users.givenName)
                    .field(Field.Users.middleName)
                    .field(Field.Users.familyName)
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
        IResponse<Users> response = oneRoster.request(Request.builder()
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
        IResponse<Users> response = oneRoster.request(Request.builder()
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
        IResponse<Users> response = oneRoster.request(Request.builder()
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
        IResponse<Users> response = oneRoster.request(Request.builder()
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
                    .field(Field.Users.role)
                    .field(Field.Users.givenName)
                    .field(Field.Users.middleName)
                    .field(Field.Users.familyName)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean containsFilterValue = isFilter(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(User i : response.getData().getUsers()) {
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
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id2)
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

    private boolean testAllByPredicatesWithPaging(OneRoster oneRoster, ServicePath servicePath, String id, String id2, int limit) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id2)
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

    private boolean testAllByPredicatesWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, String id, String id2, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id2)
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

    private boolean testAllByPredicatesWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id, String id2, int limit, Filter filter) throws InvalidPathException {
        IResponse<Users> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id2)
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
                    .field(Field.Users.role)
                    .field(Field.Users.givenName)
                    .field(Field.Users.middleName)
                    .field(Field.Users.familyName)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean containsFilterValue = isFilter(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(User i : response.getData().getUsers()) {
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
    private boolean hasData(User instance) {
        return instance != null && instance.hasData();
    }

    private boolean hasData(Users instance) {
        return instance != null && !instance.getUsers().isEmpty();
    }

    private int[] typeCount(Users instance) {
        int student = 0, teacher =  0, other = 0;

        for(User i : instance.getUsers()) {
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

    private int[] typeCount(User instance) {
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

    private boolean isCorrectSourcedId(User instance, String sourcedId) {
        return sourcedId.equalsIgnoreCase(instance.getSourcedId());
    }

    private boolean isFilter(Users instance, String filterValue) {
        for(User i : instance.getUsers()) {
            if(!filterValue.equalsIgnoreCase(i.getFamilyName())) {
                return false;
            }
        }
        return true;
    }

    private boolean isFieldSelectionOnly(User instance) {
        return Stream.of(
                instance.getStatus(),
                instance.getDateLastModified(),
                instance.getMetadata(),
                instance.getUsername(),
                instance.getEnabledUser(),
                instance.getIdentifier(),
                instance.getEmail(),
                instance.getSms(),
                instance.getPhone(),
                instance.getPassword()
            ).allMatch(Objects::isNull)
                && instance.getUserIds().isEmpty()
                && instance.getAgents().isEmpty()
                && instance.getOrgs().isEmpty()
                && instance.getGrades().isEmpty()
        ;
    }

    private boolean isListOrdered(Users instance, IField sortField) {
        List<String> originalRefIds = new LinkedList<>();
        List<String> manuallySortedRefIds = new LinkedList<>();

        instance.getUsers().forEach(i -> originalRefIds.add(i.getGivenName())); //Take the refIds of the list
        manuallySortedRefIds = instance.getUsers().stream().sorted(Comparator.comparing(User::getGivenName, String::compareToIgnoreCase).reversed()).map(User::getGivenName).collect(Collectors.toList());

        return originalRefIds.equals(manuallySortedRefIds);
    }

    private boolean isCountLimited(Users instance, int limit) {
        return instance.getUsers().size() <= limit;
    }
}
