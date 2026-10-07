package jobportal.infrastructure.persistence.profiles;

import jobportal.domain.entity.RecruiterProfile;
import jobportal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRecruiterProfileRepository extends JpaRepository<RecruiterProfile, Long> {
    boolean existsByUser(User user);

    Optional<RecruiterProfile> findByUser(User user);
}
