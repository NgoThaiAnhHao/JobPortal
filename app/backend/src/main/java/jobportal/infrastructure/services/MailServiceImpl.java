package jobportal.infrastructure.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jobportal.application.services.MailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public MailServiceImpl(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendVerificationToken(String receiverEmail, String otpCode) throws MessagingException {
        // Create context to send "otpCode" variable
        Context context = new Context();
        context.setVariable("otpCode", otpCode);

        // Reading html file
        String htmlContent = templateEngine.process("otp-message", context);

        // Generate message
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("ngothaianhhao@gmail.com");
        helper.setTo(receiverEmail);
        helper.setSubject("Verify Your Email");
        helper.setText(htmlContent);

        emailSender.send(message);
    }
}
