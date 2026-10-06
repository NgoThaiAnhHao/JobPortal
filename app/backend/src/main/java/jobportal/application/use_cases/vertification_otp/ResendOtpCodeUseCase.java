package jobportal.application.use_cases.vertification_otp;

import jakarta.mail.MessagingException;
import jobportal.domain.entity.User;
import jobportal.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResendOtpCodeUseCase {

    private final UserRepository userRepository;
    private final SendVerifyOtpCodeUseCase sendVerifyOtpCodeUseCase;

    public ResendOtpCodeUseCase(UserRepository userRepository, SendVerifyOtpCodeUseCase sendVerifyOtpCodeUseCase) {
        this.userRepository = userRepository;
        this.sendVerifyOtpCodeUseCase = sendVerifyOtpCodeUseCase;
    }

    @Transactional
    public void execute(String email) throws MessagingException {
        User user = userRepository.findByEmail(email);
        sendVerifyOtpCodeUseCase.execute(user);
    }
}
