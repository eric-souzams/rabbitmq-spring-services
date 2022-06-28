package io.rabbit.emailapi.services;

import io.rabbit.emailapi.models.EmailEntity;

public interface EmailService {

    void sendEmail(EmailEntity emailModel);

    EmailEntity buildEmail(String emailTo, String orderStatus, String orderId);

}
