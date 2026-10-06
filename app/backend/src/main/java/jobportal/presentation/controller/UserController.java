package jobportal.presentation.controller;

import jobportal.application.dto.user.UserResponse;
import jobportal.application.use_cases.users.GetAllUsersUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetAllUsersUseCase getAllUsersUseCase;

    public UserController(GetAllUsersUseCase getAllUsersUseCase) {
        this.getAllUsersUseCase = getAllUsersUseCase;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return getAllUsersUseCase.execute();
    }
}
