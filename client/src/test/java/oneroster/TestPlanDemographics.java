package oneroster;

import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.exception.InvalidPathException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestPlanDemographics {
    private final boolean showResponseDetails, showTestResults, showTypes, showJson;
    private boolean c1, c2, c3, c4, c5, c6;

    public TestPlanDemographics(boolean showResponseDetails, boolean showTestResults, boolean showTypes, boolean showJson) {
        this.showResponseDetails = showResponseDetails;
        this.showTestResults = showTestResults;
        this.showTypes = showTypes;
        this.showJson = showJson;
    }

    public void run(OneRoster oneRoster) throws InvalidPathException {
        final int LIMIT = 5;
        final String DEMOGRAPHIC_SOURCED_ID = "015CF087-9590-4FD0-8EF8-37EF2253F210";

        //All - Users
        c1 = testAll(oneRoster, ServicePath.GET_Demographics);
        c2 = testAllWithPaging(oneRoster, ServicePath.GET_Demographics, LIMIT);
        c3 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Demographics, LIMIT, Field.Demographics.birthDate, SortOrder.DESC);
        c4 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Demographics, LIMIT, new Filter(Field.Demographics.hispanicOrLatinoEthnicity, Predicate.Equals, "true"));

        //By Id - Users
        c5 = testById(oneRoster, ServicePath.GET_Demographic_By_SourcedId, DEMOGRAPHIC_SOURCED_ID);
        c6 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_Demographic_By_SourcedId, DEMOGRAPHIC_SOURCED_ID);

    }

    public void results() {
        boolean testsPassed = (c1 && c2 && c3 && c4 && c5 && c6);
        System.out.println("All Demographic Tests Passed: " + testsPassed);
    }

    /** All **/
    private boolean testAll(OneRoster oneRoster, ServicePath servicePath) throws InvalidPathException {
        IResponse<Demographics> response = oneRoster.request(Request.builder()
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
        IResponse<Demographics> response = oneRoster.request(Request.builder()
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
        IResponse<Demographics> response = oneRoster.request(Request.builder()
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

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, int limit, Filter filter) throws InvalidPathException {
        IResponse<Demographics> response = oneRoster.request(Request.builder()
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
                    .field(Field.Demographics.birthDate)
                    .field(Field.Demographics.hispanicOrLatinoEthnicity)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData(), limit);
        boolean containsFilterValue = isFilter(response.getData(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(Demographic i : response.getData().getDemographics()) {
            if(!isFieldSelectionOnly(i)) {
                isFieldSelectionOnly = false;
            }
        }

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " containsFilterValue: " + containsFilterValue + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "N/A");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && containsFilterValue && isFieldSelectionOnly;
    }

    /** By Id **/
    private boolean testById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Demographic> response = oneRoster.request(Request.builder()
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
        IResponse<Demographic> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Demographics.birthDate)
                    .field(Field.Demographics.hispanicOrLatinoEthnicity)
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



    /** Test Methods **/
    private boolean hasData(Demographic instance) {
        return instance != null && instance.hasData();
    }

    private boolean hasData(Demographics instance) {
        return instance != null && !instance.getDemographics().isEmpty();
    }


    private boolean isCorrectSourcedId(Demographic instance, String sourcedId) {
        return sourcedId.equalsIgnoreCase(instance.getSourcedId());
    }

    private boolean isFilter(Demographics instance, String filterValue) {
        for(Demographic i : instance.getDemographics()) {
            if(!filterValue.equalsIgnoreCase(i.getHispanicOrLatinoEthnicity().toString())) {
                return false;
            }
        }
        return true;
    }

    private boolean isFieldSelectionOnly(Demographic instance) {
        return Stream.of(
            instance.getStatus(),
            instance.getDateLastModified(),
            instance.getMetadata(),
            instance.getSex(),
            instance.getAmericanIndianOrAlaskaNative(),
            instance.getAsian(),
            instance.getBlackOrAfricanAmerican(),
            instance.getNativeHawaiianOrOtherPacificIslander(),
            instance.getWhite(),
            instance.getDemographicRaceTwoOrMoreRaces(),
            instance.getCountryOfBirthCode(),
            instance.getStateOfBirthAbbreviation(),
            instance.getCityOfBirth(),
            instance.getPublicSchoolResidenceStatus()
        ).allMatch(Objects::isNull);
    }

    private boolean isListOrdered(Demographics instance, IField sortField) {
        List<String> originalRefIds = new LinkedList<>();
        List<String> manuallySortedRefIds = new LinkedList<>();

        instance.getDemographics().forEach(i -> originalRefIds.add(i.getBirthDate().toString())); //Take the refIds of the list
        manuallySortedRefIds = instance.getDemographics().stream().sorted(Comparator.comparing(demographic -> demographic.getBirthDate().toString(), String::compareToIgnoreCase)).map(demographic -> demographic.getBirthDate().toString()).collect(Collectors.toList());
        manuallySortedRefIds.sort(Collections.reverseOrder());

        return originalRefIds.equals(manuallySortedRefIds);
    }

    private boolean isCountLimited(Demographics instance, int limit) {
        return instance.getDemographics().size() <= limit;
    }
}
