package az.doshabcatering.doshabcatering.controller;

import az.doshabcatering.doshabcatering.entity.Messages;
import az.doshabcatering.doshabcatering.servise.appService.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("public/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid Messages messages) {
        return messageService.save(messages);
    }

    @GetMapping("/{id}")
    public Messages findById(@PathVariable UUID id) {
        return messageService.findById(id);
    }


}
