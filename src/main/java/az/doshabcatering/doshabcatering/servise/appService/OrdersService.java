package az.doshabcatering.doshabcatering.servise.appService;

import az.doshabcatering.doshabcatering.documents.OrdersIndex;
import az.doshabcatering.doshabcatering.entity.Orders;
import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.repository.jpaRepo.OrderRepository;
import az.doshabcatering.doshabcatering.servise.elasticService.ElasticOrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final ElasticOrdersService elasticOrdersService;

    public ResponseEntity<?> save(Orders orders, String email) {
        UserEntity user = authService.getUserByEmail(email);
        orders.setUser(user);
        orderRepository.save(orders);
        log.info("user with email {} has been created order", user.getEmail());
        OrdersIndex ordersIndex = new OrdersIndex();
        BeanUtils.copyProperties(orders, ordersIndex);
        elasticOrdersService.saveOrder(ordersIndex);
        log.info("orders has been saved with orderIndex id {}", ordersIndex.getId());
        return ResponseEntity.ok("Orders has been saved successfully");
    }
}
