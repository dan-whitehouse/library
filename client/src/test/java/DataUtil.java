import org.ricone.library.client.oneroster.request.Field;
import org.ricone.library.client.oneroster.request.IField;
import org.ricone.library.client.oneroster.response.model.*;
import org.ricone.library.client.oneroster.response.model.Class;

public class DataUtil {

    public static String getFieldValue(IField field, Org instance) {
        String value = "NO_VALUE";
        if(field.equals(Field.sourcedId)) {
            value = instance.getSourcedId();
        }
        else if(field.equals(Field.dateLastModified)) {
            if(instance.getDateLastModified() != null) {
                value = instance.getDateLastModified().toString();
            }
        }
        else if(field.equals(Field.status)) {
            if(instance.getStatus() != null) {
                value = instance.getStatus().getValue();
            }
        }
        else if(field.equals(Field.Orgs.name)) {
            value = instance.getName();
        }
        else if(field.equals(Field.Orgs.type)) {
            if(instance.getType() != null) {
                value = instance.getType().getValue();
            }
        }
        else if(field.equals(Field.Orgs.identifier)) {
            value = instance.getIdentifier();
        }
        else if(field.equals(Field.Orgs.Parent.sourcedId)) {
            if(instance.getParent() != null) {
                value = instance.getParent().getSourcedId();
            }
        }
        else if(field.equals(Field.Orgs.Parent.type)) {
            if(instance.getParent() != null) {
                value = instance.getParent().getType().getValue();
            }
        }
        else if(field.equals(Field.Orgs.Parent.href)) {
            if(instance.getParent() != null) {
                value = instance.getParent().getHref();
            }
        }
        else if(field.equals(Field.Orgs.Children.sourcedId)) {
            if(!instance.getChildren().isEmpty()) {
                value = instance.getChildren().get(0).getSourcedId();
            }
        }
        else if(field.equals(Field.Orgs.Children.type)) {
            if(!instance.getChildren().isEmpty()) {
                value = instance.getChildren().get(0).getType().getValue();
            }
        }
        else if(field.equals(Field.Orgs.Children.href)) {
            if(!instance.getChildren().isEmpty()) {
                value = instance.getChildren().get(0).getHref();
            }
        }
        return value;
    }

    public static String getFieldValue(IField field, AcademicSession instance) {
        String value = "NO_VALUE";
        if(field.equals(Field.sourcedId)) {
            value = instance.getSourcedId();
        }
        else if(field.equals(Field.dateLastModified)) {
            if(instance.getDateLastModified() != null) {
                value = instance.getDateLastModified().toString();
            }
        }
        else if(field.equals(Field.status)) {
            if(instance.getStatus() != null) {
                value = instance.getStatus().getValue();
            }
        }
        else if(field.equals(Field.AcademicSessions.title)) {
            value = instance.getTitle();
        }
        else if(field.equals(Field.AcademicSessions.startDate)) {
            if(instance.getStartDate() != null) {
                value = instance.getStartDate().toString();
            }
        }
        else if(field.equals(Field.AcademicSessions.endDate)) {
            if(instance.getEndDate() != null) {
                value = instance.getEndDate().toString();
            }
        }
        else if(field.equals(Field.AcademicSessions.Parent.sourcedId)) {
            if(instance.getParent() != null) {
                value = instance.getParent().getSourcedId();
            }
        }
        else if(field.equals(Field.AcademicSessions.Parent.type)) {
            if(instance.getParent() != null) {
                value = instance.getParent().getType().getValue();
            }
        }
        else if(field.equals(Field.AcademicSessions.Parent.href)) {
            if(instance.getParent() != null) {
                value = instance.getParent().getHref();
            }
        }
        else if(field.equals(Field.AcademicSessions.Children.sourcedId)) {
            if(!instance.getChildren().isEmpty()) {
                value = instance.getChildren().get(0).getSourcedId();
            }
        }
        else if(field.equals(Field.AcademicSessions.Children.type)) {
            if(!instance.getChildren().isEmpty()) {
                value = instance.getChildren().get(0).getType().getValue();
            }
        }
        else if(field.equals(Field.AcademicSessions.Children.href)) {
            if(!instance.getChildren().isEmpty()) {
                value = instance.getChildren().get(0).getHref();
            }
        }
        //TODO: Does school year exist in spec?
        return value;
    }

    public static String getFieldValue(IField field, Course instance) {
        String value = "NO_VALUE";
        if(field.equals(Field.sourcedId)) {
            value = instance.getSourcedId();
        }
        else if(field.equals(Field.dateLastModified)) {
            if(instance.getDateLastModified() != null) {
                value = instance.getDateLastModified().toString();
            }
        }
        else if(field.equals(Field.status)) {
            if(instance.getStatus() != null) {
                value = instance.getStatus().getValue();
            }
        }
        else if(field.equals(Field.Courses.title)) {
            value = instance.getTitle();
        }
        else if(field.equals(Field.Courses.SchoolYear.sourcedId)) {
            if(instance.getSchoolYear() != null) {
                value = instance.getSchoolYear().getSourcedId();
            }
        }
        else if(field.equals(Field.Courses.SchoolYear.type)) {
            if(instance.getSchoolYear() != null) {
                value = instance.getSchoolYear().getType().getValue();
            }
        }
        else if(field.equals(Field.Courses.SchoolYear.href)) {
            if(instance.getSchoolYear() != null) {
                value = instance.getSchoolYear().getHref();
            }
        }
        else if(field.equals(Field.Courses.courseCode)) {
            value = instance.getCourseCode();
        }
        else if(field.equals(Field.Courses.grades)) {
            if(!instance.getGrades().isEmpty()) {
                value = instance.getGrades().get(0);
            }
        }
        else if(field.equals(Field.Courses.subjects)) {
            if(!instance.getSubjects().isEmpty()) {
                value = instance.getSubjects().get(0);
            }
        }
        else if(field.equals(Field.Courses.Org.sourcedId)) {
            if(instance.getOrg() != null) {
                value = instance.getOrg().getSourcedId();
            }
        }
        else if(field.equals(Field.Courses.Org.type)) {
            if(instance.getOrg() != null) {
                value = instance.getOrg().getType().getValue();
            }
        }
        else if(field.equals(Field.Courses.Org.href)) {
            if(instance.getOrg() != null) {
                value = instance.getOrg().getHref();
            }
        }
        else if(field.equals(Field.Courses.subjectCodes)) {
            if(!instance.getSubjectCodes().isEmpty()) {
                value = instance.getSubjectCodes().get(0);
            }
        }

        //TODO: Does school year exist in spec?
        return value;
    }

    public static String getFieldValue(IField field, Class instance) {
        String value = "NO_VALUE";
        if(field.equals(Field.sourcedId)) {
            value = instance.getSourcedId();
        }
        else if(field.equals(Field.dateLastModified)) {
            if(instance.getDateLastModified() != null) {
                value = instance.getDateLastModified().toString();
            }
        }
        else if(field.equals(Field.status)) {
            if(instance.getStatus() != null) {
                value = instance.getStatus().getValue();
            }
        }
        else if(field.equals(Field.Classes.title)) {
            value = instance.getTitle();
        }
        else if(field.equals(Field.Classes.classCode)) {
            value = instance.getClassCode();
        }
        else if(field.equals(Field.Classes.classType)) {
            if(instance.getClassType() != null) {
                value = instance.getClassType().getValue();
            }
        }
        else if(field.equals(Field.Classes.location)) {
            value = instance.getLocation();
        }
        else if(field.equals(Field.Classes.grades)) {
            if(!instance.getGrades().isEmpty()) {
                value = instance.getGrades().get(0);
            }
        }
        else if(field.equals(Field.Classes.subjects)) {
            if(!instance.getSubjects().isEmpty()) {
                value = instance.getSubjects().get(0);
            }
        }
        else if(field.equals(Field.Classes.Terms.sourcedId)) {
            if(!instance.getTerms().isEmpty()) {
                value = instance.getTerms().get(0).getSourcedId();
            }
        }
        else if(field.equals(Field.Classes.Terms.type)) {
            if(!instance.getTerms().isEmpty()) {
                value = instance.getTerms().get(0).getType().getValue();
            }
        }
        else if(field.equals(Field.Classes.Terms.href)) {
            if(!instance.getTerms().isEmpty()) {
                value = instance.getTerms().get(0).getHref();
            }
        }
        else if(field.equals(Field.Classes.subjectCodes)) {
            if(!instance.getSubjectCodes().isEmpty()) {
                value = instance.getSubjectCodes().get(0);
            }
        }
        else if(field.equals(Field.Classes.periods)) {
            if(!instance.getPeriods().isEmpty()) {
                value = instance.getPeriods().get(0);
            }
        }
        return value;
    }

    public static String getFieldValue(IField field, Enrollment instance) {
        String value = "NO_VALUE";
        if(field.equals(Field.sourcedId)) {
            value = instance.getSourcedId();
        }
        else if(field.equals(Field.dateLastModified)) {
            if(instance.getDateLastModified() != null) {
                value = instance.getDateLastModified().toString();
            }
        }
        else if(field.equals(Field.status)) {
            if(instance.getStatus() != null) {
                value = instance.getStatus().getValue();
            }
        }
        else if(field.equals(Field.Enrollments.role)) {
            if(instance.getRole() != null) {
                value = instance.getRole().getValue();
            }
        }
        else if(field.equals(Field.Enrollments.primary)) {
            if(instance.getPrimary() != null) {
                value = instance.getPrimary().toString();
            }
        }
        else if(field.equals(Field.Enrollments.User.sourcedId)) {
            if(instance.getUser() != null) {
                value = instance.getUser().getSourcedId();
            }
        }
        else if(field.equals(Field.Enrollments.User.type)) {
            if(instance.getUser() != null) {
                value = instance.getUser().getType().getValue();
            }
        }
        else if(field.equals(Field.Enrollments.User.href)) {
            if(instance.getUser() != null) {
                value = instance.getUser().getHref();
            }
        }
        else if(field.equals(Field.Enrollments.Clazz.sourcedId)) {
            if(instance.getClass_() != null) {
                value = instance.getClass_().getSourcedId();
            }
        }
        else if(field.equals(Field.Enrollments.Clazz.type)) {
            if(instance.getClass_() != null) {
                value = instance.getClass_().getType().getValue();
            }
        }
        else if(field.equals(Field.Enrollments.Clazz.href)) {
            if(instance.getClass_() != null) {
                value = instance.getClass_().getHref();
            }
        }
        else if(field.equals(Field.Enrollments.School.sourcedId)) {
            if(instance.getSchool() != null) {
                value = instance.getSchool().getSourcedId();
            }
        }
        else if(field.equals(Field.Enrollments.School.type)) {
            if(instance.getSchool() != null) {
                value = instance.getSchool().getType().getValue();
            }
        }
        else if(field.equals(Field.Enrollments.School.href)) {
            if(instance.getSchool() != null) {
                value = instance.getSchool().getHref();
            }
        }
        else if(field.equals(Field.Enrollments.beginDate)) {
            if(instance.getBeginDate() != null) {
                value = instance.getBeginDate().toString();
            }
        }
        else if(field.equals(Field.Enrollments.endDate)) {
            if(instance.getEndDate() != null) {
                value = instance.getEndDate().toString();
            }
        }
        return value;
    }

    public static String getFieldValue(IField field, User instance) {
        String value = "NO_VALUE";
        if(field.equals(Field.sourcedId)) {
            value = instance.getSourcedId();
        }
        else if(field.equals(Field.dateLastModified)) {
            if(instance.getDateLastModified() != null) {
                value = instance.getDateLastModified().toString();
            }
        }
        else if(field.equals(Field.status)) {
            if(instance.getStatus() != null) {
                value = instance.getStatus().getValue();
            }
        }
        else if(field.equals(Field.Enrollments.role)) {
            if(instance.getRole() != null) {
                value = instance.getRole().getValue();
            }
        }
        else if(field.equals(Field.Users.username)) {
            value = instance.getUsername();
        }
        else if(field.equals(Field.Users.UserIds.type)) {
            if(!instance.getUserIds().isEmpty()) {
                value = instance.getUserIds().get(0).getType();
            }
        }
        else if(field.equals(Field.Users.UserIds.identifier)) {
            if(!instance.getUserIds().isEmpty()) {
                value = instance.getUserIds().get(0).getIdentifier();
            }
        }
        else if(field.equals(Field.Users.enabledUser)) {
            if(instance.getEnabledUser() != null) {
                value = instance.getEnabledUser().toString();
            }
        }
        else if(field.equals(Field.Users.givenName)) {
            value = instance.getGivenName();
        }
        else if(field.equals(Field.Users.familyName)) {
            value = instance.getFamilyName();
        }
        else if(field.equals(Field.Users.middleName)) {
            value = instance.getMiddleName();
        }
        else if(field.equals(Field.Users.role)) {
            if(instance.getRole() != null) {
                value = instance.getRole().getValue();
            }
        }
        else if(field.equals(Field.Users.identifier)) {
            value = instance.getIdentifier();
        }
        else if(field.equals(Field.Users.email)) {
            value = instance.getEmail();
        }
        else if(field.equals(Field.Users.sms)) {
            value = instance.getSms();
        }
        else if(field.equals(Field.Users.phone)) {
            value = instance.getPhone();
        }
        else if(field.equals(Field.Users.Agents.sourcedId)) {
            if(!instance.getAgents().isEmpty()) {
                value = instance.getAgents().get(0).getSourcedId();
            }
        }
        else if(field.equals(Field.Users.Agents.type)) {
            if(!instance.getAgents().isEmpty()) {
                value = instance.getAgents().get(0).getType().getValue();
            }
        }
        else if(field.equals(Field.Users.Agents.href)) {
            if(!instance.getAgents().isEmpty()) {
                value = instance.getAgents().get(0).getHref();
            }
        }
        else if(field.equals(Field.Users.Orgs.sourcedId)) {
            if(!instance.getOrgs().isEmpty()) {
                value = instance.getOrgs().get(0).getSourcedId();
            }
        }
        else if(field.equals(Field.Users.Orgs.type)) {
            if(!instance.getOrgs().isEmpty()) {
                value = instance.getOrgs().get(0).getType().getValue();
            }
        }
        else if(field.equals(Field.Users.Orgs.href)) {
            if(!instance.getOrgs().isEmpty()) {
                value = instance.getOrgs().get(0).getHref();
            }
        }
        else if(field.equals(Field.Users.grades)) {
            if(!instance.getGrades().isEmpty()) {
                value = instance.getGrades().get(0);
            }
        }
        else if(field.equals(Field.Users.password)) {
            value = instance.getPassword();
        }
        return value;
    }

    public static String getFieldValue(IField field, Demographic instance) {
        String value = "NO_VALUE";
        if(field.equals(Field.sourcedId)) {
            value = instance.getSourcedId();
        }
        else if(field.equals(Field.dateLastModified)) {
            if(instance.getDateLastModified() != null) {
                value = instance.getDateLastModified().toString();
            }
        }
        else if(field.equals(Field.status)) {
            if(instance.getStatus() != null) {
                value = instance.getStatus().getValue();
            }
        }
        else if(field.equals(Field.Demographics.birthDate)) {
            if(instance.getBirthDate() != null) {
                value = instance.getBirthDate().toString();
            }
        }
        else if(field.equals(Field.Demographics.sex)) {
            if(instance.getBirthDate() != null) {
                value = instance.getSex().getValue();
            }
        }
        else if(field.equals(Field.Demographics.americanIndianOrAlaskaNative)) {
            if(instance.getBirthDate() != null) {
                value = instance.getAmericanIndianOrAlaskaNative().toString();
            }
        }
        else if(field.equals(Field.Demographics.asian)) {
            if(instance.getBirthDate() != null) {
                value = instance.getAsian().toString();
            }
        }
        else if(field.equals(Field.Demographics.blackOrAfricanAmerican)) {
            if(instance.getBirthDate() != null) {
                value = instance.getBlackOrAfricanAmerican().toString();
            }
        }
        else if(field.equals(Field.Demographics.nativeHawaiianOrOtherPacificIslander)) {
            if(instance.getBirthDate() != null) {
                value = instance.getNativeHawaiianOrOtherPacificIslander().toString();
            }
        }
        else if(field.equals(Field.Demographics.white)) {
            if(instance.getBirthDate() != null) {
                value = instance.getWhite().toString();
            }
        }
        else if(field.equals(Field.Demographics.demographicRaceTwoOrMoreRaces)) {
            if(instance.getBirthDate() != null) {
                value = instance.getDemographicRaceTwoOrMoreRaces().toString();
            }
        }
        else if(field.equals(Field.Demographics.hispanicOrLatinoEthnicity)) {
            if(instance.getBirthDate() != null) {
                value = instance.getHispanicOrLatinoEthnicity().toString();
            }
        }
        else if(field.equals(Field.Demographics.countryOfBirthCode)) {
            if(instance.getBirthDate() != null) {
                value = instance.getCountryOfBirthCode();
            }
        }
        else if(field.equals(Field.Demographics.stateOfBirthAbbreviation)) {
            if(instance.getBirthDate() != null) {
                value = instance.getStateOfBirthAbbreviation();
            }
        }
        else if(field.equals(Field.Demographics.cityOfBirth)) {
            if(instance.getBirthDate() != null) {
                value = instance.getCityOfBirth();
            }
        }
        else if(field.equals(Field.Demographics.publicSchoolResidenceStatus)) {
            if(instance.getBirthDate() != null) {
                value = instance.getPublicSchoolResidenceStatus();
            }
        }
        return value;
    }
}
