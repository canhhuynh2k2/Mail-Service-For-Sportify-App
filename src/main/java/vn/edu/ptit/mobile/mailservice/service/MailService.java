package vn.edu.ptit.mobile.mailservice.service;

import jakarta.mail.MessagingException;
import vn.edu.ptit.mobile.mailservice.model.MailRequest;

public interface MailService {
    String sendMail(Boolean isRegister, MailRequest mailRequest) throws MessagingException;
}
