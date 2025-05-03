package az.doshabcatering.doshabcatering.repository;

import az.doshabcatering.doshabcatering.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByOtp(String otp);
}
