package jobportal.application.use_cases.refresh_token;

import jobportal.application.dto.authentication.LoginResponse;
import jobportal.application.dto.authentication.RefreshTokenRequest;
import jobportal.application.services.TokenService;
import jobportal.application.utils.TokenHashUtils;
import jobportal.domain.entity.RefreshToken;
import jobportal.domain.entity.User;
import jobportal.domain.exception.common.authentication.AccountDisabledException;
import jobportal.domain.exception.common.authentication.UserNotFoundException;
import jobportal.domain.exception.common.refresh_token.TokenExpiredException;
import jobportal.domain.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RefreshAnAccessTokenUseCase {

    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshAnAccessTokenUseCase(TokenService tokenService, RefreshTokenRepository refreshTokenRepository) {
        this.tokenService = tokenService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public LoginResponse execute(RefreshTokenRequest refreshTokenRequest) {
        // Validate refresh token and return current user who logged in
        User currentUser = validateRefreshTokenEligibility(refreshTokenRequest);

        // Clear old refreshToken if exists
        refreshTokenRepository.deleteByUser(currentUser);

        // Create refresh token
        String rawRefreshToken = refreshTokenRepository.generateRefreshToken(currentUser);

        // Generate new access token
        String accessToken = tokenService.generateAccessToken(currentUser.getEmail());

        return new LoginResponse(
                accessToken,
                rawRefreshToken,
                currentUser.getUserType().getUserTypeEnum().toString()
        );
    }

    public User validateRefreshTokenEligibility(RefreshTokenRequest refreshTokenRequest) {
        // Find token exist in database
        RefreshToken refreshTokenFound = refreshTokenRepository
                .findByTokenHash(
                        TokenHashUtils.hashToken(refreshTokenRequest.getRefreshToken())
                );
        User user = refreshTokenFound.getUser();

        // Check user account was be deleted
        if (user == null) {
            refreshTokenRepository.delete(refreshTokenFound);
            throw new UserNotFoundException("Account was be deleted.");
        }

        // Check user not verified
        if (!user.isEnabled()) {
            refreshTokenRepository.delete(refreshTokenFound);
            throw new AccountDisabledException("Account not be verified");
        }

        // Check expired token
        if (refreshTokenFound.getExpiredAt().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshTokenFound);
            throw new TokenExpiredException("Refresh token is expired, try login again.");
        }

        return user;
    }
}
