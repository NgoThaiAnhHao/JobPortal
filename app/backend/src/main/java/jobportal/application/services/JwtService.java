package jobportal.application.services;

import jobportal.domain.entity.User;
import org.springframework.security.core.Authentication;

public interface JwtService {
    String generateAccessToken(String email, String role);
}
