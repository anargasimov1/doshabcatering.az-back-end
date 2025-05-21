package az.doshabcatering.doshabcatering.servise.elasticService;

import az.doshabcatering.doshabcatering.documents.Users;
import az.doshabcatering.doshabcatering.repository.elasticRepo.ElasticUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticUsersService {

    private final ElasticUserRepo elasticRepo;

    public void delete(Integer id) {
        elasticRepo.deleteById(id);
    }

    public Users save(Users users) {
        return elasticRepo.save(users);
    }

    public Users findById(Integer id) {
        return elasticRepo.findById(id).orElse(null);
    }


}
