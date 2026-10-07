package jobportal.application.use_cases.profiles;

import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.application.dto.profiles.ProfileResponse;
import jobportal.application.mapper.AdminProfileMapper;
import jobportal.application.use_cases.users.GetMyAccountUseCase;
import jobportal.domain.entity.User;
import jobportal.domain.repository.AdminProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class GetAdminProfileUseCase {

    private final GetMyAccountUseCase getMyAccountUseCase;
    private final AdminProfileMapper adminProfileMapper;
    private final AdminProfileRepository adminProfileRepository;

    public GetAdminProfileUseCase(GetMyAccountUseCase getMyAccountUseCase, AdminProfileMapper adminProfileMapper, AdminProfileRepository adminProfileRepository) {
        this.getMyAccountUseCase = getMyAccountUseCase;
        this.adminProfileMapper = adminProfileMapper;
        this.adminProfileRepository = adminProfileRepository;
    }

    public ProfileResponse execute() {
        User currentUser = getMyAccountUseCase.execute();

        return adminProfileMapper.toResponse(
                adminProfileRepository.findByUser(currentUser)
        );
    }
}
