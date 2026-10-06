package jobportal.infrastructure.persistence.verification;

import jobportal.domain.entity.User;
import jobportal.domain.entity.VerificationOtp;
import jobportal.domain.exception.common.authentication.UserNotFoundException;
import jobportal.domain.repository.VerificationOtpRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Repository
public class VerificationRepositoryImpl implements VerificationOtpRepository {

    private final JpaVerificationRepository jpaVerificationRepository;

    public VerificationRepositoryImpl(JpaVerificationRepository jpaVerificationRepository) {
        this.jpaVerificationRepository = jpaVerificationRepository;
    }

    @Override
    public void deleteByUser(User user) {
        jpaVerificationRepository.deleteByUser(user);
        jpaVerificationRepository.flush();
    }

    @Override
    public void save(VerificationOtp verificationOtp) {
        jpaVerificationRepository.save(verificationOtp);
    }

    @Override
    public VerificationOtp findByUser(User user) {
        return jpaVerificationRepository
                .findByUser(user)
                .orElseThrow(() ->
                    new UserNotFoundException("User not found.")
                );
    }

    @Transactional
    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.MINUTES)
    public void deleteExpiredOtp() {
        jpaVerificationRepository.deleteByExpiredAtBefore(LocalDateTime.now());
    }
}
