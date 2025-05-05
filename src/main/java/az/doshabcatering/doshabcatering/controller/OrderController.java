package az.doshabcatering.doshabcatering.controller;

import az.doshabcatering.doshabcatering.entity.Orders;
import az.doshabcatering.doshabcatering.servise.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;

    @PostMapping("/{email}")
    public Orders createOrder(@RequestBody Orders orders, @PathVariable String email) {
        return ordersService.save(orders, email);
    }
}
