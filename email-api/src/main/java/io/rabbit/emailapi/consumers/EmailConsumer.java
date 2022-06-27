package io.rabbit.emailapi.consumers;

import io.rabbit.emailapi.services.imp.EmailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailServiceImp emailServiceImp;
}
