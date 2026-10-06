package jobportal.presentation.controller;

import jakarta.validation.Valid;
import jobportal.application.dto.authentication.LoginRequest;
import jobportal.application.dto.authentication.LoginResponse;
import jobportal.application.usecase.authentication.LoginUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final LoginUseCase loginUseCase;

    public AuthenticationController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid  @RequestBody LoginRequest loginRequest) {
        return loginUseCase.execute(loginRequest);
    }
}
