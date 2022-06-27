package io.rabbit.checkout.utils;

public class RabbitMQConst {

    // Exchanges
    public static final String EXCHANGE_NAME = "messages.topic";

    // Queues
    public static final String CREDIT_CARD_QUEUE_NAME = "credit.card.queue";
    public static final String CHECKOUT_STATUS_QUEUE_NAME = "checkout.status.queue";
    public static final String EMAIL_QUEUE_NAME = "email.queue";
    public static final String DEBIT_CARD_QUEUE_NAME = "debit.card.queue";

    //Routing Keys
    public static final String CREDIT_CARD_ROUTING_KEY = "credit.card.key";
    public static final String CHECKOUT_STATUS_ROUTING_KEY = "checkout.status.key";
    public static final String DEBIT_CARD_ROUTING_KEY = "debit.card.key";
    public static final String EMAIL_ROUTING_KEY = "email.key";

}
