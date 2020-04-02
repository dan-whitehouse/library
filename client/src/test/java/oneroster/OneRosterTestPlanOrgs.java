package oneroster;

import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.model.Org;
import org.ricone.library.client.oneroster.response.model.OrgType;
import org.ricone.library.client.oneroster.response.model.Orgs;
import org.ricone.library.client.oneroster.response.model.Type;
import org.ricone.library.exception.InvalidPathException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OneRosterTestPlanOrgs {

    public static void main(String[] args) throws InvalidPathException {
        Authenticator authenticator = Authenticator.builder()
                .url(System.getenv("url")).api(API.OneRoster)
                .credentials(System.getenv("username"), System.getenv("password"))
                .provider(System.getenv("provider"))
                .authenticate();

        Optional<Endpoint> endpoint = authenticator.getEndpoint("localhost");
        if(endpoint.isPresent()) {
            OneRoster oneRoster = new OneRoster(endpoint.get());
            test(oneRoster);
        }
    }

    private static void test(OneRoster oneRoster) throws InvalidPathException {
        final int LIMIT = 5;
        final String ORG_SOURCED_ID = "03ACF04F-DC12-411A-9A42-E8323516D699";
        final String SCHOOL_SOURCED_ID = "78C72FBF-8C29-4107-BF2B-E3BA9F9849C5";

        //Orgs
        testAll(oneRoster, ServicePath.GET_Orgs);
        testAllWithPaging(oneRoster, ServicePath.GET_Orgs, LIMIT);
        testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Orgs, LIMIT, Field.Orgs.name, SortOrder.DESC);
        testAllWithPagingAndFilteringAndFieldSelectionSourcedIdAndName(oneRoster, ServicePath.GET_Orgs, LIMIT, Field.Orgs.name, "Schalmont CSD");
        testById(oneRoster, ServicePath.GET_Org_By_SourcedId, ORG_SOURCED_ID);
        testByIdWithFieldSelectionSourcedIdAndName(oneRoster, ServicePath.GET_Org_By_SourcedId, ORG_SOURCED_ID);

        //Schools
        testAll(oneRoster, ServicePath.GET_Schools);
        testAllWithPaging(oneRoster, ServicePath.GET_Schools, LIMIT);
        testAllWithPagingAndSorting(oneRoster, ServicePath.GET_Schools, LIMIT, Field.Orgs.name, SortOrder.DESC);
        testAllWithPagingAndFilteringAndFieldSelectionSourcedIdAndName(oneRoster, ServicePath.GET_Schools, LIMIT, Field.Orgs.name, "Schalmont HS");
        testById(oneRoster, ServicePath.GET_School_By_SourcedId, SCHOOL_SOURCED_ID);
        testByIdWithFieldSelectionSourcedIdAndName(oneRoster, ServicePath.GET_School_By_SourcedId, SCHOOL_SOURCED_ID);
    }

    private static void testAll(OneRoster oneRoster, ServicePath servicePath) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        System.out.println("\t\u21b3 hasData: " + hasData);
        System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        System.out.println("\t\u21b3\n" + Util.getResponseJson(response));
    }

    private static void testAllWithPaging(OneRoster oneRoster, ServicePath servicePath, int limit) throws InvalidPathException {
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
        System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited);
        System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        System.out.println("\t\u21b3\n" + Util.getResponseJson(response));
    }

    private static void testAllWithPagingAndSorting(OneRoster oneRoster, ServicePath servicePath, int limit, IField field, SortOrder order) throws InvalidPathException {
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
        System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isSorted: " + isSorted);
        System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        System.out.println("\t\u21b3\n" + Util.getResponseJson(response));
    }

    private static void testAllWithPagingAndFilteringAndFieldSelectionSourcedIdAndName(OneRoster oneRoster, ServicePath servicePath, int limit, IField filterField, String filterValue) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
            .request()
                .path(servicePath)
            .end()
            .with()
                .paging()
                    .limit(limit)
                .end()
                .filtering()
                    .filter(filterField, Predicate.Equals, filterValue)
                .end()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Orgs.name)
                .end()
            .end()
        .build());

        boolean hasData = hasData(response.getData());
        boolean isLimited = isCountLimited(response.getData().getOrgs(), limit);
        boolean isCorrectName = isCorrectName(response.getData().getOrgs(), filterValue);
        boolean isSourcedIdAndNameOnly = true;

        for(Org org : response.getData().getOrgs()) {
            if(!isSourcedIdAndNameOnly(org)) {
                isSourcedIdAndNameOnly = false;
            }
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isLimited: " + isLimited + " | " + " isCorrectName: " + isCorrectName + " | " + " isSourcedIdAndNameOnly: " + isSourcedIdAndNameOnly);
        System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        System.out.println("\t\u21b3\n" + Util.getResponseJson(response));
    }

    private static void testById(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
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
        System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isCorrectSourcedId: " + isCorrectSourcedId);
        System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        System.out.println("\t\u21b3\n" + Util.getResponseJson(response));
    }

    private static void testByIdWithFieldSelectionSourcedIdAndName(OneRoster oneRoster, ServicePath servicePath, String id) throws InvalidPathException {
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
        boolean isSourcedIdAndNameOnly = false;

        if(isSourcedIdAndNameOnly(response.getData())) {
            isSourcedIdAndNameOnly = true;
        }

        int[] typeData = typeCount(response.getData());

        System.out.println(response.getRequestPath());
        System.out.println("\t\u21b3 " + Util.getResponseDetails(response));
        System.out.println("\t\u21b3 hasData: " + hasData + " | " + " isCorrectSourcedId: " + isCorrectSourcedId + " | " + " isSourcedIdAndNameOnly: " + isSourcedIdAndNameOnly);
        System.out.println("\t\u21b3 types: " + "district(" + typeData[0] + ")" + " | school(" + typeData[1] + ")" + " | other(" + typeData[2] + ")");
        System.out.println("\t\u21b3\n" + Util.getResponseJson(response));
    }

    /** Test Methods **/
    private static boolean hasData(Org org) {
        return org != null && org.hasData();
    }

    private static boolean hasData(Orgs orgs) {
        return orgs != null && !orgs.getOrgs().isEmpty();
    }

    private static int[] typeCount(Orgs orgs) {
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

    private static int[] typeCount(Org org) {
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

    private static boolean isCorrectSourcedId(Org org, String sourcedId) {
        return sourcedId.equalsIgnoreCase(org.getSourcedId());
    }

    private static boolean isCorrectName(List<Org> orgs, String name) {
        for(Org org : orgs) {
            if(!name.equalsIgnoreCase(org.getName())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSourcedIdAndNameOnly(Org org) {
        return Stream.of(
            org.getStatus(),
            org.getDateLastModified(),
            org.getMetadata(),
            org.getType(),
            org.getIdentifier(),
            org.getParent()
        ).allMatch(Objects::isNull) && org.getChildren().isEmpty() && org.getSourcedId() != null && org.getName() != null;
    }

    private static boolean isListOrdered(List<Org> orgs) {
        LinkedList<String> names = new LinkedList<>();
        orgs.forEach(org -> names.add(org.getName()));

        return names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).equals(names);
    }

    private static boolean isCountLimited(List<Org> l, int limit) {
        return l.size() <= limit;
    }
}
