package jobportal.application.usecase.authentication;

import jobportal.application.dto.authentication.RefreshTokenRequest;
import jobportal.application.utils.TokenHashUtils;
import jobportal.domain.entity.RefreshToken;
import jobportal.domain.repository.RefreshTokenRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogoutUseCase {

    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutUseCase(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void execute(RefreshTokenRequest refreshTokenRequest) {
        // Find token exist in database
        RefreshToken refreshTokenFound = refreshTokenRepository.findByTokenHash(
                TokenHashUtils.hashToken(refreshTokenRequest.getRefreshToken())
        );

        // Delete refresh token and clear authentication
        refreshTokenRepository.delete(refreshTokenFound);
        SecurityContextHolder.clearContext();
    }
}
