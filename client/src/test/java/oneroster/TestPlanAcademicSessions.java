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

public class TestPlanAcademicSessions {
    private final boolean showResponseDetails, showTestResults, showTypes, showJson;
    private boolean a1, a2, a3, a4, a5, a6;
    private boolean t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;

    public TestPlanAcademicSessions(boolean showResponseDetails, boolean showTestResults, boolean showTypes, boolean showJson) {
        this.showResponseDetails = showResponseDetails;
        this.showTestResults = showTestResults;
        this.showTypes = showTypes;
        this.showJson = showJson;
    }

    public void run(OneRoster oneRoster) throws InvalidPathException {
        final int LIMIT = 5;
        final String ACADEMIC_SESSION_SOURCED_ID = "DB9BC130-3DA2-4E74-A5F8-79B8D0525047";
        final String TERM_SOURCED_ID = "E903F225-588C-4EF4-98CB-BBD54945B424";
        final String SCHOOL_SOURCED_ID = "78C72FBF-8C29-4107-BF2B-E3BA9F9849C5";

        //Academic Sessions
        a1 = testAll(oneRoster, ServicePath.GET_AcademicSessions);
        a2 = testAllWithPaging(oneRoster, ServicePath.GET_AcademicSessions, LIMIT);
        a3 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_AcademicSessions, LIMIT, Field.AcademicSessions.title, SortOrder.DESC);
        a4 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_AcademicSessions, LIMIT, new Filter(Field.AcademicSessions.title, Predicate.Equals, "2"));
        a5 = testById(oneRoster, ServicePath.GET_AcademicSession_By_SourcedId, ACADEMIC_SESSION_SOURCED_ID);
        a6 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_AcademicSession_By_SourcedId, ACADEMIC_SESSION_SOURCED_ID);

        //Terms
        t1 = testAll(oneRoster, ServicePath.GET_Terms);
        t2 = testAllWithPaging(oneRoster, ServicePath.GET_Terms, LIMIT);
        t3 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Terms, LIMIT, Field.AcademicSessions.title, SortOrder.DESC);
        t4 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Terms, LIMIT, new Filter(Field.AcademicSessions.title, Predicate.Equals, "2"));
        t5 = testById(oneRoster, ServicePath.GET_Term_By_SourcedId, TERM_SOURCED_ID);
        t6 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_Term_By_SourcedId, TERM_SOURCED_ID);

        //Terms By School
        t7 = testAllById(oneRoster, ServicePath.GET_Terms_By_School_SourcedId, SCHOOL_SOURCED_ID);
        t8 = testAllByIdWithPaging(oneRoster, ServicePath.GET_Terms_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT);
        t9 = testAllByIdWithPagingAndSorting(oneRoster, ServicePath.GET_Terms_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, Field.AcademicSessions.title, SortOrder.DESC);
        t10 = testAllByIdWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Terms_By_School_SourcedId, SCHOOL_SOURCED_ID, LIMIT, new Filter(Field.AcademicSessions.title, Predicate.Equals, "2"));
    }

    public void results() {
        boolean testsPassed = (a1 && a2 && a3 && a4 && a5 && a6 && t1 && t2 && t3 && t4 && t5 && t6 && t7 && t8 && t9 && t10);
        System.out.println("All Academic Session Tests Passed: " + testsPassed);
    }

    /** All **/
    private boolean testAll(OneRoster oneRoster, ServicePath servicePath) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData);
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllWithPaging(OneRoster oneRoster, ServicePath servicePath, int limit) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, int limit, Filter filter) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
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
                    .field(Field.AcademicSessions.title)
                    .field(Field.AcademicSessions.startDate)
                    .field(Field.AcademicSessions.endDate)
                    .field(Field.AcademicSessions.type)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean isCorrectName = isCorrectTitle(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(AcademicSession i : response.getData().getAcademicSessions()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isCorrectName: " + isCorrectName + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isCorrectName && isFieldSelectionOnly;
    }

    /** By Id **/
    private boolean testById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<AcademicSession> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId;
    }

    private boolean testByIdWithFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<AcademicSession> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.AcademicSessions.title)
                    .field(Field.AcademicSessions.startDate)
                    .field(Field.AcademicSessions.endDate)
                    .field(Field.AcademicSessions.type)
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId && isFieldSelectionOnly;
    }

    /** By Predicate **/
    private boolean testAllById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllByIdWithPaging(OneRoster oneRoster, ServicePath servicePath, String id, int limit) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllByIdWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, String id, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllByIdWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id, int limit, Filter filter) throws InvalidPathException {
        IResponse<AcademicSessions> response = oneRoster.request(Request.builder()
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
                    .field(Field.AcademicSessions.title)
                    .field(Field.AcademicSessions.startDate)
                    .field(Field.AcademicSessions.endDate)
                    .field(Field.AcademicSessions.type)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean isCorrectName = isCorrectTitle(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(AcademicSession i : response.getData().getAcademicSessions()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isCorrectName: " + isCorrectName + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "schoolYear(" + typeData[0] + ")" + " | term(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isCorrectName && isFieldSelectionOnly;
    }

    /** Test Methods **/
    private boolean hasData(AcademicSession instance) {
        return instance != null && instance.hasData();
    }

    private boolean hasData(AcademicSessions instance) {
        return instance != null && !instance.getAcademicSessions().isEmpty();
    }

    private int[] typeCount(AcademicSessions instance) {
        int schoolYear = 0, term =  0, other = 0;

        for(AcademicSession i : instance.getAcademicSessions()) {
           if(SessionType.schoolYear.equals(i.getType())) {
               schoolYear++;
           }
           else if(SessionType.term.equals(i.getType())) {
               term++;
           }
           else {
               other++;
           }
        }
        return new int[]{schoolYear, term, other};
    }

    private int[] typeCount(AcademicSession instance) {
        int schoolYear = 0, term =  0, other = 0;

        if(SessionType.schoolYear.equals(instance.getType())) {
            schoolYear++;
        }
        else if(SessionType.term.equals(instance.getType())) {
            term++;
        }
        else {
            other++;
        }
        return new int[]{schoolYear, term,  other};
    }

    private boolean isCorrectSourcedId(AcademicSession instance, String sourcedId) {
        return sourcedId.equalsIgnoreCase(instance.getSourcedId());
    }

    private boolean isCorrectTitle(AcademicSessions instance, String title) {
        for(AcademicSession i : instance.getAcademicSessions()) {
            if(!title.equalsIgnoreCase(i.getTitle())) {
                return false;
            }
        }
        return true;
    }

    private boolean isFieldSelectionOnly(AcademicSession instance) {
        return Stream.of(
            instance.getStatus(),
            instance.getDateLastModified(),
            instance.getMetadata(),
            instance.getParent(),
            instance.getSchoolYear()
        ).allMatch(Objects::isNull) && instance.getChildren().isEmpty();
    }

    private boolean isListOrdered(AcademicSessions instance) {
        LinkedList<String> list = new LinkedList<>();
        instance.getAcademicSessions().forEach(i -> list.add(i.getTitle()));
        return list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).equals(list);
    }

    private boolean isCountLimited(AcademicSessions instance, int limit) {
        return instance.getAcademicSessions().size() <= limit;
    }
}
