package jobportal.presentation.controller;

import jobportal.application.dto.user.UserResponse;
import jobportal.application.mapper.UserMapper;
import jobportal.application.use_cases.users.GetAllUsersUseCase;
import jobportal.application.use_cases.users.GetMyAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetMyAccountUseCase getMyAccountUseCase;

    public UserController(GetAllUsersUseCase getAllUsersUseCase, GetMyAccountUseCase getMyAccountUseCase) {
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.getMyAccountUseCase = getMyAccountUseCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return getAllUsersUseCase.execute();
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getMyAccount(){
        return getMyAccountUseCase.execute();
    }
}
