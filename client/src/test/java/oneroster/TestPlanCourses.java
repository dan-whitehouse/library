package oneroster;

import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.exception.InvalidPathException;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestPlanCourses {
    private final boolean showResponseDetails, showTestResults, showTypes, showJson;
    private boolean c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

    public TestPlanCourses(boolean showResponseDetails, boolean showTestResults, boolean showTypes, boolean showJson) {
        this.showResponseDetails = showResponseDetails;
        this.showTestResults = showTestResults;
        this.showTypes = showTypes;
        this.showJson = showJson;
    }

    public void run(OneRoster oneRoster) throws InvalidPathException {
        final int LIMIT = 5;
        final String COURSE_SOURCED_ID = "B547EBE3-34C0-4DE0-948B-DF1A902AB8D8";
        final String SCHOOL_SOURCED_ID = "78C72FBF-8C29-4107-BF2B-E3BA9F9849C5";

        //All
        c1 = testAll(oneRoster, ServicePath.GET_Courses);
        c2 = testAllWithPaging(oneRoster, ServicePath.GET_Courses, LIMIT);
        c3 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Courses, LIMIT, Field.Courses.title, SortOrder.DESC);
        c4 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Courses, LIMIT, new Filter(Field.Courses.title, Predicate.Equals, "Algebra II"));

        //By Id
        c5 = testById(oneRoster, ServicePath.GET_Course_By_SourcedId, COURSE_SOURCED_ID);
        c6 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_Course_By_SourcedId, COURSE_SOURCED_ID);

        //Terms By School
        c7 = testAllById(oneRoster, ServicePath.GET_Courses_By_School_SourcedId, SCHOOL_SOURCED_ID);
        c8 = testAllByIdWithPaging(oneRoster, ServicePath.GET_Courses_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT);
        c9 = testAllByIdWithPagingAndSorting(oneRoster, ServicePath.GET_Courses_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, Field.Courses.title, SortOrder.DESC);
        c10 = testAllByIdWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Courses_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT,new Filter(Field.Courses.title, Predicate.Equals, "Algebra II"));
    }

    public void results() {
        boolean testsPassed = (c1 && c2 && c3 && c4 && c5 && c6 && c7 && c8 && c9 && c10);
        System.out.println("All Course Tests Passed: " + testsPassed);
    }

    /** All **/
    private boolean testAll(OneRoster oneRoster, ServicePath servicePath) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
        .build());

        boolean hasData = hasData(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllWithPaging(OneRoster oneRoster, ServicePath servicePath, int limit) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
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

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
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
        boolean isSorted = isListOrdered(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, int limit, Filter filter) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
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
                    .field(Field.Courses.title)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean isCorrectName = isCorrectTitle(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(Course i : response.getData().getCourses()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isCorrectName: " + isCorrectName + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isCorrectName && isFieldSelectionOnly;
    }

    /** By Id **/
    private boolean testById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Course> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isCorrectSourcedId = isCorrectSourcedId(response.getData(), id);

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isCorrectSourcedId: " + isCorrectSourcedId);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId;
    }

    private boolean testByIdWithFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Course> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Courses.title)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isCorrectSourcedId = isCorrectSourcedId(response.getData(), id);
        boolean isFieldSelectionOnly = false;

        if(isFieldSelectionOnly(response.getData())) {
            isFieldSelectionOnly = true;
        }

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isCorrectSourcedId: " + isCorrectSourcedId + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId && isFieldSelectionOnly;
    }

    /** By Predicate **/
    private boolean testAllById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllByIdWithPaging(OneRoster oneRoster, ServicePath servicePath, String id, int limit) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
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

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllByIdWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, String id, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
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
        boolean isSorted = isListOrdered(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllByIdWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id, int limit, Filter filter) throws InvalidPathException {
        IResponse<Courses> response = oneRoster.request(Request.builder()
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
                    .field(Field.Courses.title)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean isCorrectName = isCorrectTitle(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(Course i : response.getData().getCourses()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isCorrectName: " + isCorrectName + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isCorrectName && isFieldSelectionOnly;
    }

    /** Test Methods **/
    private boolean hasData(Course instance) {
        return instance != null && instance.hasData();
    }

    private boolean hasData(Courses instance) {
        return instance != null && !instance.getCourses().isEmpty();
    }

    private boolean isCorrectSourcedId(Course instance, String sourcedId) {
        return sourcedId.equalsIgnoreCase(instance.getSourcedId());
    }

    private boolean isCorrectTitle(Courses instance, String title) {
        for(Course i : instance.getCourses()) {
            if(!title.equalsIgnoreCase(i.getTitle())) {
                return false;
            }
        }
        return true;
    }

    private boolean isFieldSelectionOnly(Course instance) {
        return Stream.of(
            instance.getStatus(),
            instance.getDateLastModified(),
            instance.getMetadata(),
            instance.getSchoolYear(),
            instance.getCourseCode(),
            instance.getOrg()
        ).allMatch(Objects::isNull) && instance.getGrades().isEmpty() && instance.getSubjects().isEmpty() && instance.getSubjectCodes().isEmpty() && instance.getResources().isEmpty();
    }

    private boolean isListOrdered(Courses instance) {
        LinkedList<String> list = new LinkedList<>();
        instance.getCourses().forEach(i -> list.add(i.getTitle()));
        return list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).equals(list);
    }

    private boolean isCountLimited(Courses instance, int limit) {
        return instance.getCourses().size() <= limit;
    }
}
