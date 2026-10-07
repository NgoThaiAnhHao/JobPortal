package jobportal.application.use_cases.profiles;

import jobportal.domain.entity.AdminProfile;
import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.RecruiterProfile;
import jobportal.domain.entity.User;
import jobportal.domain.exception.common.user.ExistsProfileException;
import jobportal.domain.exception.common.user.InvalidUserTypeException;
import jobportal.domain.repository.AdminProfileRepository;
import jobportal.domain.repository.JobSeekerProfileRepository;
import jobportal.domain.repository.RecruiterProfileRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserProfileUseCase {

    private final AdminProfileRepository adminProfileRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    public CreateUserProfileUseCase(AdminProfileRepository adminProfileRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository) {
        this.adminProfileRepository = adminProfileRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    @Transactional
    public void execute(User user) {
        // Get user type
        String userTypeName = user.getUserType().getUserTypeEnum().toString();

        // Authorization based on user type
        switch (userTypeName) {

            // Check role is jobseeker => create jobseeker profile
            case "JOB_SEEKER" -> {
                // Check exists profile
                if (jobSeekerProfileRepository.existsByUser(user)) {
                    throw new ExistsProfileException("This job seeker profile already exists.");
                }

                // Create profile
                JobSeekerProfile jobSeekerProfile = new JobSeekerProfile();
                jobSeekerProfile.setUser(user);
                jobSeekerProfileRepository.save(jobSeekerProfile);
            }

            // Check role is recruiter => create recruiter profile
            case "RECRUITER" -> {
                // Check exists profile
                if (recruiterProfileRepository.existsByUser(user)) {
                    throw new ExistsProfileException("This recruiter profile already exists.");
                }

                // Create profile
                RecruiterProfile recruiterProfile = new RecruiterProfile();
                recruiterProfile.setUser(user);
                recruiterProfileRepository.save(recruiterProfile);
            }

            // Check role is admin => create admin profile
            case "ADMIN" -> {
                // Check exists profile
                if (adminProfileRepository.existsByUser(user)) {
                    throw new ExistsProfileException("This admin profile already exists.");
                }

                // Create profile
                AdminProfile adminProfile = new AdminProfile();
                adminProfile.setUser(user);
                adminProfileRepository.save(adminProfile);
            }

            default ->
                    throw new InvalidUserTypeException(
                            String.format("Unsupported for user type: %s", userTypeName)
                    );
        }
    }
}
