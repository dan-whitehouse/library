import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.oneroster.request.*;
import org.ricone.library.client.oneroster.response.OffsetResponse;
import org.ricone.library.client.oneroster.response.Response;
import org.ricone.library.client.oneroster.response.model.Org;
import org.ricone.library.exception.InvalidPathException;

import java.util.List;

/**
 * @project: client
 * @author: Dan on 01/16/2020.
 */

public class OneRosterBuilderTest {
    public static void main(String[] args) throws InvalidPathException {

        OneRoster oneRoster = new OneRoster(new Endpoint());

        Response<Org> response = oneRoster.getOrg(Request.builder()
            .request()
                .path(ServicePath.GET_School_By_SourcedId)
                .ids().id("11111111-1111-1111-111111111").end()
            .end()
            .with()
                .paging().limit(50).end()
                .sorting().field(Field.sourcedId).end()
                .fieldSelection().field(Field.sourcedId).end()
                .filtering().filter(Field.status, Predicate.Equals, "active").end()
            .end()
        .build());


        //Paging Example
        OffsetResponse offset = oneRoster.getOffset(Request.builder()
            .request().path(ServicePath.GET_Orgs).end()
            .with()
                .paging().limit(250).end()
            .end()
        .build());

        while(offset.hasNext()) {
            Response<List<Org>> pagingResponse = oneRoster.getOrgs(Request.builder()
                .request().path(ServicePath.GET_Orgs).end()
                .with()
                    .paging().limit(250).offset(offset.next()).end()
                .end()
            .build());
        }
    }

    private static void examples() throws InvalidPathException {
        //Return Single Object
        Request.builder()
            .request()
                .path(ServicePath.GET_Org_By_SourcedId)
                .ids()
                    .id("A9F798CE-DA1A-3195-88CD-13AAC9416187")
                .end()
            .end()
        .build();


        //Return Collection, Single Id, With Minimal Options
        Request.builder()
            .request()
                .path(ServicePath.GET_Orgs)
            .end()
            .with()
                .paging()
                    .limit(50)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                .end()
                .fieldSelection()
                    .field(Field.sourcedId)
                .end()
                .filtering()
                    .filter(Field.status, Predicate.Equals, "active")
                .end()
            .end()
        .build();


        //Return Collection, Multiple Ids, All With Options
        Request.builder()
            .request()
                .path(ServicePath.GET_Teachers_By_Class_SourcedId_With_School_SourcedId)
                .ids()
                    .id("A9F798CE-DA1A-3195-88CD-13AAC9416187")
                    .id("09C40E77-9A2C-440F-9415-C992E9C7ABB0")
                .end()
            .end()
            .with()
                .paging()
                    .limit(1)
                    .offset(0)
                .end()
                .sorting()
                    .field(Field.sourcedId)
                    .orderBy(SortOrder.ASC)
                .end()
                .fieldSelection()
                    .field(Field.sourcedId)
                    .field(Field.dateLastModified)
                    .field(Field.Users.givenName)
                    .field(Field.Users.familyName)
                .end()
                .filtering()
                    .filter(Field.status, Predicate.Equals, "active")
                    .and()
                    .filter(Field.Users.email, Predicate.Equals, "smith")
                .end()
            .end()
        .build();
    }
}
