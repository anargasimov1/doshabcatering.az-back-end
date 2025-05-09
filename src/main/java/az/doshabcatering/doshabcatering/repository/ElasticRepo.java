package az.doshabcatering.doshabcatering.repository;

import az.doshabcatering.doshabcatering.documents.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepo extends ElasticsearchRepository<Users, Integer> {


}
