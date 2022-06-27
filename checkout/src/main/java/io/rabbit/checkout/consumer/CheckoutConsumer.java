package io.rabbit.checkout.consumer;

import com.rabbitmq.client.Channel;
import io.rabbit.checkout.dto.UpdateStatusDto;
import io.rabbit.checkout.service.CheckoutService;
import io.rabbit.checkout.utils.RabbitMQConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CheckoutConsumer {

    @Autowired
    private CheckoutService checkoutService;

    @RabbitListener(queues = { RabbitMQConst.CHECKOUT_STATUS_QUEUE_NAME }, containerFactory = "customListenerConfig1")
    public void updateCheckoutOrderStatus(UpdateStatusDto request, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException, InterruptedException {
        checkoutService.updateStatus(request);

        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        log.info("Updating Checkout Order from id -> {}", request.getId());

        channel.basicAck(tag, false);
    }

}
