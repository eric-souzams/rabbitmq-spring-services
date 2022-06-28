package io.rabbit.creditcard.services.imp;

import io.rabbit.creditcard.enums.CheckoutStatus;
import io.rabbit.creditcard.models.CreditCardEntity;
import io.rabbit.creditcard.repositories.CreditCardRepository;
import io.rabbit.creditcard.services.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

@Service
public class CreditCardServiceImp implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public CheckoutStatus processOrder(CreditCardEntity creditCardEntity) {
        creditCardEntity.setDateTimeProcessed(OffsetDateTime.now());

        List<CheckoutStatus> status = List.of(CheckoutStatus.APPROVED, CheckoutStatus.REPROVED);
        CheckoutStatus randomStatus = status.get(new Random().nextInt(status.size()));

        creditCardEntity.setProcessStatus(randomStatus);

        saveOrder(creditCardEntity);

        return randomStatus;
    }

    @Transactional
    @Override
    public void saveOrder(CreditCardEntity creditCardEntity) {
        creditCardRepository.save(creditCardEntity);
    }
}
