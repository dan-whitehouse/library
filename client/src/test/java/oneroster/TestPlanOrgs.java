package oneroster;

import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.model.Org;
import org.ricone.library.client.oneroster.response.model.OrgType;
import org.ricone.library.client.oneroster.response.model.Orgs;
import org.ricone.library.exception.InvalidPathException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestPlanOrgs {
    private final boolean showResponseDetails, showTestResults, showTypes, showJson;
    private boolean o1, o2, o3, o4, o5, o6;
    private boolean s1, s2, s3, s4, s5, s6;

    public TestPlanOrgs(boolean showResponseDetails, boolean showTestResults, boolean showTypes, boolean showJson) {
        this.showResponseDetails = showResponseDetails;
        this.showTestResults = showTestResults;
        this.showTypes = showTypes;
        this.showJson = showJson;
    }

    public void run(OneRoster oneRoster) throws InvalidPathException {
        final int LIMIT = 5;
        final String ORG_SOURCED_ID = "03ACF04F-DC12-411A-9A42-E8323516D699";
        final String SCHOOL_SOURCED_ID = "78C72FBF-8C29-4107-BF2B-E3BA9F9849C5";

        //Orgs
        o1 = testAll(oneRoster, ServicePath.GET_Orgs);
        o2 = testAllWithPaging(oneRoster, ServicePath.GET_Orgs, LIMIT);
        o3 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Orgs, LIMIT, Field.Orgs.name, SortOrder.DESC);
        o4 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Orgs, LIMIT, new Filter(Field.Orgs.name, Predicate.Equals, "Schalmont CSD"));
        o5 = testById(oneRoster, ServicePath.GET_Org_By_SourcedId, ORG_SOURCED_ID);
        o6 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_Org_By_SourcedId, ORG_SOURCED_ID);

        //Schools
        s1 = testAll(oneRoster, ServicePath.GET_Schools);
        s2 = testAllWithPaging(oneRoster, ServicePath.GET_Schools, LIMIT);
        s3 = testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Schools, LIMIT, Field.Orgs.name, SortOrder.DESC);
        s4 = testAllWithPagingAndFilteringAndFieldSelection(oneRoster, ServicePath.GET_Schools, LIMIT, new Filter(Field.Orgs.name, Predicate.Equals, "Schalmont HS"));
        s5 = testById(oneRoster, ServicePath.GET_School_By_SourcedId, SCHOOL_SOURCED_ID);
        s6 = testByIdWithFieldSelection(oneRoster, ServicePath.GET_School_By_SourcedId, SCHOOL_SOURCED_ID);
    }

    public void results() {
        boolean testsPassed = (o1 && o2 && o3 && o4 && o5 && o6 && s1 && s2 && s3 && s4 && s5 && s6);
        System.out.println("All Org Tests Passed: " + testsPassed);
    }

    private boolean testAll(OneRoster oneRoster, ServicePath servicePath) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData);
        if(showTypes) System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData;
    }

    private boolean testAllWithPaging(OneRoster oneRoster, ServicePath servicePath, int limit) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
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
        boolean isLimited = isCountLimited(response.getData().getOrgs(), limit);
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited);
        if(showTypes) System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited;
    }

    private boolean testAllWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, int limit, IField field, SortOrder order) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
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
        boolean isLimited = isCountLimited(response.getData().getOrgs(), limit);
        boolean isSorted = isListOrdered(response.getData().getOrgs());
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        if(showTypes) System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isSorted;
    }

    private boolean testAllWithPagingAndFilteringAndFieldSelection(OneRoster oneRoster, ServicePath servicePath, int limit, Filter filter) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
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
                    .field(Field.Orgs.name)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData().getOrgs(), limit);
        boolean isCorrectName = isCorrectName(response.getData().getOrgs(), filter.getValue());
        boolean isFieldSelectionOnly = true;

        for(Org org : response.getData().getOrgs()) {
            if(!isFieldSelectionOnly(org)) {
                isFieldSelectionOnly = false;
            }
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        if(showResponseDetails) System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        if(showTestResults) System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isCorrectName: " + isCorrectName + " | " + " isFieldSelectionOnly: " + isFieldSelectionOnly);
        if(showTypes) System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isLimited && isCorrectName && isFieldSelectionOnly;
    }

    private boolean testById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Org> response = oneRoster.request(Request.builder()
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId;
    }

    private boolean testByIdWithFieldSelection(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
        IResponse<Org> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
                .ids()
                    .id(id)
                .end()
            .end()
            .with()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Orgs.name)
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
        if(showTypes) System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        if(showJson) System.out.println("\t\u21b3\n" + Util.getResponseJson(response));

        return hasData && isCorrectSourcedId && isFieldSelectionOnly;
    }

    /** Test Methods **/
    private boolean hasData(Org org) {
        return org != null && org.hasData();
    }

    private boolean hasData(Orgs orgs) {
        return orgs != null && !orgs.getOrgs().isEmpty();
    }

    private int[] typeCount(Orgs orgs) {
        int district = 0, school =  0, other = 0;

        for(Org org : orgs.getOrgs()) {
           if(OrgType.district.equals(org.getType())) {
               district++;
           }
           else if(OrgType.school.equals(org.getType())) {
               school++;
           }
           else {
               other++;
           }
        }
        return new int[]{district, school, other};
    }

    private int[] typeCount(Org org) {
        int district = 0, school =  0, other = 0;

        if(OrgType.district.equals(org.getType())) {
            district++;
        }
        else if(OrgType.school.equals(org.getType())) {
            school++;
        }
        else {
            other++;
        }
        return new int[]{district, school, other};
    }

    private boolean isCorrectSourcedId(Org org, String sourcedId) {
        return sourcedId.equalsIgnoreCase(org.getSourcedId());
    }

    private boolean isCorrectName(List<Org> orgs, String name) {
        for(Org org : orgs) {
            if(!name.equalsIgnoreCase(org.getName())) {
                return false;
            }
        }
        return true;
    }

    private boolean isFieldSelectionOnly(Org org) {
        return Stream.of(
            org.getStatus(),
            org.getDateLastModified(),
            org.getMetadata(),
            org.getType(),
            org.getIdentifier(),
            org.getParent()
        ).allMatch(Objects::isNull) && org.getChildren().isEmpty();
    }

    private boolean isListOrdered(List<Org> orgs) {
        LinkedList<String> names = new LinkedList<>();
        orgs.forEach(org -> names.add(org.getName()));

        return names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).equals(names);
    }

    private boolean isCountLimited(List<Org> l, int limit) {
        return l.size() <= limit;
    }
}
