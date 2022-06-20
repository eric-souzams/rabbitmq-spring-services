package io.rabbit.emailapi.repositories;

import io.rabbit.emailapi.models.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailEntity, UUID> {
}
