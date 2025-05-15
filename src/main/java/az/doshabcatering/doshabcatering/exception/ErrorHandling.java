package az.doshabcatering.doshabcatering.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ErrorHandling {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        log.error(errors.toString());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SQLException.class)
    public ErrorResponse handleSQLException(SQLException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = BeanCreationException.class)
    public ErrorResponse handleBeanCreationException(BeanCreationException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ErrorResponse handleException(UsernameNotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(LocalDateTime.now(), "user not found", HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = ConnectException.class)
    public ErrorResponse handleConnectionException(ConnectException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value());
    }

    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handleException(Exception ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
