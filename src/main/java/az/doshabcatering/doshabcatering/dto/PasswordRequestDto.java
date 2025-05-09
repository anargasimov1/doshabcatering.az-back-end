package az.doshabcatering.doshabcatering.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordRequestDto {

    @NotEmpty(message = "otp kodu boş ola bilməz!")
    String otp;

    @Size(min = 6, max = 12, message = "parol 6 ilə 12 simvol arası olmalıdır!")
    String password;
}
