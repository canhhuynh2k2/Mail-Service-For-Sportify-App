package vn.edu.ptit.mobile.mailservice.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import vn.edu.ptit.mobile.mailservice.model.MailRequest;
import vn.edu.ptit.mobile.mailservice.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public String sendMail(Boolean isRegister, MailRequest mailRequest) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(MimeMessage.RecipientType.TO, mailRequest.getToEmail());
        if(isRegister){
            message.setSubject("WELCOME TO SPORTIFY! PLEASE VERIFY YOUR ACCOUNT!");
            message.setContent(getRegisterContext(mailRequest.getName(), mailRequest.getCode()), "text/html; charset=utf-8");
        }
        else {
            message.setSubject("RESET PASSWORD FOR YOUR ACCOUNT ON SPORTIFY!");
            message.setContent(getResetPasswordContext(mailRequest.getName(), mailRequest.getCode()), "text/html; charset=utf-8");
        }
        emailSender.send(message);
        return "OK";
    }

    public String getRegisterContext(String name, String code) {
        return "<p style=\"font-size: 18px\">Hello <strong>" + name + "</strong>,</p>" +
                "<p style=\"font-size: 18px\">Welcome to Sportify! Your verification code is: <strong>" + code + "</strong></p>" +
                "<p style=\"font-size: 18px\">Thank you for using our service!</p>" +
                "<p style=\"font-size: 18px\">Best regards,</p><p style=\"font-size: 18px\"><i>Sportify Team</i></p>";
    }

    public String getResetPasswordContext(String name, String code) {
        return "<p style=\"font-size: 18px\">Hello <strong>" + name + "</strong>,</p>" +
                "<p style=\"font-size: 18px\">Your reset password code is: <strong>" + code + "</strong></p>" +
                "<p style=\"font-size: 18px\" >Thank you for using our service!</p>" +
                "<p style=\"font-size: 18px\">Best regards,</p style=\"font-size: 18px\"><p><i>Sportify Team</i></p>";
    }
}
