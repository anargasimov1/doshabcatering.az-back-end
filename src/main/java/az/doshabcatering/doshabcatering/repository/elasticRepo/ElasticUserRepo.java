package az.doshabcatering.doshabcatering.repository.elasticRepo;

import az.doshabcatering.doshabcatering.documents.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticUserRepo extends ElasticsearchRepository<Users, Integer> {


}
