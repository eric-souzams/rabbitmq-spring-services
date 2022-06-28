package io.rabbit.debitcard.services;

import io.rabbit.debitcard.enums.CheckoutStatus;
import io.rabbit.debitcard.models.DebitCardEntity;

public interface DebitCardService {

    CheckoutStatus processOrder(DebitCardEntity debitCardEntity);

    DebitCardEntity saveOrder(DebitCardEntity debitCardEntity);

}
