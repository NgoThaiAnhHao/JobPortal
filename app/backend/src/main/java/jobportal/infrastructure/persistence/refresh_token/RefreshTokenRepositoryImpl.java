package jobportal.infrastructure.persistence.refresh_token;

import jobportal.application.utils.TokenHashUtils;
import jobportal.domain.entity.RefreshToken;
import jobportal.domain.entity.User;
import jobportal.domain.exception.common.ResourceNotFoundException;
import jobportal.domain.repository.RefreshTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;

    public RefreshTokenRepositoryImpl(JpaRefreshTokenRepository jpaRefreshTokenRepository) {
        this.jpaRefreshTokenRepository = jpaRefreshTokenRepository;
    }

    @Override
    public void deleteByUser(User user) {
        jpaRefreshTokenRepository.deleteByUser(user);
        jpaRefreshTokenRepository.flush();
    }

    @Override
    public RefreshToken findByTokenHash(String tokenHash) {
        return jpaRefreshTokenRepository
                .findByToken(tokenHash)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Token is not exists.")
                );
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        jpaRefreshTokenRepository.delete(refreshToken);
        jpaRefreshTokenRepository.flush();
    }

    @Override
    public String generateRefreshToken(User user) {
        String rawToken = UUID.randomUUID().toString();
        String tokenHash = TokenHashUtils.hashToken(rawToken);

        jpaRefreshTokenRepository.save(
                new RefreshToken(
                        tokenHash,
                        user
                )
        );

        return rawToken;
    }

    @Transactional
    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.MINUTES)
    public void deleteExpiredToken() {
        jpaRefreshTokenRepository.deleteByExpiredAtBefore(LocalDateTime.now());
    }
}
