package az.doshabcatering.doshabcatering.admin.controller;

import az.doshabcatering.doshabcatering.admin.service.AdminUsersService;
import az.doshabcatering.doshabcatering.dto.response.DtoResponse;
import az.doshabcatering.doshabcatering.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AdminAuthController {

    private final AdminUsersService adminUsersService;

    @GetMapping
    private ResponseEntity<?> success() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{email}")
    public UserEntity getUserWithOrders(@PathVariable String email) {
        return adminUsersService.getUserWithOrders(email);
    }

    @GetMapping("/allusers")
    public Page<UserEntity> getUsersWithOrders(@RequestParam(defaultValue = "0") int size, @RequestParam(defaultValue = "10") int page) {
        return adminUsersService.listAllUsersWithOrders(size, page);
    }

    @GetMapping("/users")
    private Page<DtoResponse> listAllUsers(@RequestParam(defaultValue = "0") Integer num,
                                           @RequestParam(defaultValue = "15") Integer size) {
        return adminUsersService.listAllUsers(num, size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
        return adminUsersService.deleteUserById(id);

    }
}
