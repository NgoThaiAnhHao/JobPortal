package jobportal.infrastructure.persistence.profiles;

import jobportal.domain.entity.AdminProfile;
import jobportal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAdminProfileRepository extends JpaRepository<AdminProfile, Long> {
    boolean existsByUser(User user);

    Optional<AdminProfile> findByUser(User user);
}
