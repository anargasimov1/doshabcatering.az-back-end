package az.doshabcatering.doshabcatering.servise.appService;

import az.doshabcatering.doshabcatering.entity.Messages;
import az.doshabcatering.doshabcatering.repository.jpaRepo.MessageRepo;
import az.doshabcatering.doshabcatering.servise.mail.MailService;
import az.doshabcatering.doshabcatering.utils.HtmlTemplates;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepo messageRepo;
    private final MailService mailService;

    @SneakyThrows
    public ResponseEntity<String> save(Messages messages) {
        messageRepo.save(messages);
        log.info("save message successful");
        mailService.sendMail(messages.getEmail(), "müraciətin qeydə alınması",
                HtmlTemplates.Message(messages.getFullName()));
        mailService.sendMail("mehman@doshabcatering.az", "yeni mesaj",
                "portaldan yeni mesaj var: \n" + messages.getMessage());
        return ResponseEntity.ok("mesajınız uğurla göndərildi!");
    }

    public Messages findById(UUID id) {
        return messageRepo.findById(id).orElse(null);
    }
}
