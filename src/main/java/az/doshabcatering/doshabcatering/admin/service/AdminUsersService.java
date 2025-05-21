package az.doshabcatering.doshabcatering.admin.service;

import az.doshabcatering.doshabcatering.dto.response.DtoResponse;
import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.repository.jpaRepo.UserRepository;
import az.doshabcatering.doshabcatering.servise.elasticService.ElasticUsersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUsersService {

    private final UserRepository userRepository;
    private final ElasticUsersService elasticUsersServiceService;

    public Page<DtoResponse> listAllUsers(int num, int size) {
        PageRequest pageRequest = PageRequest.of(num, size, Sort.by("id"));
        Page<UserEntity> users = userRepository.findAll(pageRequest);

        return users.map(userEntity -> {
            DtoResponse dto = new DtoResponse();
            BeanUtils.copyProperties(userEntity, dto);
            return dto;
        });
    }

    public UserEntity getUserWithOrders(String email) {
        return userRepository.findByIdWithOrders(email).orElse(null);
    }

    public Page<UserEntity> listAllUsersWithOrders(int num, int size) {
        PageRequest pageRequest = PageRequest.of(num, size, Sort.by("id"));
        return userRepository.findAllWithOrders(pageRequest);
    }


    @Transactional
    public ResponseEntity<?> deleteUserById(Integer id) {
        userRepository.deleteById(id);
        elasticUsersServiceService.delete(id);
        return ResponseEntity.ok("Delete user with id " + id + " successfully");
    }

}
