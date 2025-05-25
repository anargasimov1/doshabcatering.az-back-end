package az.doshabcatering.doshabcatering.servise.appService;

import az.doshabcatering.doshabcatering.entity.Messages;
import az.doshabcatering.doshabcatering.repository.jpaRepo.MessageRepo;
import az.doshabcatering.doshabcatering.servise.mail.MailService;
import az.doshabcatering.doshabcatering.utils.HtmlTemplates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepo messageRepo;
    private final MailService mailService;


    public ResponseEntity<String> save(Messages messages) {
        messageRepo.save(messages);
        log.info("save message successful");
        try {
            mailService.sendMail(messages.getEmail(), "müraciətin qeydə alınması",
                    HtmlTemplates.Message(messages.getFullName()));
            mailService.sendMail("mehman@doshabcatering.az", "yeni mesaj",
                    "portaldan yeni mesaj var: \n" + messages.getMessage());
        } catch (Exception e) {
            log.error("save message failed {}", e.getMessage());
        }
        return ResponseEntity.ok("mesajınız uğurla göndərildi!");
    }

}
