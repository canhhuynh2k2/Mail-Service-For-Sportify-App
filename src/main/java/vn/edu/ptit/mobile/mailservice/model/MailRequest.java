package vn.edu.ptit.mobile.mailservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {

    private String toEmail;

    private String name;

    private String code;

}
