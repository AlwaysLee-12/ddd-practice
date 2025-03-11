package hello.ddd.domain.survey.service;

import javax.naming.NoPermissionException;

//외부 시스템 연동이 필요한 도메인 로직이 있다면 도메인 서비스로 구현
public class CreateSurveyService {

    private SurveyPermissionChecker permissionChecker;

    public Long createSurvey(CreateServeyRequest request) throws NoPermissionException {
        validate(request);

        if (!permissionChecker.hasUserCreationPermission(request.getRequestorId())) {
            throw new NoPermissionException();
        }
    }
}
