package jobportal.presentation.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jobportal.application.dto.authentication.LoginRequest;
import jobportal.application.dto.authentication.LoginResponse;
import jobportal.application.dto.authentication.RefreshTokenRequest;
import jobportal.application.dto.authentication.RegisterRequest;
import jobportal.application.dto.verification_otp.EmailRequest;
import jobportal.application.dto.verification_otp.VerifyOtpRequest;
import jobportal.application.use_cases.authentication.LoginUseCase;
import jobportal.application.use_cases.authentication.LogoutUseCase;
import jobportal.application.use_cases.refresh_token.RefreshAnAccessTokenUseCase;
import jobportal.application.use_cases.authentication.RegisterUseCase;
import jobportal.application.use_cases.vertification_otp.ResendOtpCodeUseCase;
import jobportal.application.use_cases.vertification_otp.VerifyOtpCodeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final VerifyOtpCodeUseCase verifyOtpCodeUseCase;
    private final ResendOtpCodeUseCase resendOtpCodeUseCase;
    private final RefreshAnAccessTokenUseCase refreshAnAccessTokenUseCase;
    private final LogoutUseCase logoutUseCase;

    public AuthenticationController(LoginUseCase loginUseCase, RegisterUseCase registerUseCase, VerifyOtpCodeUseCase verifyOtpCodeUseCase, ResendOtpCodeUseCase resendOtpCodeUseCase, RefreshAnAccessTokenUseCase refreshAnAccessTokenUseCase, LogoutUseCase logoutUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
        this.verifyOtpCodeUseCase = verifyOtpCodeUseCase;
        this.resendOtpCodeUseCase = resendOtpCodeUseCase;
        this.refreshAnAccessTokenUseCase = refreshAnAccessTokenUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginUseCase.execute(loginRequest);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequest registerRequest) throws MessagingException {
        registerUseCase.execute(registerRequest);
    }

    @PostMapping("/verify-otp")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void verifyOtp(
            @Valid @RequestBody VerifyOtpRequest verifyOtpRequest) {
        verifyOtpCodeUseCase.execute(verifyOtpRequest.getOtpCode(), verifyOtpRequest.getEmail());
    }

    @PostMapping("/resend-otp")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resendOtpCode(
            @Valid @RequestBody EmailRequest emailRequest) throws MessagingException {
        resendOtpCodeUseCase.execute(emailRequest.getEmail());
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse refreshAnAccessToken(
            @Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {

        return refreshAnAccessTokenUseCase.execute(refreshTokenRequest);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(
            @Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {

        logoutUseCase.execute(refreshTokenRequest);
    }

}
