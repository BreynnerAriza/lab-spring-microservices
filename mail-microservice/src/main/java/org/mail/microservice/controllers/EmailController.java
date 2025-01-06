package org.mail.microservice.controllers;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.mail.microservice.services.IEmailService;
import org.mail.microservice.services.models.EmailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
@AllArgsConstructor
public class EmailController {

    private final IEmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) throws MessagingException {
        emailService.sendMail(emailDTO);
        return ResponseEntity.ok("Email sent");
    }

}
