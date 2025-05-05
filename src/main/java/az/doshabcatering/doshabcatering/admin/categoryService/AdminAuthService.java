package az.doshabcatering.doshabcatering.admin.categoryService;

import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.repository.UserRepository;
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


    public Page<UserEntity> listAllUsers(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return userRepository.findAll(pageRequest);
    }

    @Transactional
    public ResponseEntity<?> deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("Delete user with id " + id + " successfully");
    }
}
