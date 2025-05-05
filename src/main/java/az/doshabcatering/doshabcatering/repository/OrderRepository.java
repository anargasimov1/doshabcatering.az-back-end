package az.doshabcatering.doshabcatering.repository;


import az.doshabcatering.doshabcatering.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {

    Page<Orders> findOrdersByDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

}
