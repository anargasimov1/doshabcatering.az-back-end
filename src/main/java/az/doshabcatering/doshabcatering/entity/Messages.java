package az.doshabcatering.doshabcatering.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String fullName;

    String email;
    String subject;
    String message;
    String phone;

    @CreationTimestamp
    LocalDateTime date;
}
