package az.doshabcatering.doshabcatering.admin.service;

import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.repository.jpaRepo.UserRepository;
import az.doshabcatering.doshabcatering.servise.elasticService.ElasticUsersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final UserRepository userRepository;
    private final ElasticUsersService elasticUsersServiceService;

    public Page<UserEntity> listAllUsers(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return userRepository.findAll(pageRequest);
    }

    @Transactional
    public ResponseEntity<?> deleteUserById(Integer id) {
        userRepository.deleteById(id);
        elasticUsersServiceService.delete(id);
        return ResponseEntity.ok("Delete user with id " + id + " successfully");
    }
}
