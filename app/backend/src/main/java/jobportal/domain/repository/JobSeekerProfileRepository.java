package jobportal.domain.repository;

import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.User;

public interface JobSeekerProfileRepository {
    boolean existsByUser(User user);

    JobSeekerProfile save(JobSeekerProfile jobSeekerProfile);

    JobSeekerProfile findByUser(User user);
}
