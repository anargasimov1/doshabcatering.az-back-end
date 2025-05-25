package az.doshabcatering.doshabcatering.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DtoResponse {

    Integer id;

    String name;

    String surname;

    String email;

    String phone_number;

    boolean isVerified;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime created_at;

}
