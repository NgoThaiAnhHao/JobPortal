package jobportal.infrastructure.persistence.refresh_token;

import jobportal.domain.entity.RefreshToken;
import jobportal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface JpaRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByUser(User user);

    Optional<RefreshToken> findByToken(String tokenHash);

    void deleteByExpiredAtBefore(LocalDateTime now);
}
