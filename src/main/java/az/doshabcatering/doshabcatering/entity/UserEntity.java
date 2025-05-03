package az.doshabcatering.doshabcatering.entity;

import az.doshabcatering.doshabcatering.enums.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    String surname;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String email;

    @Column(name = "phone_number", nullable = false)
    String phone_number;

    @Column(name = "is_verified", nullable = false)
    boolean isVerified;

    @Enumerated(EnumType.STRING)
    Roles roles;

    String otp;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime created_at;

    LocalDateTime updated_at;


    @PostPersist
    private void assignDefaultValues() {
        if(roles == null){
            this.roles = Roles.USER;
        }
        this.created_at = LocalDateTime.now();
    }

}

