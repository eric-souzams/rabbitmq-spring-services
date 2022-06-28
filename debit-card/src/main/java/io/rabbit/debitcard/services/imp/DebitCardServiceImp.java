package io.rabbit.debitcard.services.imp;

import io.rabbit.debitcard.enums.CheckoutStatus;
import io.rabbit.debitcard.models.DebitCardEntity;
import io.rabbit.debitcard.repositories.DebitCardRepository;
import io.rabbit.debitcard.services.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

@Service
public class DebitCardServiceImp implements DebitCardService {

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Override
    public CheckoutStatus processOrder(DebitCardEntity debitCardEntity) {
        debitCardEntity.setDateTimeProcessed(OffsetDateTime.now());

        List<CheckoutStatus> status = List.of(CheckoutStatus.APPROVED, CheckoutStatus.REPROVED);
        CheckoutStatus randomStatus = status.get(new Random().nextInt(status.size()));

        debitCardEntity.setProcessStatus(randomStatus);

        debitCardRepository.save(debitCardEntity);

        return randomStatus;
    }

    @Transactional
    @Override
    public DebitCardEntity saveOrder(DebitCardEntity debitCardEntity) {
        return debitCardRepository.save(debitCardEntity);
    }

}
