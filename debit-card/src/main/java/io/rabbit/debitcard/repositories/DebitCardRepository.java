package io.rabbit.debitcard.repositories;

import io.rabbit.debitcard.models.DebitCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DebitCardRepository extends JpaRepository<DebitCardEntity, UUID> {
}
