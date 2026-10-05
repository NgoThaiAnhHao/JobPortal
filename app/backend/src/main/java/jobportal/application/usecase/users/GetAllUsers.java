package jobportal.application.usecase.users;

import jobportal.application.dto.user.UserResponse;
import jobportal.application.mapper.UserMapper;
import jobportal.domain.entity.User;
import jobportal.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsers {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public GetAllUsers(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<UserResponse> execute() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }
}
