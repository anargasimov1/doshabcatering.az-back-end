package az.doshabcatering.doshabcatering.entity;

import az.doshabcatering.doshabcatering.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID Id;

    String meals;

    String prince;


    @JsonFormat(pattern = "dd.MM.YYYY")
    LocalDateTime date;

    String status;

    String lat;

    String lng;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    UserEntity user;

    @PrePersist
    void prePersist() {
        this.status = Status.PENDING.toString();
        ZoneId bakuZone = ZoneId.of("Asia/Baku");
        this.date = ZonedDateTime.now(bakuZone).toLocalDateTime();
    }


}
