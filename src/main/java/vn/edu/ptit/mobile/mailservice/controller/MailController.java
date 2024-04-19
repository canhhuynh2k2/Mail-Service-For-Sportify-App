package vn.edu.ptit.mobile.mailservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.mobile.mailservice.model.MailRequest;
import vn.edu.ptit.mobile.mailservice.service.MailService;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/register")
    public ResponseEntity<?> sendRegisterEmail(@RequestBody @Valid MailRequest mailRequest) {
        try{
            String res = mailService.sendMail(true, mailRequest);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send email");
        }

    }

    @PostMapping("/reset")
    public ResponseEntity<?> sendResetPasswordEmail(@RequestBody @Valid MailRequest mailRequest) {
        try{
            String res = mailService.sendMail(false, mailRequest);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send email");
        }
    }

}
