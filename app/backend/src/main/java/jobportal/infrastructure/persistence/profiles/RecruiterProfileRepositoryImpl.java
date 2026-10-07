package jobportal.infrastructure.persistence.profiles;

import jobportal.domain.entity.RecruiterProfile;
import jobportal.domain.entity.User;
import jobportal.domain.exception.common.user.ProfileNotFoundException;
import jobportal.domain.repository.RecruiterProfileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RecruiterProfileRepositoryImpl implements RecruiterProfileRepository {

    private final JpaRecruiterProfileRepository jpaRecruiterProfileRepository;

    public RecruiterProfileRepositoryImpl(JpaRecruiterProfileRepository jpaRecruiterProfileRepository) {
        this.jpaRecruiterProfileRepository = jpaRecruiterProfileRepository;
    }

    @Override
    public boolean existsByUser(User user) {
        return jpaRecruiterProfileRepository.existsByUser(user);
    }

    @Override
    public void save(RecruiterProfile recruiterProfile) {
        jpaRecruiterProfileRepository.save(recruiterProfile);
    }

    @Override
    public RecruiterProfile findByUser(User user) {
        return jpaRecruiterProfileRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new ProfileNotFoundException("Your recruiter profile not found.")
                );
    }
}
