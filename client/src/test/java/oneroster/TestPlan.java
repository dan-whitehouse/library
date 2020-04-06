package oneroster;

import org.ricone.library.authentication.API;
import org.ricone.library.authentication.Authenticator;
import org.ricone.library.authentication.Endpoint;
import org.ricone.library.client.oneroster.request.OneRoster;
import org.ricone.library.exception.InvalidPathException;

import java.util.Optional;

public class TestPlan {

    public static void main(String[] args) throws InvalidPathException {
        Authenticator authenticator = Authenticator.builder()
                .url(System.getenv("url")).api(API.OneRoster)
                .credentials(System.getenv("username"), System.getenv("password"))
                .provider(System.getenv("provider"))
                .authenticate();

        Optional<Endpoint> endpoint = authenticator.getEndpoint("localhost");
        if(endpoint.isPresent()) {
            OneRoster oneRoster = new OneRoster(endpoint.get());

            //Orgs && Schools
            TestPlanOrgs testPlanOrgs = new TestPlanOrgs(true,true,true,false);
            //testPlanOrgs.run(oneRoster);

            //Academic Sessions && Terms
            TestPlanAcademicSessions testPlanAcademicSessions = new TestPlanAcademicSessions(true,true,true,false);
            //testPlanAcademicSessions.run(oneRoster);

            //Courses
            TestPlanCourses testPlanCourses = new TestPlanCourses(true,true,true,false);
            //testPlanCourses.run(oneRoster);

            //Courses
            TestPlanClasses testPlanClasses = new TestPlanClasses(true,true,true,false);
            testPlanClasses.run(oneRoster);

            //Results of Each
            System.out.println("-----------------------------------------------");
            //testPlanOrgs.results();
            //testPlanAcademicSessions.results();
            //testPlanCourses.results();
            testPlanClasses.results();
        }
    }
}
