package jobportal.infrastructure.persistence.profiles;

import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaJobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Long> {
    boolean existsByUser(User user);

    Optional<JobSeekerProfile> findByUser(User user);
}
