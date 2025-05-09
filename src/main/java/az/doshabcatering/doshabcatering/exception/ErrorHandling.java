package az.doshabcatering.doshabcatering.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.GenericJDBCException;
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

@RestControllerAdvice
@Slf4j
public class ErrorHandling {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        log.error(errors.toString());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SQLException.class, GenericJDBCException.class})
    public ResponseEntity<String> handleSQLException(SQLException ex, GenericJDBCException jdbcException) {
        log.error(ex.getMessage());
        log.error(jdbcException.getMessage());
        return new ResponseEntity<>("Nəsə doğru olmadı!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<String> handleException(UsernameNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>("istifadeçi tapılmadı!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handleRedisConnectionException(ConnectException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Redis server-ə qoşulmaq mümkün olmadı.");
        body.put("details", ex.getMessage());
        log.error(body.toString());

        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleJDBCException(RuntimeException ex) {
        log.error(ex.getMessage());
        ErrorResponse<?> errorResponse = new ErrorResponse<>();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
