package org.mail.microservice.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.mail.microservice.services.IEmailService;
import org.mail.microservice.services.models.EmailDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendMail(EmailDTO emailDTO) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(emailDTO.getDestinatario());
        helper.setSubject(emailDTO.getAsunto());

        Context context = new Context();
        context.setVariable("mensaje", emailDTO.getMensaje());
        context.setVariable("imageCid", "img2"); //AGREGAR IMAGEN
        String html = templateEngine.process("email", context);
        helper.setText(html, true);

        // Adjuntar la imagen como recurso incrustado
        ClassPathResource image = new ClassPathResource("static/img/welcome.png");
        helper.addInline("img2", image); // Asociar el ID con la imagen

        mailSender.send(mimeMessage);
    }

}
