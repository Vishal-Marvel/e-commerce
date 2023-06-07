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
        String url = baseURL + "/auth/"+endpoint+"?code=" + otp;
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("Verification");

        // Set the HTML content
        String htmlContent = "<table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" align=\"center\" bgcolor=\"#FFFFFF\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td style=\"padding:0px 40px 20px 40px;font-family:'Roboto',sans-serif;font-size:17px;line-height:20px;color:#555555;text-align:center;font-weight:300\">\n" +
                "\n" +
                "\n" +
                "            <p style=\"margin:0 0 5px 0\">You received this email because you requested for "+purpose+"</p>\n" +
                "\n" +
                "\n" +
                "    </td></tr> \n" +
                "    <tr>\n" +
                "        <td style=\"padding:0px 40px 20px 40px;font-family:'Roboto',sans-serif;font-size:17px;line-height:20px;color:#555555;text-align:center;font-weight:300\">\n" +
                "\n" +
                "\n" +
                "            <p style=\"margin:0 0 5px 0\">The below link is valid only for one hour</p>\n" +
                "\n" +
                "\n" +
                "    </td></tr>\n" +
                "    <tr>\n" +
                "        <td style=\"padding:20px 40px 40px 40px;text-align:center\" align=\"center\">" +
                "            <p style=\"text-align: center; margin-top: 10px;font-size:20px\"><a href=\""+url+"\">Click here</a> to verify your email.</p>\n" +
                "\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "\n" +
                "</tbody></table>";

        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }
}
