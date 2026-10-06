package jobportal.domain.exception.common.verification;

public class AlreadyVerifiedAccountException extends RuntimeException {
    public AlreadyVerifiedAccountException(String message) {
        super(message);
    }
}
