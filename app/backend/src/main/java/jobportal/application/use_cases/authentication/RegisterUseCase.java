package jobportal.application.use_cases.authentication;

import jakarta.mail.MessagingException;
import jobportal.application.dto.authentication.RegisterRequest;
import jobportal.application.dto.user.UserResponse;
import jobportal.application.mapper.UserMapper;
import jobportal.application.use_cases.profiles.CreateUserProfileUseCase;
import jobportal.application.use_cases.users.GetUserByEmailUseCase;
import jobportal.application.use_cases.vertification_otp.SendVerifyOtpCodeUseCase;
import jobportal.domain.entity.User;
import jobportal.domain.entity.UserType;
import jobportal.domain.enums.UserTypeEnum;
import jobportal.domain.exception.common.DuplicateResourceException;
import jobportal.domain.exception.common.authentication.AccountDisabledException;
import jobportal.domain.exception.common.authentication.AdminRegistrationNotAllowedException;
import jobportal.domain.exception.common.authentication.PasswordMismatchException;
import jobportal.domain.exception.common.authentication.RegisterFailedException;
import jobportal.domain.repository.UserRepository;
import jobportal.domain.repository.UserTypeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUseCase {

    private final UserRepository userRepository;
    private final GetUserByEmailUseCase getUserByEmail;
    private final UserTypeRepository userTypeRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SendVerifyOtpCodeUseCase sendVerifyOtpCode;
    private final CreateUserProfileUseCase createUserProfile;

    public RegisterUseCase(UserRepository userRepository, GetUserByEmailUseCase getUserByEmail, UserTypeRepository userTypeRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, SendVerifyOtpCodeUseCase sendVerifyOtpCode, CreateUserProfileUseCase createUserProfile) {
        this.userRepository = userRepository;
        this.getUserByEmail = getUserByEmail;
        this.userTypeRepository = userTypeRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.sendVerifyOtpCode = sendVerifyOtpCode;
        this.createUserProfile = createUserProfile;
    }

    @Transactional
    public void execute(RegisterRequest registerRequest) throws MessagingException {
        // Checking input constraints and business logic
        validateRegisterEligibility(registerRequest);

        // Mapping
        UserType userType = userTypeRepository.findByUserTypeEnum(registerRequest.getUserTypeEnum());
        User user = userMapper.toEntity(registerRequest);

        // Hash password
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );

        // Checking is first user, set ADMIN
        UserType userTypeAdmin = userTypeRepository.findByUserTypeEnum(UserTypeEnum.ADMIN);
        if (!userRepository.isAdminExist(userTypeAdmin)) {
            user.setUserType(userTypeAdmin);
        } else {
            user.setUserType(userType);
        }

        // Save user to db
        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            throw new RegisterFailedException("Register failed.");
        }

        // Create profile
        createUserProfile.execute(savedUser);

        // Send Verification Otp
        sendVerifyOtpCode.execute(user);

    }

    // ================================== HELPER METHOD =================================
    private void validateRegisterEligibility(RegisterRequest registerRequest) {
        // Checking confirm password and password
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new PasswordMismatchException("Password unmatches with confirm password.");
        }

        // Checking User Type Request is valid? (Type ADMIN: INVALID)
        if ("ADMIN".equals(
                registerRequest.getUserTypeEnum().toString()
        ))  {
            throw new AdminRegistrationNotAllowedException("Admin is not allowed.");
        }

        // Checking duplicate email
        String emailRequest = registerRequest.getEmail();
        if (userRepository.isExistsEmail(emailRequest)) {

            UserResponse user = getUserByEmail.execute(registerRequest.getEmail());
            if (user.isEnabled()) {
                throw new DuplicateResourceException(
                        "Email already exists."
                );
            }

            throw new AccountDisabledException(
                    "Account not be verified."
            );
        }
    }

}
