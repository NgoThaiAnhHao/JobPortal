package jobportal.domain.repository;

import jobportal.domain.entity.RefreshToken;
import jobportal.domain.entity.User;

public interface RefreshTokenRepository {
    void deleteByUser(User user);

    RefreshToken findByTokenHash(String tokenHash);

    void delete(RefreshToken refreshToken);

    String generateRefreshToken(User user);

}
