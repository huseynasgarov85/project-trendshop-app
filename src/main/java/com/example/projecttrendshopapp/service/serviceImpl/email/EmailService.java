package com.example.projecttrendshopapp.service.serviceImpl.email;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;


    public void sendSimpleEmail(String userEmail, String subject, String body, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userEmail);
        message.setTo(subject);
        message.setSubject(body);
        message.setText(to);
        javaMailSender.send(message);
    }

    public void sendToSuccessPayment(List<BasketEntity> list) {
        String to = null;
        if (list.stream().findFirst().isPresent()) {
            to = list.stream().findFirst().get().getUser().getEmail();
        }
        Double productPrice = list.stream().findFirst().get().getOrder().getProductsPrice();
        sendSimpleEmail("huseynnaskerovv@gmail.com", to, "TrendShop Payment Transaction !!", generateBill(productPrice));
    }

    public void sendEmailWithAttachment(String email, String to, String body, String subject) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(email);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        javaMailSender.send(mimeMessage);
    }

    @SneakyThrows
    public void sendOtpEmail(String email, String otp) {
        String to = email;
        String body = "verify your identity : " + otp;
        String subject = "Your OTP code";
        sendEmailWithAttachment("huseynnaskerovv@gmail.com", to, body, subject);
    }

    private String generateBill(Double price) {
        String paymentMessage = "Successfully Payment Generated";
        return "Total Price: $" + price + "\n" + paymentMessage + "\n" + "Date: " + LocalDate.now();
    }
}
