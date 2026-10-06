package jobportal.domain.exception.handler;

import jobportal.domain.exception.common.authentication.*;
import jobportal.domain.exception.common.refresh_token.InvalidRefreshTokenException;
import jobportal.domain.exception.common.refresh_token.TokenExpiredException;
import jobportal.domain.exception.error.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(AdminRegistrationNotAllowedException.class)
    public ResponseEntity<ApiErrorResponse> handleAdminRegistrationNotAllowedException(
            AdminRegistrationNotAllowedException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "Admin_Registration_Not_Allowed",
                Map.of("userTypeName", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handlePasswordMismatchException(
            PasswordMismatchException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Password_Mismatch",
                Map.of("password", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(
            UserNotFoundException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "User_Not_Found_Exception",
                Map.of("user", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountDisabledException.class)
    public ResponseEntity<ApiErrorResponse> handleAccountDisabledException(
            AccountDisabledException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Account_Disabled",
                Map.of("user", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(
            BadCredentialsException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Bad_Credentials",
                Map.of("login", "Email or password is incorrect.")
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRefreshTokenException(
            InvalidRefreshTokenException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid_Refresh_Token",
                Map.of("refreshToken", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ApiErrorResponse> handleTokenExpiredException(
            TokenExpiredException e) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Token_Expired",
                Map.of("refreshToken", e.getMessage())
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegisterFailedException.class)
    public ResponseEntity<ApiErrorResponse> handleRegisterFailedException(
            RegisterFailedException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Register_Failed",
                Map.of("register", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotAuthenticatedException(
            UserNotAuthenticatedException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "User_Not_Authenticated",
                Map.of("login", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
