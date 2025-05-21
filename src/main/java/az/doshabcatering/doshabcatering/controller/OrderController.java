package az.doshabcatering.doshabcatering.controller;

import az.doshabcatering.doshabcatering.entity.Orders;
import az.doshabcatering.doshabcatering.servise.appService.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*",methods =  {RequestMethod.GET, RequestMethod.POST})
public class OrderController {

    private final OrdersService ordersService;

    @PostMapping("/{email}")
    public ResponseEntity<?> createOrder(@RequestBody Orders orders, @PathVariable String email) {
        return ordersService.save(orders, email);
    }
}
