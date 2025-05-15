package az.doshabcatering.doshabcatering.dto.response;

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
    boolean is_Verified;
    LocalDateTime created_at;

}
