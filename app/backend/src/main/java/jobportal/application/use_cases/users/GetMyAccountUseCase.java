package jobportal.application.use_cases.users;

import jobportal.application.dto.user.UserResponse;
import jobportal.application.mapper.UserMapper;
import jobportal.domain.exception.common.authentication.UserNotAuthenticatedException;
import jobportal.domain.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetMyAccountUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public GetMyAccountUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse execute() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException("User is not authenticated.");
        }

        return userMapper.toResponse(
            userRepository.findByEmail(authentication.getName())
        );
    }
}
