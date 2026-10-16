package jobportal.application.use_cases.profiles.jobseeker;

import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.application.dto.profiles.UpdateJobSeekerProfileRequest;
import jobportal.application.mapper.JobSeekerProfileMapper;
import jobportal.application.use_cases.users.GetMyAccountUseCase;
import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.User;
import jobportal.domain.repository.JobSeekerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class UpdateJobSeekerProfileUseCase {

    private final GetMyAccountUseCase getMyAccountUseCase;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final JobSeekerProfileMapper jobSeekerProfileMapper;

    public UpdateJobSeekerProfileUseCase(GetMyAccountUseCase getMyAccountUseCase, JobSeekerProfileRepository jobSeekerProfileRepository, JobSeekerProfileMapper jobSeekerProfileMapper) {
        this.getMyAccountUseCase = getMyAccountUseCase;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.jobSeekerProfileMapper = jobSeekerProfileMapper;
    }

    @Transactional
    public JobSeekerProfileResponse execute(UpdateJobSeekerProfileRequest updateRequest) {
        // Get my account
        User currentUser = getMyAccountUseCase.execute();

        // Get my profile
        JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findByUser(currentUser);

        // Update first name and last name
        jobSeekerProfile.setFirstName(updateRequest.getFirstName());
        jobSeekerProfile.setLastName(updateRequest.getLastName());

        // Phone
        if (updateRequest.getPhone() != null) {
            jobSeekerProfile.setPhone(updateRequest.getPhone());
        }

        // City
        if (updateRequest.getCity() != null) {
            jobSeekerProfile.setCity(updateRequest.getCity());
        }

        // Country
        if (updateRequest.getCountry() != null) {
            jobSeekerProfile.setCountry(updateRequest.getCountry());
        }

        // Employment type
        if (updateRequest.getEmploymentType() != null) {
            jobSeekerProfile.setEmploymentType(updateRequest.getEmploymentType());
        }

        // Save to db
        return jobSeekerProfileMapper.toResponse(
                jobSeekerProfileRepository.save(jobSeekerProfile)
        );
    }


}
