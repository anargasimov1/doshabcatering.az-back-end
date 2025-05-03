package az.doshabcatering.doshabcatering.servise;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendMail(String email,String otp,String subject) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText("<html><body>" +
                "<p>Salam,</p>" +
                "<p>E-mail ünvanınızı təsdiqləmək üçün aşağıdakı linkə klikləyin:</p>" +
                "<p style='font-size: 18px; font-weight: bold;'><a href='http://127.0.0.1:8080/api/auth/verify/" + otp + "'>🔐 Emaili Təsdiqlə</a></p>" +
                "<p>Bu kod <strong>5 dəqiqə</strong> ərzində etibarlıdır.</p>" +
                "<p>Əgər bu sorğunu siz etməmisinizsə, xahiş edirik bu mesajı nəzərə almayın.</p>" +
                "<br><p>Hörmətlə,<br>Doshabcatering komandası</p>" +
                "</body></html>",true);
        helper.setFrom("info@doshabcatering.az");
        mailSender.send(mimeMessage);

    }
}
