package io.rabbit.debitcard.consumers;

import com.rabbitmq.client.Channel;
import io.rabbit.debitcard.dto.CheckoutOrderDto;
import io.rabbit.debitcard.dto.UpdateCheckoutOrderStatusDto;
import io.rabbit.debitcard.enums.CheckoutStatus;
import io.rabbit.debitcard.models.DebitCardEntity;
import io.rabbit.debitcard.producers.RabbitProducer;
import io.rabbit.debitcard.services.DebitCardService;
import io.rabbit.debitcard.utils.RabbitMQConst;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class DebitCardConsumer {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DebitCardService debitCardService;

    @Autowired
    private RabbitProducer rabbitProducer;

    @RabbitListener(queues = { RabbitMQConst.DEBIT_CARD_QUEUE_NAME }, containerFactory = "customListenerConfig1")
    public void consumer(CheckoutOrderDto checkoutOrderDto, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        DebitCardEntity convertedRequest = mapper.map(checkoutOrderDto, DebitCardEntity.class);

        log.info("Processing Order from id -> {}", checkoutOrderDto.getOrderId());

        CheckoutStatus processOrderResult = debitCardService.processOrder(convertedRequest);

        channel.basicAck(tag, false);

        UpdateCheckoutOrderStatusDto message = UpdateCheckoutOrderStatusDto.builder()
                .status(processOrderResult).orderId(checkoutOrderDto.getOrderId()).build();

        rabbitProducer.sendMessage(RabbitMQConst.EXCHANGE_NAME, RabbitMQConst.CHECKOUT_STATUS_ROUTING_KEY, message);
    }

}
