package jobportal.application.use_cases.profiles;

import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.application.mapper.JobSeekerProfileMapper;
import jobportal.application.use_cases.users.GetMyAccountUseCase;
import jobportal.domain.entity.User;
import jobportal.domain.repository.JobSeekerProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class GetJobSeekerProfileUseCase {

    private final GetMyAccountUseCase getMyAccountUseCase;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final JobSeekerProfileMapper jobSeekerProfileMapper;

    public GetJobSeekerProfileUseCase(GetMyAccountUseCase getMyAccountUseCase, JobSeekerProfileRepository jobSeekerProfileRepository, JobSeekerProfileMapper jobSeekerProfileMapper) {
        this.getMyAccountUseCase = getMyAccountUseCase;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.jobSeekerProfileMapper = jobSeekerProfileMapper;
    }

    public JobSeekerProfileResponse execute() {
        User currentUser = getMyAccountUseCase.execute();

        return jobSeekerProfileMapper.toResponse(
                jobSeekerProfileRepository
                        .findByUser(currentUser)
        );
    }
}
