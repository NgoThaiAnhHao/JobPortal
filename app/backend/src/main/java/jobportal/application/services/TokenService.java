package jobportal.application.services;

public interface TokenService {
    String generateAccessToken(String email);
}
