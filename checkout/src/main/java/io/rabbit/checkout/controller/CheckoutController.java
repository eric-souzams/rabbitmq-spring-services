package io.rabbit.checkout.controller;

import io.rabbit.checkout.dto.CheckoutRequest;
import io.rabbit.checkout.entity.CheckoutEntity;
import io.rabbit.checkout.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> makeCheckout(@Valid @RequestBody CheckoutRequest request) {
        CheckoutEntity checkout = mapper.map(request, CheckoutEntity.class);

        checkoutService.startCheckout(checkout, request.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body("Your order will be processor, coming soon we go send an email confirmation.");
    }
}
