package jobportal.domain.exception.handler;


import jobportal.domain.exception.common.DuplicateResourceException;
import jobportal.domain.exception.common.ResourceNotFoundException;
import jobportal.domain.exception.common.TooManyRequestException;
import jobportal.domain.exception.error.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new LinkedHashMap<>();

        // Add to errors list
        e.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        // Set error details
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Method_Argument_Not_Valid",
                errors
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    // Enum exception
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        Map<String, String> errors = new LinkedHashMap<>();

        String message = "Invalid value: " + e.getValue();

        // For enum type
        if (e.getRequiredType() != null && e.getRequiredType().isEnum()) {
            Object[] acceptedValues = e.getRequiredType().getEnumConstants();
            message += ". Accepted values: " + Arrays.toString(acceptedValues);
        }

        // Add to errors list
        errors.put(
                e.getName(),
                message
        );

        // Set error details
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Method_Argument_Type_Mismatch",
                errors
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put(
                "userTypeName",
                "Accepted Value: [RECRUITER, JOB_SEEKER, ADMIN]"
        );

        errors.put(
                "provider",
                "Accepted Value: [LOCAL, GOOGLE]"
        );

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "HttpMessageNotReadable",
                errors
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Resource_Not_Found",
                Map.of("resource", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateResourceException(
            DuplicateResourceException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Duplicate_Resource",
                Map.of("resource", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TooManyRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleTooManyRequestException(
            TooManyRequestException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.TOO_MANY_REQUESTS.value(),
                "Too_Many_Request",
                Map.of("requests", e.getMessage())
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.TOO_MANY_REQUESTS);
    }

}