package jobportal.application.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class TokenHashUtils {
    public static String hashToken(String token) {
        try {
            // Create hash model
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            // Div to bytes
            byte[] tokenBytes = token.getBytes(StandardCharsets.UTF_8);

            // Hash
            byte[] hashedBytes = sha256.digest(tokenBytes);

            // Convert to string
            return HexFormat.of().formatHex(hashedBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
