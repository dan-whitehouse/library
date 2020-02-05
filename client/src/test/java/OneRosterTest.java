import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Util;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.OffsetResponse;
import org.ricone.library.client.oneroster.response.Response;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.exception.InvalidPathException;

import java.util.Optional;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-30
 */

public class OneRosterTest {
    public static void main(String[] args) throws InvalidPathException {

        Authenticator authenticator = Authenticator.builder()
            .url(System.getenv("url")).api(API.OneRoster)
            .credentials(System.getenv("username"), System.getenv("password"))
            .provider(System.getenv("provider"))
        .authenticate();

        Optional<Endpoint> endpoint = authenticator.getEndpoint("localhost");
        if(endpoint.isPresent()) {
            OneRoster2 oneRoster = new OneRoster2(endpoint.get());

            test(oneRoster);

        }
    }


    private static void test(OneRoster2 oneRoster) throws InvalidPathException {
        int limit = 2;
        OffsetResponse offset = oneRoster.getOffset(Request.builder()
            .request().path(ServicePath.GET_Orgs).end()
            .with()
                .paging().limit(limit).end()
                .filtering().filter(Field.Orgs.type, Predicate.Equals, OrgType.school).end()
            .end()
        .build());

        while(offset.hasNext()) {
            Response<Orgs> response = oneRoster.getOrgs(Request.builder()
                .request().path(ServicePath.GET_Orgs).end()
                .with()
                    .paging().limit(limit).offset(offset.next()).end()
                    .filtering().filter(Field.Orgs.type, Predicate.Equals, OrgType.school).end()
                .end()
            .build());
            Util.debugResponseJsonNoXml(response);

            for(Org org : response.getData().getOrgs()) {
                System.out.println("Org: " + org.getName());

                IResponse<AcademicSessions> courseResponse = oneRoster.getAcademicSessions(Request.builder()
                    .request()
                        .path(ServicePath.GET_Terms_By_School_SourcedId)
                        .ids().id(org.getSourcedId()).end()
                    .end()
                .build());
                Util.debugResponseJsonNoXml(courseResponse);
            }
        }
    }
}
