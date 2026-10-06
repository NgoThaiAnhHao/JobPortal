package jobportal.infrastructure.services;

import jobportal.application.services.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtEncoder jwtEncoder;;

    @Value("${spring.jwt.expiration}")
    private long EXPIRATION;

    public JwtServiceImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String generateAccessToken(String email, String role) {
        // Get now
        Instant now = Instant.now();

        // Set user information
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(email)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRATION))
                .claim("roles", List.of(role))
                .build();

        // Set header information
        JwsHeader header = JwsHeader
                .with(MacAlgorithm.HS256)
                .build();

        // Generate token
        return jwtEncoder
                .encode(JwtEncoderParameters.from(header, claims))
                .getTokenValue();
    }
}
