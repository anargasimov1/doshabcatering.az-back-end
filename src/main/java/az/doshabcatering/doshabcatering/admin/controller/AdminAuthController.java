package az.doshabcatering.doshabcatering.admin.controller;


import az.doshabcatering.doshabcatering.admin.service.AdminAuthService;
import az.doshabcatering.doshabcatering.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService adminAuthService;


    @GetMapping
    private Page<UserEntity> listAllUsers(@RequestParam(defaultValue = "0") Integer pageNumber,
                                          @RequestParam(defaultValue = "15") Integer pageSize) {
        return adminAuthService.listAllUsers(pageNumber, pageSize);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestParam Integer id) {
        return adminAuthService.deleteUserById(id);

    }
}
