package az.doshabcatering.doshabcatering.repository.elasticRepo;

import az.doshabcatering.doshabcatering.documents.OrdersIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface ElasticOrderRepo extends ElasticsearchRepository<OrdersIndex, UUID> {
}
