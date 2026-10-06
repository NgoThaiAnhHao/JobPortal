package jobportal.domain.exception.handler;

import jobportal.domain.exception.common.user.ExistsProfileException;
import jobportal.domain.exception.common.user.InvalidUserTypeException;
import jobportal.domain.exception.common.user.ProfileNotFoundException;
import jobportal.domain.exception.error.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class UserHandlerException {

    @ExceptionHandler(InvalidUserTypeException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidUserTypeException(
            InvalidUserTypeException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid_User_Type",
                Map.of("userType", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistsProfileException.class)
    public ResponseEntity<ApiErrorResponse> handleExistsProfileException(
            ExistsProfileException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Exists_Profile",
                Map.of("userProfile", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleProfileNotFoundException(
            ProfileNotFoundException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Profile_Not_Found",
                Map.of("userProfile", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }
}
