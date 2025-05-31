package az.doshabcatering.doshabcatering.entity;

import az.doshabcatering.doshabcatering.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    LocalDateTime created_at;

    LocalDateTime otp_expires_at;

    LocalDateTime updated_at;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    List<Orders> orders;


    @PrePersist
    public void assignDefaultValues() {
        if (roles == null) {
            this.roles = Roles.USER;
        }
        ZoneId bakuZone = ZoneId.of("Asia/Baku");
        this.created_at = ZonedDateTime.now(bakuZone).toLocalDateTime();
        this.otp_expires_at = ZonedDateTime.now(bakuZone).toLocalDateTime();

    }

    @PreUpdate
    public void updateTimestamp() {
        ZoneId bakuZone = ZoneId.of("Asia/Baku");
        this.updated_at = ZonedDateTime.now(bakuZone).toLocalDateTime();
        this.otp_expires_at = ZonedDateTime.now(bakuZone).toLocalDateTime();

    }

}

