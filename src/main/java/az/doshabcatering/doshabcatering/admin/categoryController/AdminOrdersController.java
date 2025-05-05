package az.doshabcatering.doshabcatering.admin.categoryController;

import az.doshabcatering.doshabcatering.admin.categoryService.AdminOrdersService;
import az.doshabcatering.doshabcatering.entity.Orders;
import az.doshabcatering.doshabcatering.servise.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrdersController {

    private final AdminOrdersService adminOrdersService;

    @GetMapping
    public Page<Orders> getOrders(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return adminOrdersService.findAll(pageNum, pageSize);
    }

    @GetMapping("/today")
    public Page<Orders> getOrdersToday(Pageable pageable) {
        return adminOrdersService.findBetweenDate(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrders(@PathVariable UUID id) {
        return adminOrdersService.deleteOrder(id);
    }
}
