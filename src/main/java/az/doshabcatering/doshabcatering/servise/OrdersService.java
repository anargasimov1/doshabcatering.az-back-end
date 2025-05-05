package az.doshabcatering.doshabcatering.servise;


import az.doshabcatering.doshabcatering.entity.Orders;
import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrderRepository orderRepository;
    private final AuthService authService;

    public Orders save(Orders orders, String email) {
        UserEntity user = authService.getUserByEmail(email);
        orders.setUser(user);
        return orderRepository.save(orders);
    }


}
