package jobportal.application.use_cases.vertification_otp;

import jakarta.mail.MessagingException;
import jobportal.application.services.MailService;
import jobportal.domain.entity.User;
import jobportal.domain.entity.VerificationOtp;
import jobportal.domain.repository.VerificationOtpRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class SendVerifyOtpCodeUseCase {

    @Value("${spring.mode}")
    private String SPRING_MODE;

    private final MailService mailService;
    private final VerificationOtpRepository verificationOtpRepository;
    private final PasswordEncoder passwordEncoder;

    public SendVerifyOtpCodeUseCase(MailService mailService, VerificationOtpRepository verificationOtpRepository, PasswordEncoder passwordEncoder) {
        this.mailService = mailService;
        this.verificationOtpRepository = verificationOtpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void execute(User user) throws MessagingException {
        // Find otp exists and delete
        verificationOtpRepository.deleteByUser(user);

        // Generating new otp code
        String otpCode;
        if (SPRING_MODE.equals("DEV")) {
            otpCode = "123456";
            System.out.printf("OTP CODE FOR EMAIL '%s': %s.\n", user.getEmail(), otpCode);
        } else {
            otpCode = getRandomOtp();
        }

        // Send to user mail
        mailService.sendVerificationToken(user.getEmail(), otpCode);

        // Create object and Save to database
        verificationOtpRepository.save(
                new VerificationOtp(passwordEncoder.encode(otpCode), user)
        );
    }

    private String getRandomOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1_000_000));
    }
}
