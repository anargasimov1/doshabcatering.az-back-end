package az.doshabcatering.doshabcatering.repository.jpaRepo;

import az.doshabcatering.doshabcatering.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.orders WHERE u.email = :email")
    Optional<UserEntity> findByIdWithOrders(@Param("email") String email);

    @Query("SELECT DISTINCT u FROM UserEntity u LEFT JOIN FETCH u.orders")
    Page<UserEntity> findAllWithOrders(Pageable pageable);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByOtp(String otp);
}
