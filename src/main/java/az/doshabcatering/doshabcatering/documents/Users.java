package az.doshabcatering.doshabcatering.documents;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {

    @Id
    Integer id;

    String name;

    String surname;

    String email;

    String phone_number;


}
