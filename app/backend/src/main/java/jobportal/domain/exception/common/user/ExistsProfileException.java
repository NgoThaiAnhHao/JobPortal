package jobportal.domain.exception.common.user;

public class ExistsProfileException extends RuntimeException {
    public ExistsProfileException(String message) {
        super(message);
    }
}
