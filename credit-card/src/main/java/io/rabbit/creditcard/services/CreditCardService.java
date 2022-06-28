package io.rabbit.creditcard.services;

import io.rabbit.creditcard.enums.CheckoutStatus;
import io.rabbit.creditcard.models.CreditCardEntity;

public interface CreditCardService {
    CheckoutStatus processOrder(CreditCardEntity creditCardEntity);

    void saveOrder(CreditCardEntity creditCardEntity);

}
