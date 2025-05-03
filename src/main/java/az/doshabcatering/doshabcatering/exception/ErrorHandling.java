package az.doshabcatering.doshabcatering.exception;

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
public class ErrorHandling {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<String> handleSQLException() {
        return new ResponseEntity<>("Nəsə doğru olmadı!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>("istifadeçi tapılmadı!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handleRedisConnectionException(ConnectException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", "Redis server-ə qoşulmaq mümkün olmadı.");
        body.put("details", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
    }


}
