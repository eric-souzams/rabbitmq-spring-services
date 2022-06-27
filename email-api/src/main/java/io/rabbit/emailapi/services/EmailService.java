package io.rabbit.emailapi.services;

import io.rabbit.emailapi.models.EmailEntity;

public interface EmailService {

    EmailEntity sendEmail(EmailEntity emailModel);

}
