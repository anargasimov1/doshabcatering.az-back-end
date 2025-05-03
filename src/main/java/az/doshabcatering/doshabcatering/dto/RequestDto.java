package az.doshabcatering.doshabcatering.dto;

import jakarta.validation.constraints.Email;
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
public class RequestDto {

    String roles;

    @Size(min = 3, max = 20, message = "Ad xanası 3 ilə 20 simvol aralığında olmalıdır!")
    @NotEmpty(message = "Zəhmət olmasa ad xanasını doldurun!")
    String name;

    @Size(min = 5, max = 20, message = "Soyad xanası 5 ilə 20 simvol aralığında olmalıdır!")
    String surname;

    @Email(message = "Email format düzgün deyil!")
    @NotEmpty(message = "Zəhmət olmasa email xanasını doldurun!")
    String email;

    @Size(min = 5, max = 12, message = "Parol minimum 5 maksimum 12 simvol olmadıdır!")
    String password;

    @NotEmpty(message = "Zəhmət olmasa telefon nömrəsi xanasını doldurun!")
    String phone_number;

}
