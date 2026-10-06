package jobportal.presentation.controller;

import jobportal.application.dto.user.UserResponse;
import jobportal.application.usecase.users.GetAllUsers;
import jobportal.domain.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetAllUsers getAllUsers;

    public UserController(GetAllUsers getAllUsers) {
        this.getAllUsers = getAllUsers;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return getAllUsers.execute();
    }
}
