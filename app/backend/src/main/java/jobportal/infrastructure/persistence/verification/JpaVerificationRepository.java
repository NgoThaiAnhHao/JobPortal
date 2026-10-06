package jobportal.infrastructure.persistence.verification;

import jobportal.domain.entity.User;
import jobportal.domain.entity.VerificationOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface JpaVerificationRepository extends JpaRepository<VerificationOtp, Long> {
    void deleteByUser(User user);

    Optional<VerificationOtp> findByUser(User user);

    void deleteByExpiredAtBefore(LocalDateTime now);
}
