package jobportal.application.usecase.users;

import jobportal.application.dto.user.UserResponse;
import jobportal.application.mapper.UserMapper;
import jobportal.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserByEmail {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public GetUserByEmail(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse execute(String email) {
        return userMapper.toResponse(
                userRepository.findByEmail(email)
        );
    }
}
