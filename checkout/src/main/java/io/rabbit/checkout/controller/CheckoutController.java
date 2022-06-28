package io.rabbit.checkout.controller;

import io.rabbit.checkout.dto.CheckoutDto;
import io.rabbit.checkout.dto.CheckoutProcessOrderDto;
import io.rabbit.checkout.dto.CheckoutRequest;
import io.rabbit.checkout.entity.CheckoutEntity;
import io.rabbit.checkout.enums.CardType;
import io.rabbit.checkout.producer.RabbitProducer;
import io.rabbit.checkout.service.CheckoutService;
import io.rabbit.checkout.utils.RabbitMQConst;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final RabbitProducer rabbitProducer;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> makeCheckout(@Valid @RequestBody CheckoutRequest request) {
        CheckoutEntity checkout = mapper.map(request, CheckoutEntity.class);
        checkout = checkoutService.startCheckout(checkout, request.getUserId());

        CheckoutDto message = mapper.map(checkout, CheckoutDto.class);
        message.setOrderId(checkout.getId());

        switch (request.getCardType()) {
            case CREDIT:
                rabbitProducer.sendMessage(RabbitMQConst.EXCHANGE_NAME, RabbitMQConst.CREDIT_CARD_ROUTING_KEY, message);
                break;
            case DEBIT:
                rabbitProducer.sendMessage(RabbitMQConst.EXCHANGE_NAME, RabbitMQConst.DEBIT_CARD_ROUTING_KEY, message);
                break;
            default:
                throw new RuntimeException("Invalid card type");
        }

        CheckoutProcessOrderDto response = CheckoutProcessOrderDto.builder().timestamp(new Date())
                .message("Your order will be processor, coming soon we go send an email confirmation.").build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
