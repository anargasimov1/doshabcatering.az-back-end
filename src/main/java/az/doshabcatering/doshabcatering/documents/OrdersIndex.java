package az.doshabcatering.doshabcatering.documents;


import az.doshabcatering.doshabcatering.enums.Status;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.UUID;

@Document(indexName = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrdersIndex {

    @Id
    UUID id;

    @Field(name = "meals")
    String meals;

    String price;
    Status status;

}
