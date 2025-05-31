package az.doshabcatering.doshabcatering.admin.service;

import az.doshabcatering.doshabcatering.entity.Orders;
import az.doshabcatering.doshabcatering.repository.jpaRepo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminOrdersService {

    private final OrderRepository orderRepository;

    public Page<Orders> findAll(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("date").ascending());
        return orderRepository.findAll(pageRequest);
    }

    public void findOrders(UUID id, String status) {
        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        orders.setStatus(status);
        orderRepository.save(orders);
    }

    public List<Orders> findBetweenDate() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atTime(10, 0);
        LocalDateTime endOfDay = today.atTime(LocalTime.of(17, 0));
        return orderRepository.findOrdersByDateBetween(startOfDay, endOfDay);
    }

    public ResponseEntity<?> deleteOrder(UUID id) {
        orderRepository.deleteById(id);
        return ResponseEntity.ok("uğurla silindi!");
    }

}
