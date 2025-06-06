package az.doshabcatering.doshabcatering.admin.controller;

import az.doshabcatering.doshabcatering.admin.service.AdminOrdersService;
import az.doshabcatering.doshabcatering.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.DELETE})
public class AdminOrdersController {

    private final AdminOrdersService adminOrdersService;

    @GetMapping
    public Page<Orders> getOrders(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return adminOrdersService.findAll(pageNum, pageSize);
    }

    @PostMapping("/{id}")
    public void upDateOrders(@PathVariable UUID id, @RequestBody String status) {
        adminOrdersService.findOrders(id, status);
    }

    @GetMapping("/today")
    public List<Orders> getOrdersToday() {
        return adminOrdersService.findBetweenDate();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrders(@PathVariable UUID id) {
        return adminOrdersService.deleteOrder(id);
    }
}
