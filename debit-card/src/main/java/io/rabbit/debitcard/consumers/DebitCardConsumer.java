package io.rabbit.debitcard.consumers;

import io.rabbit.debitcard.utils.RabbitMQConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DebitCardConsumer {

    @RabbitListener(queues = RabbitMQConst.QUEUE_NAME)
    public void consumer() {

    }

}
