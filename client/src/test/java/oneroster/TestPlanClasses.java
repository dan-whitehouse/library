package oneroster;

import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.client.oneroster.response.model.Class;
import org.ricone.library.exception.InvalidPathException;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestPlanClasses {
    private final boolean showResponseDetails, showTestResults, showTypes, showJson;
    private boolean c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30;

    public TestPlanClasses(boolean showResponseDetails, boolean showTestResults, boolean showTypes, boolean showJson) {
        this.showResponseDetails = showResponseDetails;
        this.showTestResults = showTestResults;
        this.showTypes = showTypes;
        this.showJson = showJson;
    }

    public void run(OneRoster oneRoster) throws InvalidPathException {
        final int LIMIT = 5;
        final String CLASS_SOURCED_ID = "11242C1C-B800-47DE-9FF3-3AF42A30EDEA";
        final String SCHOOL_SOURCED_ID = "78C72FBF-8C29-4107-BF2B-E3BA9F9849C5";
        final String TERM_SOURCED_ID = "2256BBE3-174F-459D-86A1-914751CBF3AB";
        final String COURSE_SOURCED_ID = "B547EBE3-34C0-4DE0-948B-DF1A902AB8D8";
        final String TEACHER_SOURCED_ID = "8BA06EA6-58FC-45D2-A6A3-8C2A34A7C82F";
        final String STUDENT_SOURCED_ID = "02983E3A-58A9-43ED-810F-12FED7536104";
        final String USER_SOURCED_ID = "8BA06EA6-58FC-45D2-A6A3-8C2A34A7C82F";

        //All
        c1 = testAll(oneRoster, ServicePath.GET_Classes);
        c2 = testAllWithPaging(oneRoster, ServicePath.GET_Classes, LIMIT);
        c3 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Classes, LIMIT, Field.Classes.title, SortOrder.DESC);
        c4 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Classes, LIMIT, new Filter(Field.Classes.subjects, Predicate.Contains, "Mathematics"));

        //By Id
        c5 = testById(oneRoster, ServicePath.GET_Class_By_SourcedId, CLASS_SOURCED_ID);
        c6 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_Class_By_SourcedId, CLASS_SOURCED_ID);

        //Classes By School
        c7 = testAllById(oneRoster, ServicePath.GET_Classes_By_School_SourcedId, SCHOOL_SOURCED_ID);
        c8 = testAllByIdWithPaging(oneRoster, ServicePath.GET_Classes_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT);
        c9 = testAllByIdWithPagingAndSorting(oneRoster, ServicePath.GET_Classes_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, Field.Classes.title, SortOrder.DESC);
        c10 = testAllByIdWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Classes_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, new Filter(Field.Classes.subjects, Predicate.Contains, "Mathematics"));

        //Classes By Term
        c11 = testAllById(oneRoster, ServicePath.GET_Classes_By_Term_SourcedId, TERM_SOURCED_ID);
        c12 = testAllByIdWithPaging(oneRoster, ServicePath.GET_Classes_By_Term_SourcedId, TERM_SOURCED_ID, LIMIT);
        c13 = testAllByIdWithPagingAndSorting(oneRoster, ServicePath.GET_Classes_By_Term_SourcedId, TERM_SOURCED_ID, LIMIT, Field.Classes.title, SortOrder.DESC);
        c14 = testAllByIdWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Classes_By_Term_SourcedId, TERM_SOURCED_ID, LIMIT, new Filter(Field.Classes.subjects, Predicate.Contains, "Mathematics"));

        //Classes By Course
        c15 = testAllById(oneRoster, ServicePath.GET_Classes_By_Course_SourcedId, COURSE_SOURCED_ID);
        c16 = testAllByIdWithPaging(oneRoster, ServicePath.GET_Classes_By_Course_SourcedId, COURSE_SOURCED_ID, LIMIT);
        c17 = testAllByIdWithPagingAndSorting(oneRoster, ServicePath.GET_Classes_By_Course_SourcedId, COURSE_SOURCED_ID, LIMIT, Field.Classes.title, SortOrder.DESC);
        c18 = testAllByIdWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Classes_By_Course_SourcedId, COURSE_SOURCED_ID, LIMIT, new Filter(Field.Classes.subjects, Predicate.Contains, "Mathematics"));

        //Classes By Teacher
        c19 = testAllById(oneRoster, ServicePath.GET_Classes_By_Teacher_SourcedId, TEACHER_SOURCED_ID);
        c20 = testAllByIdWithPaging(oneRoster, ServicePath.GET_Classes_By_Teacher_SourcedId, TEACHER_SOURCED_ID, LIMIT);
        c21 = testAllByIdWithPagingAndSorting(oneRoster, ServicePath.GET_Classes_By_Teacher_SourcedId, TEACHER_SOURCED_ID, LIMIT, Field.Classes.title, SortOrder.DESC);
        c22 = testAllByIdWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Classes_By_Teacher_SourcedId, TEACHER_SOURCED_ID, LIMIT, new Filter(Field.Classes.subjects, Predicate.Contains, "Mathematics"));

        //Classes By Student
        c23 = testAllById(oneRoster, ServicePath.GET_Classes_By_Student_SourcedId, STUDENT_SOURCED_ID);
        c24 = testAllByIdWithPaging(oneRoster, ServicePath.GET_Classes_By_Student_SourcedId, STUDENT_SOURCED_ID, LIMIT);
        c25 = testAllByIdWithPagingAndSorting(oneRoster, ServicePath.GET_Classes_By_Student_SourcedId, STUDENT_SOURCED_ID, LIMIT, Field.Classes.title, SortOrder.DESC);
        c26 = testAllByIdWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Classes_By_Student_SourcedId, STUDENT_SOURCED_ID, LIMIT, new Filter(Field.Classes.subjects, Predicate.Contains, "Science"));

        //Classes By USER
        c27 = testAllById(oneRoster, ServicePath.GET_Classes_By_User_SourcedId, USER_SOURCED_ID);
        c28 = testAllByIdWithPaging(oneRoster, ServicePath.GET_Classes_By_User_SourcedId, USER_SOURCED_ID, LIMIT);
        c29 = testAllByIdWithPagingAndSorting(oneRoster, ServicePath.GET_Classes_By_User_SourcedId, USER_SOURCED_ID, LIMIT, Field.Classes.title, SortOrder.DESC);
        c30 = testAllByIdWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Classes_By_User_SourcedId, USER_SOURCED_ID, LIMIT, new Filter(Field.Classes.subjects, Predicate.Contains, "Mathematics"));

        Filter filter = new Filter(Field.Classes.title, Predicate.Equals, "");
    }

    public void results() {
        boolean testsPassed = (
            c1 && c2 && c3 && c4
            && c5 && c6
            && c7 && c8 && c9 && c10
            && c11 && c12 && c13 && c14
            && c15 && c16 && c17 && c18
            && c19 && c20 && c21 && c22
            && c23 && c24 && c25 && c26
            && c27 && c28 && c29 && c30
        );
        System.out.println("All Class Tests Passed: " + testsPassed);
    }

    /** All **/
    private boolean testAll(OneRoster oneRoster, ServicePath servicePath) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData);
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllWithPaging(OneRoster oneRoster, ServicePath servicePath, int limit) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
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
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, int limit, Filter filter) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
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
                    .field(Field.Classes.title)
                    .field(Field.Classes.subjects)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean containsFilterValue = containsFilter(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(Class i : response.getData().getClasses()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " containsFilterValue: " + containsFilterValue + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && containsFilterValue && isFieldSelectionOnly;
    }

    /** By Id **/
    private boolean testById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Class> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId;
    }

    private boolean testByIdWithFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Class> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Classes.title)
                    .field(Field.Classes.subjects)
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId && isFieldSelectionOnly;
    }

    /** By Predicate **/
    private boolean testAllById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllByIdWithPaging(OneRoster oneRoster, ServicePath servicePath, String id, int limit) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllByIdWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, String id, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
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
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllByIdWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id, int limit, Filter filter) throws InvalidPathException {
        IResponse<Classes> response = oneRoster.request(Request.builder()
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
                    .field(Field.Classes.title)
                    .field(Field.Classes.subjects)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean containsFilterValue = containsFilter(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(Class i : response.getData().getClasses()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " containsFilterValue: " + containsFilterValue + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "homeroom(" + typeData[0] + ")" + " | scheduled(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && containsFilterValue && isFieldSelectionOnly;
    }

    /** Test Methods **/
    private boolean hasData(Class instance) {
        return instance != null && instance.hasData();
    }

    private boolean hasData(Classes instance) {
        return instance != null && !instance.getClasses().isEmpty();
    }

    private int[] typeCount(Classes instance) {
        int homeroom = 0, scheduled =  0, other = 0;

        for(Class i : instance.getClasses()) {
           if(ClassType.homeroom.equals(i.getClassType())) {
               homeroom++;
           }
           else if(ClassType.scheduled.equals(i.getClassType())) {
               scheduled++;
           }
           else {
               other++;
           }
        }
        return new int[]{homeroom, scheduled, other};
    }

    private int[] typeCount(Class instance) {
        int homeroom = 0, scheduled =  0, other = 0;

        if(ClassType.homeroom.equals(instance.getClassType())) {
            homeroom++;
        }
        else if(ClassType.scheduled.equals(instance.getClassType())) {
            scheduled++;
        }
        else {
            other++;
        }
        return new int[]{homeroom, scheduled, other};
    }

    private boolean isCorrectSourcedId(Class instance, String sourcedId) {
        return sourcedId.equalsIgnoreCase(instance.getSourcedId());
    }

    private boolean containsFilter(Classes instance, String filterValue) {
        for(Class i : instance.getClasses()) {
            //https://stackoverflow.com/questions/23379518/how-to-check-if-a-word-is-present-in-a-sentence-using-java
            if(!Pattern.matches(".*"+filterValue+"\\b.*", String.join(",", i.getSubjects()))) {
                return false;
            }
        }
        return true;
    }

    private boolean isFieldSelectionOnly(Class instance) {
        return Stream.of(
                instance.getStatus(),
                instance.getDateLastModified(),
                instance.getMetadata(),
                instance.getClassCode(),
                instance.getClassType(),
                instance.getLocation(),
                instance.getCourse(),
                instance.getSchool()
            ).allMatch(Objects::isNull)
            && instance.getGrades().isEmpty()
            && instance.getTerms().isEmpty()
            && instance.getSubjectCodes().isEmpty()
            && instance.getPeriods().isEmpty()
            && instance.getResources().isEmpty()
        ;
    }

    private boolean isListOrdered(Classes instance) {
        LinkedList<String> list = new LinkedList<>();
        instance.getClasses().forEach(i -> list.add(i.getTitle()));
        return list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).equals(list);
    }

    private boolean isCountLimited(Classes instance, int limit) {
        return instance.getClasses().size() <= limit;
    }
}
