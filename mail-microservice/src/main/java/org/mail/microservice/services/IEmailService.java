package org.mail.microservice.services;

import jakarta.mail.MessagingException;
import org.mail.microservice.services.models.EmailDTO;

public interface IEmailService {

    public void sendMail(EmailDTO emailDTO) throws MessagingException;

}
