package io.rabbit.checkout.repository;

import io.rabbit.checkout.entity.CheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckoutRepository extends JpaRepository<CheckoutEntity, UUID> {
}
