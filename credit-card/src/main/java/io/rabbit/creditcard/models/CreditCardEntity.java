package io.rabbit.creditcard.models;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "tb_credit_card_transactions")
public class CreditCardEntity {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

}
