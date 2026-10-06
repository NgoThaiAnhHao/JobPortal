package jobportal.domain.repository;

import jobportal.domain.entity.User;
import jobportal.domain.entity.VerificationOtp;

public interface VerificationOtpRepository {

    void deleteByUser(User user);

    void save(VerificationOtp verificationOtp);

    VerificationOtp findByUser(User user);
}
