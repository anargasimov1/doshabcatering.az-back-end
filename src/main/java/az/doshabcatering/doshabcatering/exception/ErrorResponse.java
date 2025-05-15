package az.doshabcatering.doshabcatering.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    LocalDateTime timestamp;
    String errorMessage;
    int status;

}
