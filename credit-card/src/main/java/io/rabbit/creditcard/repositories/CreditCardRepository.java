package io.rabbit.creditcard.repositories;

import io.rabbit.creditcard.models.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, UUID> {
}
