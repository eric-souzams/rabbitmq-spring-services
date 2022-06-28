package io.rabbit.checkout.service;

import io.rabbit.checkout.dto.UpdateStatusDto;
import io.rabbit.checkout.entity.CheckoutEntity;
import io.rabbit.checkout.enums.CheckoutStatus;

import java.util.UUID;

public interface CheckoutService {
    CheckoutEntity startCheckout(CheckoutEntity checkout, String userId);

    CheckoutEntity updateStatus(UpdateStatusDto updateStatusDto);

    CheckoutEntity findCheckoutById(UUID id);

}
