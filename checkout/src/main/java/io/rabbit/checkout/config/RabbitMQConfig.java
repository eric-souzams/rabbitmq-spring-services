package io.rabbit.checkout.config;

import io.rabbit.checkout.utils.RabbitMQConst;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQConst.EXCHANGE_NAME);
    }

    private Binding binding(TopicExchange topicExchange, Queue queue, String routingKey) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(routingKey);
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @PostConstruct
    public void startup() {
        TopicExchange topicExchange = topicExchange();

        Queue queueCheckoutStatus = queue(RabbitMQConst.CHECKOUT_STATUS_QUEUE_NAME);
        Queue queueCreditCard = queue(RabbitMQConst.CREDIT_CARD_QUEUE_NAME);
        Queue queueDebitCard = queue(RabbitMQConst.DEBIT_CARD_QUEUE_NAME);
        Queue queueEmail = queue(RabbitMQConst.EMAIL_QUEUE_NAME);

        Binding bindingCreditCard = binding(topicExchange, queueCreditCard, RabbitMQConst.CREDIT_CARD_ROUTING_KEY);
        Binding bindingDebitCard = binding(topicExchange, queueDebitCard, RabbitMQConst.DEBIT_CARD_ROUTING_KEY);
        Binding bindingCheckoutStatus = binding(topicExchange, queueCheckoutStatus, RabbitMQConst.CHECKOUT_STATUS_ROUTING_KEY);
        Binding bindingEmail = binding(topicExchange, queueEmail, RabbitMQConst.EMAIL_ROUTING_KEY);

        amqpAdmin.declareQueue(queueCheckoutStatus);
        amqpAdmin.declareQueue(queueCreditCard);
        amqpAdmin.declareQueue(queueDebitCard);
        amqpAdmin.declareQueue(queueEmail);

        amqpAdmin.declareExchange(topicExchange);

        amqpAdmin.declareBinding(bindingCreditCard);
        amqpAdmin.declareBinding(bindingCheckoutStatus);
        amqpAdmin.declareBinding(bindingDebitCard);
        amqpAdmin.declareBinding(bindingEmail);
    }

}