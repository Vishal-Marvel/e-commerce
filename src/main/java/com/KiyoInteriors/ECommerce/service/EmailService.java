package com.KiyoInteriors.ECommerce.service;

import jakarta.mail.MessagingException;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendVerificationEmail(String recipientEmail, String otp, String purpose, String endpoint) throws MessagingException {
        String baseURL = "http://localhost:8080";
        String url = baseURL + "/"+endpoint+"?code=" + otp;
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("Verification");


        String htmlContent = "    <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"background-color: #f8f8f8;\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\" style=\"padding: 5px;\">\n" +
                "               <h1> Kiyo Interiors</h1>" +
                "            </td>" +
                "        </tr>" +
                "        <tr>" +
                "            <td style=\"padding: 20px;\">\n" +
                "                <h3 style=\"font-size: 24px; margin-bottom: 20px;\">Email Verification</h3>\n" +
                "                <p style=\"font-size: 16px; margin-bottom: 20px;\">Dear User,</p>\n" +
                "                <p style=\"font-size: 16px; margin-bottom: 20px;\">Thank you for registering with our company. You received this email because you requested for "+purpose+" please click the button below:</p>\n" +
                "                <a href=\""+url+"\" style=\"display: inline-block; background-color: #4caf50; color: #ffffff; font-size: 16px; text-decoration: none; padding: 10px 20px; border-radius: 5px;\">Verify Email</a>\n" +
                "                <p style=\"font-size: 16px; margin-top: 20px;\">If you did not create an account with us, please ignore this email.</p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>";
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }
}
