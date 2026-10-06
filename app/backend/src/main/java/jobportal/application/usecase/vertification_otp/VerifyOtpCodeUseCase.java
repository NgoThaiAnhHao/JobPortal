package jobportal.application.usecase.vertification_otp;

import jobportal.domain.entity.User;
import jobportal.domain.entity.VerificationOtp;
import jobportal.domain.repository.UserRepository;
import jobportal.domain.repository.VerificationOtpRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class VerifyOtpCodeUseCase {

    private final UserRepository userRepository;
    private final VerificationOtpRepository verificationOtpRepository;
    private final PasswordEncoder passwordEncoder;

    public VerifyOtpCodeUseCase(UserRepository userRepository, VerificationOtpRepository verificationOtpRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationOtpRepository = verificationOtpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void execute(String inputOtpCode, String email) {
        // Find user
        User user = userRepository.findByEmail(email);

        // Check user is verified
        if (user.isEnabled()) {
            throw new RuntimeException("This account was be verified.");
        }

        // Get token to compare
        VerificationOtp verificationOtp = verificationOtpRepository
                .findByUser(user);

        // Check expired
        if (verificationOtp.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP code is expired.");
        }

        // Check otp
        if (!passwordEncoder.matches(inputOtpCode, verificationOtp.getOtpCode())) {
            throw new RuntimeException("Invalid OTP code.");
        }

        // Set enable and save user to db
        user.setEnabled(true);
        userRepository.save(user);
        user.setEmailVerifiedAt(LocalDateTime.now());

        // If save user success, delete this otp code
        verificationOtpRepository.deleteByUser(user);
    }
}
