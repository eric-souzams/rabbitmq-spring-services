package io.rabbit.creditcard.consumers;

import com.rabbitmq.client.Channel;
import io.rabbit.creditcard.dto.CheckoutOrderDto;
import io.rabbit.creditcard.dto.UpdateCheckoutOrderStatusDto;
import io.rabbit.creditcard.enums.CheckoutStatus;
import io.rabbit.creditcard.models.CreditCardEntity;
import io.rabbit.creditcard.producers.RabbitProducer;
import io.rabbit.creditcard.services.CreditCardService;
import io.rabbit.creditcard.utils.RabbitMQConst;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CreditCardConsumer {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private RabbitProducer rabbitProducer;

    @RabbitListener(queues = { RabbitMQConst.CREDIT_CARD_QUEUE_NAME }, containerFactory = "customListenerConfig1")
    public void consumer(CheckoutOrderDto checkoutOrderDto, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        CreditCardEntity convertedRequest = mapper.map(checkoutOrderDto, CreditCardEntity.class);

        log.info("Processing Order from id -> {}", checkoutOrderDto.getOrderId());

        CheckoutStatus processOrderResult = creditCardService.processOrder(convertedRequest);

        channel.basicAck(tag, false);

        UpdateCheckoutOrderStatusDto message = UpdateCheckoutOrderStatusDto.builder()
                .status(processOrderResult).orderId(checkoutOrderDto.getOrderId()).build();

        rabbitProducer.sendMessage(RabbitMQConst.EXCHANGE_NAME, RabbitMQConst.CHECKOUT_STATUS_ROUTING_KEY, message);
    }

}
