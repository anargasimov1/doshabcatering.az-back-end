package az.doshabcatering.doshabcatering.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse<T> {

    LocalDateTime timestamp;
    String message;
    T errors;

}
