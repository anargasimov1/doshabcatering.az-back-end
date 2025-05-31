package az.doshabcatering.doshabcatering.admin.service;

import az.doshabcatering.doshabcatering.entity.Messages;
import az.doshabcatering.doshabcatering.repository.jpaRepo.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminMessageService {
    private final MessageRepo messageRepo;

    public void DeleteMessage(UUID id) {
        messageRepo.findById(id)
                .ifPresent(messages -> messageRepo.deleteById(id));

    }

    public List<Messages> getMessage() {
      return messageRepo.findAll();
    }

}
