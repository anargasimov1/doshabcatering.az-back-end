package az.doshabcatering.doshabcatering.admin.service;

import az.doshabcatering.doshabcatering.entity.Orders;
import az.doshabcatering.doshabcatering.enums.Status;
import az.doshabcatering.doshabcatering.repository.jpaRepo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        Status enumStatus = parseStatus(status);
        orders.setStatus(enumStatus);
    }

    private Status parseStatus(String statusStr) {
        try {
            return Status.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new RuntimeException("Invalid status value: " + statusStr);
        }
    }

    public Page<Orders> findBetweenDate(Pageable pageable) {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atTime(8, 0);
        LocalDateTime endOfDay = today.atTime(LocalTime.of(19, 0));
        return orderRepository.findOrdersByDateBetween(startOfDay, endOfDay, pageable);
    }

    public ResponseEntity<?> deleteOrder(UUID id) {
        orderRepository.deleteById(id);
        return ResponseEntity.ok("uğurla silindi!");
    }

}
