package io.rabbit.emailapi.services.imp;

import io.rabbit.emailapi.enums.StatusEmail;
import io.rabbit.emailapi.models.EmailEntity;
import io.rabbit.emailapi.repositories.EmailRepository;
import io.rabbit.emailapi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendEmail(EmailEntity emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

//            emailSender.send(message); // comment if won't send a email

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException exception){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(emailModel);
        }
    }

    @Override
    public EmailEntity buildEmail(String emailTo, String orderStatus, String orderId) {
        return EmailEntity.builder()
                .emailFrom("no-reply@google.com")
                .emailTo(emailTo)
                .subject("Checkout Order " + orderStatus)
                .text("Your order from id " + orderId + " has been " + orderStatus)
                .statusEmail(StatusEmail.PROCESSING)
                .sendDateEmail(LocalDateTime.now())
                .build();
    }

}
