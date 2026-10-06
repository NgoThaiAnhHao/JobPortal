package jobportal.domain.exception.common.authentication;

public class AdminRegistrationNotAllowedException extends RuntimeException {
    public AdminRegistrationNotAllowedException(String message) {
        super(message);
    }
}
