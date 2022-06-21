package io.rabbit.checkout.consumer;

import io.rabbit.checkout.dto.UpdateStatusDto;
import io.rabbit.checkout.service.CheckoutService;
import io.rabbit.checkout.utils.RabbitMQConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckoutConsumer {

    @Autowired
    private CheckoutService checkoutService;

    @RabbitListener(queues = RabbitMQConst.CHECKOUT_STATUS_QUEUE_NAME)
    public void updateCheckoutOrderStatus(UpdateStatusDto request) {
        checkoutService.updateStatus(request);
    }

}
