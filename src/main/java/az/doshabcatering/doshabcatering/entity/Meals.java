package az.doshabcatering.doshabcatering.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "meals")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Meals implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    String ingredients;

    String imageUrl;

    @Column(nullable = false)
    Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    Category category;

}
