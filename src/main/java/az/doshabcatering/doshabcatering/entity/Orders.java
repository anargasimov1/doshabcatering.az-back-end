package az.doshabcatering.doshabcatering.entity;
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.experimental.FieldDefaults;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Entity
//@Table(name = "orders")
//@Data
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class Orders {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    UUID Id;
//
//    @Column(nullable = false)
//    String meals;
//
//    @Column(nullable = false)
//    Double price;
//
//    @CreationTimestamp
//    LocalDateTime date;
//
//    Integer location;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    UserEntity user;
//
//}
