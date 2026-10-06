package jobportal.application.use_cases.authentication;

import jobportal.application.dto.authentication.LoginRequest;
import jobportal.application.dto.authentication.LoginResponse;

import jobportal.application.services.JwtService;
import jobportal.domain.entity.User;
import jobportal.domain.repository.RefreshTokenRepository;
import jobportal.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginUseCase(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public LoginResponse execute(LoginRequest loginRequest) {
        // Authenticate
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Get current user details
        String email = authentication.getName();

        // Get current user entity
        User currentUser = userRepository.findByEmail(email);

        // Clear old refreshToken if exists
        refreshTokenRepository.deleteByUser(currentUser);

        // Create refresh token
        String rawRefreshToken = refreshTokenRepository.generateRefreshToken(currentUser);

        // Create JWT token
        String accessToken = jwtService.generateAccessToken(
                email,
                currentUser.getUserType().getUserTypeEnum().toString()
        );

        // Return
        return new LoginResponse(
                accessToken,
                rawRefreshToken,
                currentUser.getUserType().getUserTypeEnum().toString()
        );
    };
}
