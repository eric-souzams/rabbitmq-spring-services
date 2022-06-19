package io.rabbit.checkout.service;

import io.rabbit.checkout.entity.CheckoutEntity;

public interface CheckoutService {
    CheckoutEntity startCheckout(CheckoutEntity checkout, String userEmail);
}
