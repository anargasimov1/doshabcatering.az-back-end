package az.doshabcatering.doshabcatering.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestLogin {

    @NotEmpty(message = "email xanasını doldurun")
    @Email(message = "email format düzgün deyil!")
    String email;

    @NotEmpty(message = "parol xanasını doldurun")
    String password;
}
