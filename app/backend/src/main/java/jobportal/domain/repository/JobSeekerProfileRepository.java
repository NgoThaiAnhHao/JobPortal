package jobportal.domain.repository;

import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.User;

public interface JobSeekerProfileRepository {
    boolean existsByUser(User user);

    void save(JobSeekerProfile jobSeekerProfile);

    JobSeekerProfile findByUser(User user);
}
