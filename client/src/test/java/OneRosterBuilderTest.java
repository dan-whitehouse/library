import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.OffsetResponse;
import org.ricone.library.client.oneroster.response.Response;
import org.ricone.library.client.core.Util;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.exception.InvalidPathException;

import java.util.Optional;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public class OneRosterBuilderTest {
    public static void main(String[] args) throws InvalidPathException {

        Authenticator authenticator = Authenticator.builder()
            .url(System.getenv("url")).api(API.OneRoster)
            .credentials(System.getenv("username"), System.getenv("password"))
            .provider(System.getenv("provider"))
        .authenticate();

        Optional<Endpoint> endpoint = authenticator.getEndpoint("localhost");
        if(endpoint.isPresent()) {
            OneRoster oneRoster = new OneRoster(endpoint.get());

            //offsetTest(oneRoster);
            //allFeaturesTest(oneRoster);
            //buildVerifyTest(oneRoster);
            //responseInterfaceTest(oneRoster);
            requestTest(oneRoster);


            //orgTest(oneRoster);
            //academicSessionsTest(oneRoster);
            //coursesTest(oneRoster);
            //classesTest(oneRoster);
            //enrollmentsTest(oneRoster);
            //usersTest(oneRoster);
            //demographicsTest(oneRoster);
        }
    }

    private static void orgTest(OneRoster oneRoster) throws InvalidPathException {
        Response<Org> response = oneRoster.getOrg(Request.builder()
            .request()
                .path(ServicePath.GET_Org_By_SourcedId)
                .ids().id("03ACF04F-DC12-411A-9A42-E8323516D699").end()
            .end()
        .build());

        Util.debugResponse(response);
    }

    private static void academicSessionsTest(OneRoster oneRoster) throws InvalidPathException {
        Response<AcademicSessions> response = oneRoster.getAcademicSessions(Request.builder()
            .request().path(ServicePath.GET_AcademicSessions).end()
            .with().paging().limit(2).end().end()
        .build());
        Util.debugResponse(response);
    }

    private static void coursesTest(OneRoster oneRoster) throws InvalidPathException {
        Response<Courses> response = oneRoster.getCourses(Request.builder()
            .request().path(ServicePath.GET_Courses).end()
            .with().paging().limit(2).end().end()
        .build());
        Util.debugResponse(response);
    }

    private static void classesTest(OneRoster oneRoster) throws InvalidPathException {
        Response<Classes> response = oneRoster.getClasses(Request.builder()
            .request().path(ServicePath.GET_Classes).end()
            .with().paging().limit(2).end().end()
        .build());
        Util.debugResponse(response);
    }

    private static void enrollmentsTest(OneRoster oneRoster) throws InvalidPathException {
        Response<Enrollments> response = oneRoster.getEnrollments(Request.builder()
            .request().path(ServicePath.GET_Enrollments).end()
            .with().paging().limit(2).end().end()
        .build());
        Util.debugResponse(response);
    }

    private static void usersTest(OneRoster oneRoster) throws InvalidPathException {
        Response<Users> response = oneRoster.getUsers(Request.builder()
            .request().path(ServicePath.GET_Users).end()
            .with().paging().limit(2).end().end()
        .build());
        Util.debugResponse(response);
    }

    private static void demographicsTest(OneRoster oneRoster) throws InvalidPathException {
        Response<Demographics> response = oneRoster.getDemographics(Request.builder()
            .request().path(ServicePath.GET_Demographics).end()
            .with().paging().limit(2).end().end()
        .build());
        Util.debugResponse(response);
    }

    private static void offsetTest(OneRoster oneRoster) throws InvalidPathException {
        int limit = 2;
        OffsetResponse offset = oneRoster.getOffset(Request.builder()
            .request().path(ServicePath.GET_Orgs).end()
            .with().paging().limit(limit).end().end()
        .build());

        while(offset.hasNext()) {
            Response<Orgs> response = oneRoster.getOrgs(Request.builder()
                .request()
                    .path(ServicePath.GET_Orgs)
                .end()
                .with()
                    .paging()
                        .limit(limit)
                        .offset(offset.next())
                    .end()
                .end()
            .build());
            Util.debugResponse(response);
        }
    }

    private static void allFeaturesTest(OneRoster oneRoster) throws InvalidPathException {
        int limit = 1;
        Filter filter1 = new Filter(Field.Orgs.type, Predicate.Equals, "school");
        Filter filter2 = new Filter(Field.Orgs.name, Predicate.Contains, "HS");

        OffsetResponse offset = oneRoster.getOffset(Request.builder()
            .request().path(ServicePath.GET_Orgs).end()
            .with()
                .paging().limit(limit).end()
                .filtering().filter(filter1).and().filter(filter2).end()
            .end()
        .build());

        /* The offset request above requires both the paging and filtering as they are used below.
           The sorting and field selection features used below are/can be ignored, as they don't
           alter the number of records returned.
         */

        while(offset.hasNext()) {
            Response<Orgs> response = oneRoster.getOrgs(Request.builder()
                .request()
                    .path(ServicePath.GET_Orgs)
                .end()
                .with()
                    .paging()
                        .limit(limit)
                        .offset(offset.next()).end()
                    .filtering()
                        .filter(filter1)
                        .and()
                        .filter(filter2).end()
                    .fieldSelection()
                        .field(Field.sourcedId)
                        .field(Field.Orgs.name)
                        .field(Field.Orgs.type).end()
                    .sorting()
                        .field(Field.sourcedId)
                        .orderBy(SortOrder.ASC).end()
                .end()
            .build());

            Util.debugResponseJsonNoXml(response);
        }
    }

    private static void buildVerifyTest(OneRoster oneRoster) throws InvalidPathException {
        Response<Orgs> response = oneRoster.getOrgs(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
                //.ids().id("1").end()
            .end()
            .with()
                .paging().offset(0).limit(5).end()
                .sorting().field(Field.Orgs.name).orderBy(SortOrder.DESC).end()
                .filtering()
                    //.filter(Field.sourcedId, Predicate.Equals, "03ACF04F-DC12-411A-9A42-E8323516D699").and()
                    .filter(Field.Orgs.type, Predicate.Equals, "school").end()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.Orgs.name)
                    .end()
            .end()
        .build());
        Util.debugResponseJsonNoXml(response);
    }

    private static void responseInterfaceTest(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.getOrgs(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
            .end()
            .with()
                .paging().limit(5).end()
            .end()
        .build());

        Util.debugResponseJsonNoXml(response);
    }

    private static void requestTest(OneRoster oneRoster) throws InvalidPathException {
        IResponse<Orgs> response = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
            .end()
            .with()
                .paging().limit(5).end()
            .end()
        .build());

        Util.debugResponseJsonNoXml(response);
    }

    private static void allTheWays(OneRoster oneRoster) throws InvalidPathException {
        /* Interface */
        IResponse<Orgs> response = oneRoster.getOrgs(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
                .end()
            .with()
                .paging().limit(5).end()
            .end()
        .build());

        IResponse<Orgs> response2 = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
                .end()
            .with()
                .paging().limit(5).end()
            .end()
        .build());

        /* Abstract Class */
        Response<Orgs> response3 = oneRoster.getOrgs(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
                .end()
            .with()
                .paging().limit(5).end()
            .end()
        .build());

        Response<Orgs> response4 = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
                .end()
            .with()
                .paging().limit(5).end()
            .end()
        .build());

        /* Class */
        OrgsResponse response5 = oneRoster.getOrgs(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
            .end()
            .with()
                .paging().limit(5).end()
            .end()
        .build());

        OrgsResponse response6 = oneRoster.request(Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
            .end()
            .with()
                .paging().limit(5).end()
            .end()
        .build());
    }
}
