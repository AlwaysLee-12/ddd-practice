package hello.ddd.domain.survey.service;

public interface SurveyPermissionChecker {

    boolean hasUserCreationPermission(String userId);
}
