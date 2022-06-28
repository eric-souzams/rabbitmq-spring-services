package io.rabbit.emailapi.consumers;

import com.rabbitmq.client.Channel;
import io.rabbit.emailapi.dto.EmailOrderDto;
import io.rabbit.emailapi.models.EmailEntity;
import io.rabbit.emailapi.services.EmailService;
import io.rabbit.emailapi.utils.RabbitMQConst;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper mapper;

    @RabbitListener(queues = { RabbitMQConst.EMAIL_QUEUE_NAME }, containerFactory = "customListenerConfig1")
    public void consumer(EmailOrderDto emailOrderDto, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, InterruptedException {
        EmailEntity emailMessage = emailService.buildEmail(emailOrderDto.getEmailTo(), emailOrderDto.getStatus().toString(), emailOrderDto.getOrderId().toString());

        log.info("Sending email to -> {} from order id -> {}", emailOrderDto.getEmailTo(), emailOrderDto.getOrderId());

        Thread.sleep(TimeUnit.SECONDS.toMillis(5));

        emailService.sendEmail(emailMessage);

        channel.basicAck(tag, false);
    }
}
