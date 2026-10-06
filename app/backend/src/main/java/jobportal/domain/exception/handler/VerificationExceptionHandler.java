package jobportal.domain.exception.handler;

import jobportal.domain.exception.common.verification.AlreadyVerifiedAccountException;
import jobportal.domain.exception.common.verification.InvalidOtpException;
import jobportal.domain.exception.common.verification.OtpExpiredException;
import jobportal.domain.exception.error.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class VerificationExceptionHandler {
    @ExceptionHandler(OtpExpiredException.class)
    public ResponseEntity<ApiErrorResponse> handleOtpExpiredException(
            OtpExpiredException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Otp_Expired",
                Map.of("otpCode", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidOtpException(
            InvalidOtpException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid_Otp",
                Map.of("otpCode", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyVerifiedAccountException.class)
    public ResponseEntity<ApiErrorResponse> handleAlreadyVerifiedAccountException(
            AlreadyVerifiedAccountException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Already_Verified_Account",
                Map.of("otpCode", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
