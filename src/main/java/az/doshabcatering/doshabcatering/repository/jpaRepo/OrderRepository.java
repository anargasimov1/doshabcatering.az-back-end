package az.doshabcatering.doshabcatering.repository.jpaRepo;


import az.doshabcatering.doshabcatering.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {

    List<Orders> findOrdersByDateBetween(LocalDateTime start, LocalDateTime end);

}
