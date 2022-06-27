package io.rabbit.checkout.controller;

import io.rabbit.checkout.dto.CheckoutDto;
import io.rabbit.checkout.dto.CheckoutRequest;
import io.rabbit.checkout.dto.UpdateStatusDto;
import io.rabbit.checkout.entity.CheckoutEntity;
import io.rabbit.checkout.enums.CheckoutStatus;
import io.rabbit.checkout.producer.CheckoutProducer;
import io.rabbit.checkout.service.CheckoutService;
import io.rabbit.checkout.utils.RabbitMQConst;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final CheckoutProducer checkoutProducer;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> makeCheckout(@Valid @RequestBody CheckoutRequest request) {
        CheckoutEntity checkout = mapper.map(request, CheckoutEntity.class);

        checkout = checkoutService.startCheckout(checkout, request.getUserId());

        CheckoutDto message = mapper.map(checkout, CheckoutDto.class);

        checkoutProducer.sendMessage(RabbitMQConst.EXCHANGE_NAME, RabbitMQConst.CREDIT_CARD_ROUTING_KEY, message);

        return ResponseEntity.status(HttpStatus.CREATED).body("Your order will be processor, coming soon we go send an email confirmation.");
    }

    @GetMapping(value = "/test")
    public void test() {
        checkoutProducer.sendMessage(RabbitMQConst.EXCHANGE_NAME, RabbitMQConst.CHECKOUT_STATUS_ROUTING_KEY,
                UpdateStatusDto.builder()
                        .id(UUID.fromString("73d9cc2c-b29f-4c3d-9434-1ae241a9fdc8"))
                        .status(CheckoutStatus.APPROVED).build()
                );
    }
}
