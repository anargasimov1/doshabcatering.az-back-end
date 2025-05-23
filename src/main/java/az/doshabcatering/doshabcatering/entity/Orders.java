package az.doshabcatering.doshabcatering.entity;

import az.doshabcatering.doshabcatering.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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

    @CreationTimestamp
    @JsonFormat(pattern = "dd.MM.YYYY")
    LocalDateTime date;

    @Enumerated(EnumType.STRING)
    Status status;

    String lat;

    String lng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    UserEntity user;

    @PrePersist
    void prePersist() {
        this.status = Status.PENDING;
    }

}
