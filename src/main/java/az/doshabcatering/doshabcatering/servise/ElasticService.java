package az.doshabcatering.doshabcatering.servise;

import az.doshabcatering.doshabcatering.documents.Users;
import az.doshabcatering.doshabcatering.repository.ElasticRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticService {

    private final ElasticRepo elasticRepo;

    public Iterable<Users> findAll() {
        return elasticRepo.findAll();
    }
    public void delete() {
        elasticRepo.deleteAll();
    }

    public Users save(Users users) {
        return elasticRepo.save(users);
    }


}
