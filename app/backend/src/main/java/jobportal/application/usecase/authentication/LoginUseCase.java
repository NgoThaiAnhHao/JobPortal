package jobportal.application.usecase.authentication;

import jobportal.application.dto.authentication.LoginRequest;
import jobportal.application.dto.authentication.LoginResponse;
import jobportal.application.dto.user.UserResponse;
import jobportal.application.usecase.users.GetUserByEmail;
import jobportal.domain.entity.User;
import jobportal.infrastructure.security.CustomUserDetails;
import jobportal.infrastructure.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final GetUserByEmail getUserByEmail;

    public LoginUseCase(AuthenticationManager authenticationManager, JwtUtil jwtUtil, GetUserByEmail getUserByEmail) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.getUserByEmail = getUserByEmail;
    }

    public LoginResponse execute(LoginRequest loginRequest) {
        System.out.println("EMAIL = " + loginRequest.getEmail());
        System.out.println("PASSWORD = " + loginRequest.getPassword());

        // Authenticate
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Get current user details
        CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if (currentUserDetails == null) {
            throw new UsernameNotFoundException("Current user not found.");
        }

        // Get current user entity
        UserResponse currentUser = getUserByEmail.execute(currentUserDetails.getUsername());

        // Create JWT token
        String accessToken = jwtUtil.generateToken(currentUser.getEmail());
        return new LoginResponse(
              accessToken,
              null,
              currentUser.getUserType().getUserTypeEnum().toString()
        );
    };
}
