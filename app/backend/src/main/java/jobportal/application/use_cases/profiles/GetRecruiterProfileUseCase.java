package jobportal.application.use_cases.profiles;

import jobportal.application.dto.profiles.ProfileResponse;
import jobportal.application.dto.profiles.RecruiterProfileResponse;
import jobportal.application.mapper.RecruiterProfileMapper;
import jobportal.application.use_cases.users.GetMyAccountUseCase;
import jobportal.domain.entity.User;
import jobportal.domain.repository.RecruiterProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class GetRecruiterProfileUseCase {

    private final RecruiterProfileMapper recruiterProfileMapper;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final GetMyAccountUseCase getMyAccountUseCase;

    public GetRecruiterProfileUseCase(RecruiterProfileMapper recruiterProfileMapper, RecruiterProfileRepository recruiterProfileRepository, GetMyAccountUseCase getMyAccountUseCase) {
        this.recruiterProfileMapper = recruiterProfileMapper;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.getMyAccountUseCase = getMyAccountUseCase;
    }

    public RecruiterProfileResponse execute() {
        User currentUser = getMyAccountUseCase.execute();

        return recruiterProfileMapper.toResponse(
                recruiterProfileRepository.findByUser(currentUser)
        );
    }
}
