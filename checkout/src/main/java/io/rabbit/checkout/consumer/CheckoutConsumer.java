package io.rabbit.checkout.consumer;

import com.rabbitmq.client.Channel;
import io.rabbit.checkout.dto.EmailResultOrderProcessDto;
import io.rabbit.checkout.dto.UpdateStatusDto;
import io.rabbit.checkout.entity.CheckoutEntity;
import io.rabbit.checkout.producer.RabbitProducer;
import io.rabbit.checkout.service.CheckoutService;
import io.rabbit.checkout.utils.RabbitMQConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CheckoutConsumer {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private RabbitProducer rabbitProducer;

    @RabbitListener(queues = { RabbitMQConst.CHECKOUT_STATUS_QUEUE_NAME }, containerFactory = "customListenerConfig1")
    public void updateCheckoutOrderStatus(UpdateStatusDto request, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException, InterruptedException {
        log.info("Updating Checkout Order from id -> {}", request.getOrderId());

        CheckoutEntity updatedOrder = checkoutService.updateStatus(request);
        channel.basicAck(tag, false);

        EmailResultOrderProcessDto message = EmailResultOrderProcessDto.builder()
                .orderId(updatedOrder.getId())
                .status(updatedOrder.getStatus())
                .emailTo(updatedOrder.getUser().getEmail())
                .build();

        rabbitProducer.sendMessage(RabbitMQConst.EXCHANGE_NAME, RabbitMQConst.EMAIL_ROUTING_KEY, message);
    }

}
