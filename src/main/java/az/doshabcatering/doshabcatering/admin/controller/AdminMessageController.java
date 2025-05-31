package az.doshabcatering.doshabcatering.admin.controller;


import az.doshabcatering.doshabcatering.admin.service.AdminMessageService;
import az.doshabcatering.doshabcatering.entity.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/messages")
@RequiredArgsConstructor
public class AdminMessageController {

    private final AdminMessageService adminMessageService;

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable UUID id) {
        adminMessageService.DeleteMessage(id);
    }

    @GetMapping
    public List<Messages> getMessage() {
        return adminMessageService.getMessage();
    }
}
