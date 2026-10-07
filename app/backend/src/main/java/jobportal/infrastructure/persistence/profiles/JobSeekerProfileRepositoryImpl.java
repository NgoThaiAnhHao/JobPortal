package jobportal.infrastructure.persistence.profiles;

import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.User;
import jobportal.domain.exception.common.user.ProfileNotFoundException;
import jobportal.domain.repository.JobSeekerProfileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JobSeekerProfileRepositoryImpl implements JobSeekerProfileRepository {

    private final JpaJobSeekerProfileRepository jpaJobSeekerProfileRepository;

    public JobSeekerProfileRepositoryImpl(JpaJobSeekerProfileRepository jpaJobSeekerProfileRepository) {
        this.jpaJobSeekerProfileRepository = jpaJobSeekerProfileRepository;
    }

    @Override
    public boolean existsByUser(User user) {
        return jpaJobSeekerProfileRepository.existsByUser(user);
    }

    @Override
    public void save(JobSeekerProfile jobSeekerProfile) {
        jpaJobSeekerProfileRepository.save(jobSeekerProfile);
    }

    @Override
    public JobSeekerProfile findByUser(User user) {
        return jpaJobSeekerProfileRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new ProfileNotFoundException("Your job seeker profile not found.")
                );
    }
}
